package com.picpay.testebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.picpay.testebackend.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}
