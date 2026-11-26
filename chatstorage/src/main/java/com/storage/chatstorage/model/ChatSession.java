package com.storage.chatstorage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "chat_sessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatSession {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private boolean favorite = false;

	@Column(nullable = false)
	private Instant createdAt = Instant.now();

	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("createdAt ASC")
	private List<ChatMessage> messages = new ArrayList<>();

	public ChatSession(String userId, String title) {
		this.userId = userId;
		this.title = title;
	}

}