package com.github.nut077.article.controller.advice;

import com.github.nut077.article.dto.response.ExceptionResponse;
import com.github.nut077.article.exception.CommonException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@RestControllerAdvice
public class CommonAdvice extends ResponseEntityExceptionHandler {

  private ResponseEntity<Object> handle(Exception ex, HttpStatus status, int code) {
    log.error(ex);
    ExceptionResponse response = new ExceptionResponse();
    response.setCode("xxx-" + code);
    response.setMessage(ex.getMessage());
    return ResponseEntity.status(status).body(response);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
    return handle(ex, status, status.value());
  }

  @ExceptionHandler(CommonException.class)
  protected ResponseEntity<Object> handle(CommonException ex) {
    return handle(ex, ex.getStatus(), ex.getCode());
  }

  @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
  protected ResponseEntity<Object> handleIllegal(RuntimeException ex) {
    return handle(ex, HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<Object> handle(Exception ex) {
    return handle(ex, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value());
  }
}
