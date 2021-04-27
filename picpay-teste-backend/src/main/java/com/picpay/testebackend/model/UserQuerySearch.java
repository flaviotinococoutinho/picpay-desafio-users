package com.picpay.testebackend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class UserQuerySearch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String query;

	public UserQuerySearch() {
		super();
	}

	public UserQuerySearch(Long id, String query) {
		super();
		this.id = id;
		this.query = query;
	}

	public Long getId() {
		return id;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

