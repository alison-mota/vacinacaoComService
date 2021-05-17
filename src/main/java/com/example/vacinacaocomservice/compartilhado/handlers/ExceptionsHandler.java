package com.example.vacinacaocomservice.compartilhado.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> interceptaBeanValidation(MethodArgumentNotValidException ex){
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String campos = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String mensagens = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        ExceptionResponse response = new ExceptionResponse(campos, mensagens, String.valueOf(HttpStatus.BAD_REQUEST));


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponse> interceptaResponseStatusException(ResponseStatusException ex){
        HttpStatus status = ex.getStatus();
        String localizedMessage = ex.getReason();

        ExceptionResponse response = new ExceptionResponse("Erro na requisição", localizedMessage, String.valueOf(status));

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> interceptaHttpMessageNotReadableException(HttpMessageNotReadableException ex){

        ExceptionResponse response = new ExceptionResponse("Erro na requisição", "Data inválida", String.valueOf(HttpStatus.BAD_REQUEST));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}
