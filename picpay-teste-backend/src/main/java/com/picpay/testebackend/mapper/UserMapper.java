package com.picpay.testebackend.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.picpay.testebackend.dto.UserDTO;
import com.picpay.testebackend.model.User;
import com.picpay.testebackend.model.UserModel;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDtos(List<User> users);

    User toUser(UserDTO userDTO);

    List<User> toUsers(List<UserDTO> userDTOS);

    UserModel toUserModel(User user);
}
