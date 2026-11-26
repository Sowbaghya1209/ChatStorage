package com.storage.chatstorage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionDto {

	private UUID id;

	private String userId;

	private String title;

	private boolean favorite;

	private Instant createdAt;

}