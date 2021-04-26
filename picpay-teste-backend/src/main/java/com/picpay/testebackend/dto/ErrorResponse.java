package com.picpay.testebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class ErrorResponse {

	@Getter
	@Setter
	private String message;
	@Getter
	@Setter
	private String reason;
	@Getter
	@Setter
	private String location;
}