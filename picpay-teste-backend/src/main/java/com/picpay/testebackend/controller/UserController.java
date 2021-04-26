package com.picpay.testebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.testebackend.exception.BadPathParamException;
import com.picpay.testebackend.model.User;
import com.picpay.testebackend.service.UserService;
import com.picpay.testebackend.util.PaginationUtil;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
    private UserService userService;
	
	@GetMapping("/")
    public List<User> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return userService.getAllUsers(page, size);
    }
	
	@GetMapping("/teste")
    public String getEchoUsers() {
        return "teste";
    }
	
    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable String id) {
        if (id.length() != 6) {
            throw new BadPathParamException("ID should be exactly 6 digits long.");
        }
        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/busca")
    public List<User> getUsersByGivenPattern(@RequestParam(name = "query") String query) {
        return userService.getUsersByPattern(query);
    }
/*
    @GetMapping("/search-by-name")
    public ResponseEntity<?> getUsersByGivenName(@RequestParam(name = "name") String name) {
		Page<User> user = userService.getUsersByGivenName(name);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(user, "/usuario/busca");
		return ResponseEntity.ok().headers(headers).body(user.getContent());
    }
    */
}
