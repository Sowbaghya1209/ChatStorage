package com.storage.chatstorage.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

	@Value("${security.api-key:changeme}")
	private String apiKey;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("X-API-KEY");
		String path = request.getRequestURI();
		if (path.startsWith("/swagger") || path.startsWith("/v3/api-docs") || path.equals("/health")) {
			filterChain.doFilter(request, response);
			return;
		}
		if (header == null || !header.equals(apiKey)) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("application/json");
			response.getWriter().write("{\"error\":\"Unauthorized\"}");
			return;
		}
		filterChain.doFilter(request, response);
	}

}
