package com.picpay.testebackend.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.picpay.testebackend.model.UserModel;
import com.picpay.testebackend.util.ResultQuery;

public interface ISearchService {
	ResultQuery searchFromQuery(String query) throws IOException;
	Page <UserModel> findByNomeLikeOrUsernameLike(String name, Pageable pageable) throws IOException;
}
