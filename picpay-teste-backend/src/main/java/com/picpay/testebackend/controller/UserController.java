package com.picpay.testebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.testebackend.dto.UserDTO;
import com.picpay.testebackend.service.IUserService;
import com.picpay.testebackend.util.PathResources;

@RestController
@RequestMapping(PathResources.USER)
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(PathResources.SAVE)
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(this.userService.save(userDTO), HttpStatus.OK);
    }

    @GetMapping(PathResources.FIND_ONE + "/{" + PathResources.ID + "}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(this.userService.findById(id), HttpStatus.OK);
    }
    
    
    @GetMapping(PathResources.FIND_ALL)
    public ResponseEntity<List<UserDTO>> findById(@RequestParam(value = "pagina", required = false, defaultValue = "0") int pagina) {
        return new ResponseEntity<>(this.userService.findAll(pagina), HttpStatus.OK);
    }
}
