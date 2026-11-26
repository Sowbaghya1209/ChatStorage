package com.storage.chatstorage.service;

import com.storage.chatstorage.exception.NotFoundException;
import com.storage.chatstorage.model.ChatMessage;
import com.storage.chatstorage.model.ChatSession;
import com.storage.chatstorage.repo.ChatMessageRepository;
import com.storage.chatstorage.repo.ChatSessionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class MessageService {

	private final ChatMessageRepository messageRepo;

	private final ChatSessionRepository sessionRepo;

	public MessageService(ChatMessageRepository messageRepo, ChatSessionRepository sessionRepo) {
		this.messageRepo = messageRepo;
		this.sessionRepo = sessionRepo;
	}

	public ChatMessage addMessage(UUID sessionId, String sender, String content, String contextJson) {
		ChatSession s = sessionRepo.findById(sessionId).orElseThrow(() -> new NotFoundException("Session not found"));
		ChatMessage m = new ChatMessage(s, sender, content, contextJson);
		return messageRepo.save(m);
	}

	public Page<ChatMessage> listMessages(UUID sessionId, int page, int size) {
		Pageable p = PageRequest.of(page, size, Sort.by("createdAt").ascending());
		return messageRepo.findBySessionIdOrderByCreatedAtAsc(sessionId, p);
	}

}
