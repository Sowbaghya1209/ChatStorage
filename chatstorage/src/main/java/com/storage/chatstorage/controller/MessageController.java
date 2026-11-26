package com.storage.chatstorage.controller;

import com.storage.chatstorage.dto.CreateMessageRequest;
import com.storage.chatstorage.dto.MessageDto;
import com.storage.chatstorage.model.ChatMessage;
import com.storage.chatstorage.service.MessageService;
import com.storage.chatstorage.util.Mapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sessions/{sessionId}/messages")
public class MessageController {

	private final MessageService messageService;

	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping
	public ResponseEntity<MessageDto> addMessage(@PathVariable UUID sessionId,
			@Valid @RequestBody CreateMessageRequest req) {
		ChatMessage m = messageService.addMessage(sessionId, req.getSender(), req.getContent(), req.getContextJson());
		return ResponseEntity.ok(Mapper.toDto(m));
	}

	@GetMapping
	public ResponseEntity<Page<MessageDto>> listMessages(@PathVariable UUID sessionId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
		Page<ChatMessage> msgs = messageService.listMessages(sessionId, page, size);
		Page<MessageDto> dtoPage = msgs.map(Mapper::toDto);
		return ResponseEntity.ok(dtoPage);
	}

}
