package com.picpay.testebackend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.testebackend.controller.swagger.ISearchController;
import com.picpay.testebackend.model.UserModel;
import com.picpay.testebackend.service.ISearchService;
import com.picpay.testebackend.util.Constants;
import com.picpay.testebackend.util.PaginationUtil;
import com.picpay.testebackend.util.PathResources;

@RestController
@RequestMapping(PathResources.SEARCH_CONTROLLER)
public class SearchController extends ControllerBase<UserModel> implements ISearchController{

    private ISearchService searchService;

    @Autowired
    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }
    
    @GetMapping("/{" + Constants.QUERY + "}/{pageAtual}")
    public ResponseEntity<Page<UserModel>> searchNomeOrUsername(@PathVariable String query,@PathVariable int pageAtual) throws IOException {
    	PageRequest pageRequest = PageRequest.of(pageAtual, 15);
    	Page<UserModel> page = searchService.findByNomeLikeOrUsernameLike(query.trim(),pageRequest);
    	HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, PathResources.SEARCH_CONTROLLER+"/");
    	return responderPaginado(headers,page);
    }
    
    
}
