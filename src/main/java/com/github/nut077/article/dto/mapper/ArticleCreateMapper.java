package com.github.nut077.article.dto.mapper;

import com.github.nut077.article.dto.ArticleCreateDto;
import com.github.nut077.article.entity.Article;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArticleCreateMapper extends BaseMapper<Article, ArticleCreateDto> {

  @Override
  @Mapping(target = "authorId", source = "user.id")
  ArticleCreateDto mapToDto(Article entity);
}
