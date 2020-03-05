package com.github.nut077.article.controller;

import com.github.nut077.article.dto.RoleDto;
import com.github.nut077.article.service.RoleService;
import com.github.nut077.article.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class RoleController extends CommonController {

  private final RoleService roleService;
  private final JsonUtil jsonUtil;

  public ResponseEntity<List<RoleDto>> findAll() {
    return ResponseEntity.ok(roleService.findAll());
  }

  public ResponseEntity<RoleDto> create(RoleDto dto) {
    log.info("RoleController::create req -->> {}", jsonUtil.toJson(dto));
    return ResponseEntity.ok(roleService.create(dto));
  }

  public ResponseEntity<RoleDto> update(Long id, RoleDto dto) {
    log.info("RoleController::update id -->> [{}]", id);
    log.info("RoleController::update req -->> {}", jsonUtil.toJson(dto));
    return ResponseEntity.ok(roleService.update(id, dto));
  }

  public void delete(Long id) {
    log.info("RoleController::update id -->> [{}]", id);
    roleService.delete(id);
  }
}
