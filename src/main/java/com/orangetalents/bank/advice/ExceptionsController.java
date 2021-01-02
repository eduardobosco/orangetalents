package com.orangetalents.bank.advice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.orangetalents.bank.exception.ClienteNotFoundException;
import com.orangetalents.bank.exception.ParametroObrigatorioException;

@RestControllerAdvice
public class ExceptionsController {

	

	@ExceptionHandler(ParametroObrigatorioException.class)
	public ResponseEntity<Void> trataParametroObrigatorio(ParametroObrigatorioException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();

	}
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Void> trataCadastroDuplicado(DataIntegrityViolationException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();

	}
	
	@ExceptionHandler(ClienteNotFoundException.class)
	public ResponseEntity<Void> trataClienteFound(ClienteNotFoundException exception) {
		String mensagem = exception.getMessage();
		return ResponseEntity.notFound().header("x-erro-msg", mensagem).build();

	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> trataValidacoes(MethodArgumentNotValidException exception) {
		
		Map<String, String> errosMap = new HashMap<String, String>();
		
		List<ObjectError> errosEncontrados = exception.getBindingResult().getAllErrors();
		
		for (ObjectError erro : errosEncontrados) {
			
			FieldError fieldError = (FieldError) erro;
			
			String atributo = fieldError.getField();
			String mensagem = fieldError.getDefaultMessage();
			errosMap.put(atributo, mensagem);
		}
		return ResponseEntity.badRequest().body(errosMap);
	}
}
