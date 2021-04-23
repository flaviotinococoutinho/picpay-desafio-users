package com.picpay.testebackend.dto;

public class UserDTO {

	private int id;
	private String username;
	private String nome;
	private String uuid;

	public UserDTO() {
	}

	public UserDTO(String username, String nome, String uuid) {
		super();
		this.username = username;
		this.nome = nome;
		this.uuid = uuid;
	}

	public UserDTO(int id, String username, String nome, String uuid) {
		super();
		this.id = id;
		this.username = username;
		this.nome = nome;
		this.uuid = uuid;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		this.nome = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", username='" + username + '\'' + ", nome='" + nome + '\'' + ", uuid='" + uuid
				+ '\'' + '}';
	}

}