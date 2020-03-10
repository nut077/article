package com.github.nut077.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "users")
public class User extends Common {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(unique = true)
  private String username;

  @NotNull
  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Article> articles;

  public void setArticles(List<Article> articles) {
    articles.forEach(article -> article.setUser(this));
    this.articles = articles;
  }

  @OneToMany(mappedBy = "user")
  private List<Comment> comments;

  public void setComments(List<Comment> comments) {
    comments.forEach(comment -> comment.setUser(this));
    this.comments = comments;
  }

  @ManyToMany
  @JsonIgnore
  @JoinTable(
    name = "users_roles",
    joinColumns = @JoinColumn(name = "users_id"),
    inverseJoinColumns = @JoinColumn(name = "roles_id")
  )
  private Set<Role> roles;
}
