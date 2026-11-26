package com.storage.chatstorage.config;

import io.github.bucket4j.*;
import io.github.bucket4j.Bucket;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

	private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

	private Bucket resolveBucket(String key) {
		return buckets.computeIfAbsent(key, k -> {
			Bandwidth limit = Bandwidth.classic(100, Refill.greedy(100, Duration.ofMinutes(1)));
			return Bucket.builder().addLimit(limit).build();
		});
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String key = request.getHeader("X-API-KEY");
		if (key == null) {
			filterChain.doFilter(request, response);
			return;
		}
		Bucket bucket = resolveBucket(key);
		if (bucket.tryConsume(1)) {
			filterChain.doFilter(request, response);
		}
		else {
			response.setStatus(429);
			response.setContentType("application/json");
			response.getWriter().write("{\"error\":\"Too many requests\"}");
		}
	}

}
