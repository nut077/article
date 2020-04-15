package com.github.nut077.article.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleCreateDto {

  private String id;
  private String title;
  private String content;
  private String excerpt;
  private Long authorId;
}
