package com.github.nut077.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
  private List<Comment> comments;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  public void setComments(List<Comment> comments) {
    if (comments != null) {
      comments.forEach(comment -> comment.setArticle(this));
    }
    this.comments = comments;
  }
}
