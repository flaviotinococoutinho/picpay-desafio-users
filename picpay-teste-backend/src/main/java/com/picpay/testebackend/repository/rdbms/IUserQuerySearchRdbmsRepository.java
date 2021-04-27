package com.picpay.testebackend.repository.rdbms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.picpay.testebackend.model.User;
import com.picpay.testebackend.model.UserQuerySearch;

@Repository
public interface IUserQuerySearchRdbmsRepository
		extends JpaRepository<UserQuerySearch, Long>, JpaSpecificationExecutor<User> {

}
