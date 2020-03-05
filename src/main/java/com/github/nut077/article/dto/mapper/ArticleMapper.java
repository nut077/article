package com.github.nut077.article.dto.mapper;

import com.github.nut077.article.dto.ArticleDto;
import com.github.nut077.article.dto.CommentDto;
import com.github.nut077.article.entity.Article;
import com.github.nut077.article.entity.Comment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArticleMapper extends BaseMapper<Article, ArticleDto> {

  @Override
  @Mapping(target = "authorId", source = "user.id")
  ArticleDto mapToDto(Article entity);

  @Mapping(target = "articleId", source = "article.id")
  @Mapping(target = "userId", source = "user.id")
  CommentDto mapToCommentDto(Comment entity);

  @Mapping(target = "article.id", source = "articleId")
  @Mapping(target = "user.id", source = "userId")
  Comment commentDtoToComment(CommentDto dto);
}
