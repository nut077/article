package com.github.nut077.article.service;

import com.github.nut077.article.dto.ArticleCreateDto;
import com.github.nut077.article.dto.ArticleDto;
import com.github.nut077.article.dto.mapper.ArticleCreateMapper;
import com.github.nut077.article.dto.mapper.ArticleMapper;
import com.github.nut077.article.entity.Article;
import com.github.nut077.article.entity.User;
import com.github.nut077.article.exception.NotFoundException;
import com.github.nut077.article.repository.ArticleRepository;
import com.github.nut077.article.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

  private final ArticleRepository articleRepository;
  private final ArticleMapper articleMapper;
  private final ArticleCreateMapper articleCreateMapper;
  private final UserService userService;
  private final JwtTokenUtil jwtTokenUtil;

  public List<ArticleDto> findAll() {
    return articleMapper.mapToListDto(articleRepository.findAll());
  }

  public ArticleDto findById(Long id) {
    return articleMapper.mapToDto(getArticle(id));
  }

  public ArticleDto create(ArticleCreateDto dto, String token) {
    String username = jwtTokenUtil.getUsernameFromToken(token.substring(7));
    User user = userService.findByUsername(username);
    dto.setAuthorId(user.getId());
    return articleMapper.mapToDto(articleRepository.save(articleCreateMapper.mapToEntity(dto)));
  }

  @Transactional
  public ArticleDto update(Long id, ArticleCreateDto dto) {
    Article article = getArticle(id);
    return articleMapper.mapToDto(articleRepository.save(articleCreateMapper.mapToEntity(dto, article)));
  }

  public void delete(Long id) {
    getArticle(id);
    articleRepository.deleteById(id);
  }

  private Article getArticle(Long id) {
    return articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Article id: [" + id + "] is not found"));
  }
}
