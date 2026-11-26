package com.storage.chatstorage.dto;

import java.time.Instant;
import java.util.UUID;

public class MessageDto {

	private UUID id;

	private String sender;

	private String content;

	private String contextJson;

	private Instant createdAt;

	public MessageDto() {
	}

	public MessageDto(UUID id, String sender, String content, String contextJson, Instant createdAt) {
		this.id = id;
		this.sender = sender;
		this.content = content;
		this.contextJson = contextJson;
		this.createdAt = createdAt;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContextJson() {
		return contextJson;
	}

	public void setContextJson(String contextJson) {
		this.contextJson = contextJson;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

}
