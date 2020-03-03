package com.github.nut077.article.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NotFoundException extends CommonException {

  public NotFoundException(String message) {
    super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), message);
  }
}
