package com.github.nut077.article.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleDto {

  private String id;
  private String title;
  private String content;
  private String excerpt;
  private String authorId;
  private List<CommentDto> comments;
}
