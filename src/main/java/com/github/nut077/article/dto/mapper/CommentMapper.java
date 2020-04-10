package com.github.nut077.article.dto.mapper;

import com.github.nut077.article.dto.CommentDto;
import com.github.nut077.article.entity.Comment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommentMapper extends BaseMapper<Comment, CommentDto> {

  @Override
  @Mapping(target = "articleId", source = "article.id")
  @Mapping(target = "userId", source = "user.id")
  @Mapping(target = "username", source = "user.username")
  CommentDto mapToDto(Comment entity);
}
