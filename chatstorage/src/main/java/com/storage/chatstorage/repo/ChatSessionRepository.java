package com.storage.chatstorage.repo;

import com.storage.chatstorage.model.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatSessionRepository extends JpaRepository<ChatSession, UUID> {

	List<ChatSession> findByUserIdOrderByCreatedAtDesc(String userId);

}