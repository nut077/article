package com.github.nut077.article.dto.mapper;

import com.github.nut077.article.dto.ArticleDto;
import com.github.nut077.article.entity.Article;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ArticleMapper extends BaseMapper<Article, ArticleDto> {
}
