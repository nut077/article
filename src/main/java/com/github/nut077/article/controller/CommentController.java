package com.github.nut077.article.controller;

import com.github.nut077.article.dto.CommentDto;
import com.github.nut077.article.service.CommentService;
import com.github.nut077.article.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CommentController extends BaseController {

  private final CommentService commentService;
  private final JsonUtil jsonUtil;

  @PostMapping("/comments")
  public ResponseEntity<CommentDto> create(@RequestBody CommentDto dto, @RequestHeader("authorization") String token) {
    log.info("CommentController::create req -->> {}", jsonUtil.toJson(dto));
    return ResponseEntity.ok(commentService.create(dto, token));
  }
}
