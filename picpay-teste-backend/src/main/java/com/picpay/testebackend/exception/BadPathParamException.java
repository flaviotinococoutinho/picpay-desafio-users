package com.picpay.testebackend.exception;

public class BadPathParamException extends RuntimeException {
	private static final long serialVersionUID = -7073548164814864123L;

	public BadPathParamException(String error) {
        super(error);
    }
}