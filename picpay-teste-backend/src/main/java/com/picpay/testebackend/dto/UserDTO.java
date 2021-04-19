package com.picpay.testebackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@SuperBuilder
@ToString
@JsonInclude(Include.NON_NULL)
public class UserDTO {
	private String id;
	private String nome;
	private String username;
}
