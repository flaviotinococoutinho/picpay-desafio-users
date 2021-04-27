package com.picpay.testebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.testebackend.controller.swagger.IUserController;
import com.picpay.testebackend.dto.UserDTO;
import com.picpay.testebackend.service.IUserService;
import com.picpay.testebackend.util.PathResources;

@RestController
@RequestMapping(PathResources.USER)
public class UserController extends ControllerBase<UserDTO> implements IUserController {

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
	public ResponseEntity<UserDTO> findOne(@PathVariable Long id) {
		return responderSucessoComItem(this.userService.findById(id));
	}

	@GetMapping(PathResources.FIND_ALL)
	public ResponseEntity<List<UserDTO>> findById(
			@RequestParam(value = "pagina", required = false, defaultValue = "0") int pagina) {
		return new ResponseEntity<>(this.userService.findAll(pagina), HttpStatus.OK);
	}
}
