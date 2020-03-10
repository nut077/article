package com.github.nut077.article.controller;

import com.github.nut077.article.dto.UserDto;
import com.github.nut077.article.entity.JwtResponse;
import com.github.nut077.article.service.UserService;
import com.github.nut077.article.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController extends BaseController {

  private final AuthenticationManager authenticationManager;
  private final JwtTokenUtil jwtTokenUtil;
  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<UserDto> create(@RequestBody UserDto dto) {
    UserDto userDto = userService.create(dto);
    UserDetails userDetails = new User(userDto.getUsername(), userDto.getPassword(), new ArrayList<>());
    String token = jwtTokenUtil.generateToken(userDetails);
    return ResponseEntity.ok().header("Authorization", "Bearer " + token).body(userDto);
  }

  @PostMapping("/authenticate")
  public ResponseEntity<JwtResponse> authenticate(@RequestBody UserDto dto) throws Exception {
    authenticate(dto.getUsername(), dto.getPassword());
    UserDetails userDetails = userService.loadUserByUsername(dto.getUsername());
    String token = jwtTokenUtil.generateToken(userDetails);
    return ResponseEntity.ok().header("Authorization", "Bearer " + token)
      .body(new JwtResponse(token));
  }

  private void authenticate(String username, String password) throws Exception {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }
}
