package com.storage.chatstorage.service;

import com.storage.chatstorage.exception.NotFoundException;
import com.storage.chatstorage.model.ChatSession;
import com.storage.chatstorage.repo.ChatMessageRepository;
import com.storage.chatstorage.repo.ChatSessionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class SessionService {

	private final ChatSessionRepository sessionRepo;

	private final ChatMessageRepository messageRepo;

	public SessionService(ChatSessionRepository sessionRepo, ChatMessageRepository messageRepo) {
		this.sessionRepo = sessionRepo;
		this.messageRepo = messageRepo;
	}

	public ChatSession create(String userId, String title) {
		ChatSession s = new ChatSession(userId, title);
		return sessionRepo.save(s);
	}

	public ChatSession rename(UUID sessionId, String userId, String newTitle) {
		ChatSession s = sessionRepo.findById(sessionId).orElseThrow(() -> new NotFoundException("Session not found"));
		if (!s.getUserId().equals(userId))
			throw new NotFoundException("Session not found for user");
		s.setTitle(newTitle);
		return sessionRepo.save(s);
	}

	public void setFavorite(UUID sessionId, String userId, boolean favorite) {
		ChatSession s = sessionRepo.findById(sessionId).orElseThrow(() -> new NotFoundException("Session not found"));
		if (!s.getUserId().equals(userId))
			throw new NotFoundException("Session not found for user");
		s.setFavorite(favorite);
		sessionRepo.save(s);
	}

	public void deleteSession(UUID sessionId, String userId) {
		ChatSession s = sessionRepo.findById(sessionId).orElseThrow(() -> new NotFoundException("Session not found"));
		if (!s.getUserId().equals(userId))
			throw new NotFoundException("Session not found for user");
		messageRepo.deleteBySessionId(sessionId);
		sessionRepo.deleteById(sessionId);
	}

	public List<ChatSession> listByUser(String userId) {
		return sessionRepo.findByUserIdOrderByCreatedAtDesc(userId);
	}

}
