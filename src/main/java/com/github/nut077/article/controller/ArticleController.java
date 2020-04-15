package com.github.nut077.article.controller;

import com.github.nut077.article.dto.ArticleCreateDto;
import com.github.nut077.article.dto.ArticleDto;
import com.github.nut077.article.service.ArticleService;
import com.github.nut077.article.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ArticleController extends BaseController {

  private final ArticleService articleService;
  private final JsonUtil jsonUtil;

  @GetMapping("/articles")
  public ResponseEntity<List<ArticleDto>> findAll() {
    return ok(articleService.findAll());
  }

  @GetMapping("/articles/{id}")
  public ResponseEntity<ArticleDto> findById(@PathVariable Long id) {
    log.info("ArticleController::findById id -->> [{}]", id);
    return ResponseEntity.ok(articleService.findById(id));
  }

  @PostMapping("/articles")
  public ResponseEntity<ArticleDto> create(@RequestBody ArticleCreateDto dto, @RequestHeader("authorization") String token) {
    log.info("ArticleController::create -->> {}", jsonUtil.toJson(dto));
    return ResponseEntity.ok(articleService.create(dto, token));
  }

  @PutMapping("/articles/{id}")
  public ResponseEntity<ArticleDto> update(@PathVariable Long id, @RequestBody ArticleCreateDto dto) {
    log.info("ArticleController::update id -->> [{}]", id);
    log.info("ArticleController::update req -->> {}", jsonUtil.toJson(dto));
    return ResponseEntity.ok(articleService.update(id, dto));
  }

  @DeleteMapping("/articles/{id}")
  public void delete(@PathVariable Long id) {
    log.info("ArticleController::delete -->> [{}]", id);
    articleService.delete(id);
  }
}
