package com.picpay.testebackend.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.picpay.testebackend.dto.UserDTO;
import com.picpay.testebackend.mapper.UserMapper;
import com.picpay.testebackend.model.User;
import com.picpay.testebackend.repository.rdbms.IUserRdbmsRepository;
import com.picpay.testebackend.service.IUserService;

@Service
public class UserService implements IUserService {

    private IUserRdbmsRepository userDAO;
    private UserMapper userMapper;

    @Autowired
    public UserService(IUserRdbmsRepository userDAO, UserMapper userMapper) {
        this.userDAO = userDAO;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
		User user = this.userDAO.save(this.userMapper.toUser(userDTO));
        return this.userMapper.toUserDTO(user);
    }

    @Override
    public UserDTO findById(Long id) {
        return this.userMapper.toUserDTO(this.userDAO.findById(id).orElse(null));
    }

    @Override
    public List<UserDTO> findAll(int pagina) {
    	PageRequest pageRequest = PageRequest.of(pagina, 15);
        return this.userMapper.toUserDtos(this.userDAO.findAll(pageRequest).getContent());
    }
}
