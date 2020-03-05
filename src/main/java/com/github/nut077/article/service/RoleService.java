package com.github.nut077.article.service;

import com.github.nut077.article.dto.RoleDto;
import com.github.nut077.article.dto.mapper.RoleMapper;
import com.github.nut077.article.entity.Role;
import com.github.nut077.article.exception.NotFoundException;
import com.github.nut077.article.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

  private final RoleRepository roleRepository;
  private final RoleMapper mapper;

  public List<RoleDto> findAll() {
    return mapper.mapToListDto(roleRepository.findAll());
  }

  public RoleDto create(RoleDto dto) {
    return mapper.mapToDto(roleRepository.save(mapper.mapToEntity(dto)));
  }

  public RoleDto update(Long id, RoleDto dto) {
    return mapper.mapToDto(roleRepository.save(mapper.mapToEntity(dto, getRole(id))));
  }

  public void delete(Long id) {
    getRole(id);
    roleRepository.deleteById(id);
  }

  private Role getRole(Long id) {
    return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role id: [" + id + "] not found"));
  }
}
