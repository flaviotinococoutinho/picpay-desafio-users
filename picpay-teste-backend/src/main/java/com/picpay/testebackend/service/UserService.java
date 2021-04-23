package com.picpay.testebackend.service;

import org.springframework.data.domain.Page;

import com.picpay.testebackend.dto.UserDTO;
import com.picpay.testebackend.model.User;

public interface UserService
{
        User create ( UserDTO userDTO );
        User getOne ( Integer id );
        Page<User> findByNomeLikeOrUsernameLike(String query, Integer pagina, Integer tamanho);
}
