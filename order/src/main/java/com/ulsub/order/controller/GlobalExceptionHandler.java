package com.ulsub.order.controller;

import com.ulsub.order.dto.ErrorResponseDto;
import com.ulsub.order.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ResponseBody
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErrorResponseDto handleEntityNotFoundException(EntityNotFoundException exception) {
        log.error("Entity not found error: {}", exception.getMessage());
        return new ErrorResponseDto(exception.getMessage());
    }

    //TODO make better error response, for now only default message so ex. "errorMessage": "must not be null", without info what must not be null
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String messages = StringUtils.join(e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList(), ", ");
        log.error("Request parameters constraints violation: " + messages);
        return new ResponseEntity<>(new ErrorResponseDto(messages), HttpStatus.BAD_REQUEST);
    }
}
