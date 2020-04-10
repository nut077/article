package com.github.nut077.article.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

  private Long id;
  private String message;
  private Long articleId;
  private Long userId;
  private String username;
}
