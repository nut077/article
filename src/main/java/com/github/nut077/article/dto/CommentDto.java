package com.github.nut077.article.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

  private String message;
  private String articleId;
  private String userId;
}
