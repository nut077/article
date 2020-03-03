package com.github.nut077.article.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class SuccessResponse<T> {

  private String code;
  private String message;
  private OffsetDateTime timestamp;
  private T data;

  private SuccessResponse(String code, String message, OffsetDateTime timestamp, T data) {
    this.code = code;
    this.message = message;
    this.timestamp = timestamp;
    this.data = data;
  }

  public static <T> SuccessResponse builder(T data) {
    return new SuccessResponse<>("xxx-200", "success", OffsetDateTime.now(), data);
  }
}
