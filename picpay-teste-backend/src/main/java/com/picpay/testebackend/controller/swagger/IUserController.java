package com.picpay.testebackend.controller.swagger;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.picpay.testebackend.dto.UserDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface IUserController {
	

	@ApiOperation(value = "Persiste dados do usuário.", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Salvo com sucesso")
        ,@ApiResponse(code = 400, message = "Solicitação inválida")
        ,@ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso")
        ,@ApiResponse(code = 403, message = "Acessar o recurso que você estava tentando acessar é proibido")
        ,@ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
    })
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO);
   
	@ApiOperation(value = "Busca usuário pelo ID.", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK!")
        ,@ApiResponse(code = 400, message = "Solicitação inválida")
        ,@ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso")
        ,@ApiResponse(code = 403, message = "Acessar o recurso que você estava tentando acessar é proibido")
        ,@ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
    })
    public ResponseEntity<UserDTO> findOne(@PathVariable Long id);
    
	@ApiOperation(value = "Busca usuário pelo banco relacional pelo ID.", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK!")
        ,@ApiResponse(code = 400, message = "Solicitação inválida")
        ,@ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso")
        ,@ApiResponse(code = 403, message = "Acessar o recurso que você estava tentando acessar é proibido")
        ,@ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
    })
    public ResponseEntity<List<UserDTO>> findById(@RequestParam(value = "pagina", required = false, defaultValue = "0") int pagina);
}
