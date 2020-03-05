package com.github.nut077.article.controller;

import com.github.nut077.article.dto.UserDto;
import com.github.nut077.article.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController extends CommonController {

  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<UserDto>> findAll() {
    return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<UserDto> findById(@PathVariable Long id) {
    log.info("UserController::getUserById id -->> [{}]", id);
    return ResponseEntity.ok(userService.findById(id));
  }

  @PostMapping("/users")
  public ResponseEntity<UserDto> create(UserDto dto) {
    return ResponseEntity.ok(userService.create(dto));
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable Long id) {
    log.info("UserController::deleteUser id -->> [{}]", id);
    userService.delete(id);
  }
}
