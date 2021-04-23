package com.picpay.testebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.testebackend.model.User;
import com.picpay.testebackend.service.UserService;
import com.picpay.testebackend.util.PaginationUtil;

@RestController
@RequestMapping (produces = "application/json", value="/api/users")
public class UserController {
	
    @Autowired
    private UserService userService;
    
    	
	@GetMapping("/usuario/busca")
	public ResponseEntity<?> getPaginateUser(@RequestParam(value = "q", required = false) String query,
			@RequestParam(value = "pagina", required = false, defaultValue = "0") Integer pagina,
			@RequestParam(value = "tamanho", required = false, defaultValue = "15") Integer tamanhoPag) {

		if (query == null) {
			query = "";
		}

		Page<User> page = userService.findByNomeLikeOrUsernameLike(query, pagina, tamanhoPag);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/usuario/busca");
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}
    
    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> show(@PathVariable String id) {
        int userId = Integer.parseInt(id);
        return ResponseEntity.ok().body(userService.getOne(userId));
    }

}
