package com.picpay.testebackend.exception;

public class ErrorItem {

	private String field;
	private String message;
	
	public ErrorItem(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorItem [field=" + field + ", message=" + message + "]";
	}

}
