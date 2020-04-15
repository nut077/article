package com.github.nut077.article.service;

import com.github.nut077.article.dto.CommentDto;
import com.github.nut077.article.dto.mapper.CommentMapper;
import com.github.nut077.article.entity.Comment;
import com.github.nut077.article.entity.User;
import com.github.nut077.article.exception.NotFoundException;
import com.github.nut077.article.repository.CommentRepository;
import com.github.nut077.article.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final CommentMapper mapper;
  private final UserService userService;
  private final JwtTokenUtil jwtTokenUtil;

  public CommentDto create(CommentDto dto, String token) {
    String username = jwtTokenUtil.getUsernameFromToken(token.substring(7));
    User user = userService.findByUsername(username);
    Comment comment = mapper.mapToEntity(dto);
    comment.setUser(user);
    return mapper.mapToDto(commentRepository.save(comment));
  }

  public CommentDto update(Long id, CommentDto dto) {
    return mapper.mapToDto(commentRepository.save(mapper.mapToEntity(dto, getComment(id))));
  }

  public void delete(Long id) {
    getComment(id);
    commentRepository.deleteById(id);
  }

  private Comment getComment(Long id) {
    return commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment id: [" + id + "] not found"));
  }
}
