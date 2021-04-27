package com.picpay.testebackend.mapper;

import org.mapstruct.Mapper;

import com.picpay.testebackend.dto.UserQuerySearchDTO;
import com.picpay.testebackend.model.UserQuerySearch;

@Mapper(componentModel = "spring")
public interface UserQuerySearchMapper {

	UserQuerySearchDTO toUserQuerySearchDTO(UserQuerySearch userQuerySearch);
	UserQuerySearch toUserQuerySearch(UserQuerySearchDTO userQuerySearchDTO);
}
