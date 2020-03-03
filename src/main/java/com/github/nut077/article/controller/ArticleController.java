package com.github.nut077.article.controller;

import com.github.nut077.article.dto.ArticleDto;
import com.github.nut077.article.service.ArticleService;
import com.github.nut077.article.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.github.nut077.article.dto.response.SuccessResponse.builder;
import static org.springframework.http.ResponseEntity.ok;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ArticleController extends CommonController {

  private final ArticleService articleService;
  private final JsonUtil jsonUtil;

  @GetMapping("/articles")
  public ResponseEntity findAll() {
    return ok(builder(articleService.findAll()));
  }

  @GetMapping("/articles/{id}")
  public ResponseEntity findById(@PathVariable Long id) {
    log.info("ArticleController::findById -->> {}", id);
    return ok(builder(articleService.findById(id)));
  }

  @PostMapping("/articles")
  public ResponseEntity create(ArticleDto dto) {
    log.info("ArticleController::create -->> {}", jsonUtil.toJson(dto));
    return ok(builder(articleService.create(dto)));
  }

  @PutMapping("/articles/{id}")
  public ResponseEntity update(@PathVariable Long id, ArticleDto dto) {
    log.info("ArticleController::update id -->> {}", id);
    log.info("ArticleController::update json -->> {}", jsonUtil.toJson(dto));
    return ok(builder(articleService.update(id, dto)));
  }

  @DeleteMapping("/articles/{id}")
  public void delete(@PathVariable Long id) {
    log.info("ArticleController::delete -->> {}", id);
    articleService.delete(id);
  }
}
