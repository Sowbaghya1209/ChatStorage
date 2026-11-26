package com.storage.chatstorage.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMessageRequest {

	@NotBlank
	private String sender;

	@NotBlank
	private String content;

	private String contextJson;

}