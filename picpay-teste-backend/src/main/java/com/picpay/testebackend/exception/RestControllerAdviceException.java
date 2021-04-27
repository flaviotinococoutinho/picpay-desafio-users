package com.picpay.testebackend.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdviceException {
  
  @Autowired
  private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrorItem> handle(MethodArgumentNotValidException exception) {
		List<ErrorItem> dto = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		fieldErrors.stream().forEach((e) -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErrorItem erro = new ErrorItem(e.getField(), mensagem);
			dto.add(erro);
		});

		return dto;
	}

}
