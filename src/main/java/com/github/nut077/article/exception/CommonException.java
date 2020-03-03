package com.github.nut077.article.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonException extends RuntimeException {

  protected HttpStatus status;
  protected int code;

  public CommonException(HttpStatus status, int code, String message) {
    super(message);
    this.status = status;
    this.code = code;
  }
}
