package com.picpay.testebackend.model.sql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Entity
@Document(indexName = "pictest", type = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Field(type = FieldType.Text)
	private String username;
	@Field(type = FieldType.Text)
	private String nome;
	private String uuid;

	public User() {
	}

	public User(String username, String nome, String uuid) {
		super();
		this.username = username;
		this.nome = nome;
		this.uuid = uuid;
	}

	public User(int id, String username, String nome, String uuid) {
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
