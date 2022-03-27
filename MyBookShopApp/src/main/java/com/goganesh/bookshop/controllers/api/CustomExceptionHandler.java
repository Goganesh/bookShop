package com.goganesh.bookshop.controllers.api;

import com.goganesh.bookshop.exception.NoJwtTokenException;
import com.goganesh.bookshop.exception.NoSuchBookAction;
import com.goganesh.bookshop.dto.CardActionResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NoJwtTokenException.class, NoSuchBookAction.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {

        CardActionResponse cardActionResponse = CardActionResponse.builder()
                .result(false)
                .error(ex.getMessage())
                .build();

        return handleExceptionInternal(
                ex,
                cardActionResponse,
                new HttpHeaders(),
                HttpStatus.OK,
                request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, List<String>> body = new HashMap<>();
    //todo think how organize message info
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
