package com.picpay.testebackend.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.testebackend.service.ISearchService;
import com.picpay.testebackend.util.Constants;
import com.picpay.testebackend.util.PathResources;
import com.picpay.testebackend.util.ResultQuery;

@RestController
@RequestMapping(PathResources.SEARCH_CONTROLLER)
public class SearchController {

    private ISearchService searchService;

    @Autowired
    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(Constants.SEARCH_QUERY + "/{" + Constants.QUERY + "}")
    public ResponseEntity<ResultQuery> searchQuery(@PathVariable String query) throws IOException {
        return new ResponseEntity<> (searchService.searchFromQuery(query.trim().toLowerCase()), HttpStatus.OK);
    }
}
