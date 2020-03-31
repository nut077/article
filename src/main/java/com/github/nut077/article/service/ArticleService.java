package com.github.nut077.article.service;

import com.github.nut077.article.dto.ArticleDto;
import com.github.nut077.article.dto.mapper.ArticleMapper;
import com.github.nut077.article.entity.Article;
import com.github.nut077.article.entity.User;
import com.github.nut077.article.exception.NotFoundException;
import com.github.nut077.article.repository.ArticleRepository;
import com.github.nut077.article.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

  private final ArticleRepository articleRepository;
  private final ArticleMapper mapper;
  private final UserService userService;
  private final JwtTokenUtil jwtTokenUtil;

  public List<ArticleDto> findAll() {
    return mapper.mapToListDto(articleRepository.findAll());
  }

  public ArticleDto findById(Long id) {
    return mapper.mapToDto(getArticle(id));
  }

  public ArticleDto create(ArticleDto dto, String token) {
    String username = jwtTokenUtil.getUsernameFromToken(token.substring(7));
    User user = userService.findByUsername(username);
    dto.setAuthorId(user.getId());
    return mapper.mapToDto(articleRepository.save(mapper.mapToEntity(dto)));
  }

  public ArticleDto update(Long id, ArticleDto dto) {
    Article article = getArticle(id);
    return mapper.mapToDto(articleRepository.save(mapper.mapToEntity(dto, article)));
  }

  public void delete(Long id) {
    getArticle(id);
    articleRepository.deleteById(id);
  }

  private Article getArticle(Long id) {
    return articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Article id: [" + id + "] is not found"));
  }
}
