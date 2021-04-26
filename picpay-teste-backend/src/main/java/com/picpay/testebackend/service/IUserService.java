package com.picpay.testebackend.service;

import java.util.List;

import com.picpay.testebackend.dto.UserDTO;

public interface IUserService {
	UserDTO save(UserDTO userDTO);

	UserDTO findById(Long id);

	List<UserDTO> findAll(int pagina);
}
