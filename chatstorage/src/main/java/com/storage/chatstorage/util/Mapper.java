package com.storage.chatstorage.util;

import com.storage.chatstorage.dto.MessageDto;
import com.storage.chatstorage.dto.SessionDto;
import com.storage.chatstorage.model.ChatMessage;
import com.storage.chatstorage.model.ChatSession;

public class Mapper {

	public static SessionDto toDto(ChatSession s) {
		return new SessionDto(s.getId(), s.getUserId(), s.getTitle(), s.isFavorite(), s.getCreatedAt());
	}

	public static MessageDto toDto(ChatMessage m) {
		return new MessageDto(m.getId(), m.getSender(), m.getContent(), m.getContextJson(), m.getCreatedAt());
	}

}
