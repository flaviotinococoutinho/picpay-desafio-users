package com.picpay.testebackend.service;

import java.io.IOException;

import com.picpay.testebackend.util.ResultQuery;

public interface ISearchService {
	ResultQuery searchFromQuery(String query) throws IOException;
}
