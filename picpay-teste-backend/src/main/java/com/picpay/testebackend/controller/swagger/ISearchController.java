package com.picpay.testebackend.controller.swagger;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.picpay.testebackend.model.UserModel;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ISearchController {

	@ApiOperation(value = "Busca o nome ou username do usuário.", response = Iterable.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK!")
        ,@ApiResponse(code = 400, message = "Solicitação inválida")
        ,@ApiResponse(code = 401, message = "Você não está autorizado a visualizar o recurso")
        ,@ApiResponse(code = 403, message = "Acessar o recurso que você estava tentando acessar é proibido")
        ,@ApiResponse(code = 404, message = "O recurso que você estava tentando acessar não foi encontrado")
    })
    public ResponseEntity<Page<UserModel>> searchNomeOrUsername(@PathVariable String query,@PathVariable int pageAtual) throws IOException;
}
