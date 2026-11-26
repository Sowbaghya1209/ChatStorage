package com.storage.chatstorage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "chat_messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "session_id", nullable = false)
	private ChatSession session;

	@Column(nullable = false)
	private String sender;

	@Column(columnDefinition = "text", nullable = false)
	private String content;

	@Column(columnDefinition = "text")
	private String contextJson;

	@Column(nullable = false)
	private Instant createdAt = Instant.now();

	public ChatMessage(ChatSession session, String sender, String content, String contextJson) {
		this.session = session;
		this.sender = sender;
		this.content = content;
		this.contextJson = contextJson;
	}

}