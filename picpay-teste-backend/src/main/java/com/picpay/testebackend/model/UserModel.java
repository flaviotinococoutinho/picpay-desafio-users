package com.picpay.testebackend.model;

import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Document(indexName = "user")
public class UserModel {
    private Long id;
    private String nome;
    private String username;
    private Date modificationDate;

    public UserModel() {
		super();
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }
}
