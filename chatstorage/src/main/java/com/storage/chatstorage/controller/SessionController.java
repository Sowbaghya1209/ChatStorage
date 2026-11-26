package com.storage.chatstorage.controller;

import com.storage.chatstorage.dto.CreateSessionRequest;
import com.storage.chatstorage.dto.SessionDto;
import com.storage.chatstorage.model.ChatSession;
import com.storage.chatstorage.service.SessionService;
import com.storage.chatstorage.util.Mapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {

	private final SessionService sessionService;

	public SessionController(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	@PostMapping
	public ResponseEntity<SessionDto> create(@Valid @RequestBody CreateSessionRequest req) {
		ChatSession s = sessionService.create(req.getUserId(), req.getTitle());
		return ResponseEntity.ok(Mapper.toDto(s));
	}

	@PatchMapping("/{id}/rename")
	public ResponseEntity<SessionDto> rename(@PathVariable UUID id, @RequestParam String userId,
			@RequestParam String title) {
		ChatSession s = sessionService.rename(id, userId, title);
		return ResponseEntity.ok(Mapper.toDto(s));
	}

	@PatchMapping("/{id}/favorite")
	public ResponseEntity<Void> favorite(@PathVariable UUID id, @RequestParam String userId,
			@RequestParam boolean favorite) {
		sessionService.setFavorite(id, userId, favorite);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id, @RequestParam String userId) {
		sessionService.deleteSession(id, userId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<SessionDto>> list(@RequestParam String userId) {
		return ResponseEntity
			.ok(sessionService.listByUser(userId).stream().map(Mapper::toDto).collect(Collectors.toList()));
	}

}
