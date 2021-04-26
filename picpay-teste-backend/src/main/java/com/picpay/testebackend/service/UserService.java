package com.picpay.testebackend.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.picpay.testebackend.model.User;

public interface UserService {
    long getUserCount();
    List<User> getAllUsers(int page, int size);
    User getUserById(String id);
    List<User> getUsersByPattern(String query);
 //   Page<User> getUsersByGivenName(String name);
    void saveUser();
}