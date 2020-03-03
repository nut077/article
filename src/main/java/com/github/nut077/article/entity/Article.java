package com.github.nut077.article.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "articles")
public class Article extends Common {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 4000)
  private String content;

  private String title;
  private String excerpt;
}
