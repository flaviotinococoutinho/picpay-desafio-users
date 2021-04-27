package com.picpay.testebackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picpay.testebackend.dto.UserQuerySearchDTO;
import com.picpay.testebackend.mapper.UserQuerySearchMapper;
import com.picpay.testebackend.model.UserQuerySearch;
import com.picpay.testebackend.repository.rdbms.IUserQuerySearchRdbmsRepository;
import com.picpay.testebackend.service.IUserQuerySearchService;

@Service
public class UserQuerySearchService implements IUserQuerySearchService {

	private IUserQuerySearchRdbmsRepository queryUserDAO;
	private UserQuerySearchMapper userQueryMapper;

	@Autowired
	public UserQuerySearchService(IUserQuerySearchRdbmsRepository userDAO, UserQuerySearchMapper userQueryMapper) {
		this.queryUserDAO = userDAO;
		this.userQueryMapper = userQueryMapper;
	}

	@Override
	public UserQuerySearchDTO save(UserQuerySearchDTO userQuerySearchDTO) {
		UserQuerySearch userQuerySearch = this.queryUserDAO
				.save(this.userQueryMapper.toUserQuerySearch(userQuerySearchDTO));
		return this.userQueryMapper.toUserQuerySearchDTO(userQuerySearch);
	}

}
