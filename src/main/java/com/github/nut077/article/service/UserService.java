package com.github.nut077.article.service;

import com.github.nut077.article.dto.UserDto;
import com.github.nut077.article.dto.mapper.UserMapper;
import com.github.nut077.article.entity.Role;
import com.github.nut077.article.entity.User;
import com.github.nut077.article.exception.NotFoundException;
import com.github.nut077.article.repository.RoleRepository;
import com.github.nut077.article.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserMapper mapper;

  public List<UserDto> findAll() {
    return mapper.mapToListDto(userRepository.findAll());
  }

  public UserDto findById(Long id) {
    return mapper.mapToDto(getUser(id));
  }

  public UserDto create(UserDto dto) {
    User user = mapper.mapToEntity(dto);
    user.setPassword(passwordEncoder.encode(dto.getPassword()));
    Set<Role> roles = new HashSet<>();
    roles.add(roleRepository.findByName("ADMIN"));
    user.setRoles(roles);
    return mapper.mapToDto(userRepository.save(user));
  }

  public UserDto update(Long id, UserDto dto) {
    return mapper.mapToDto(userRepository.save(mapper.mapToEntity(dto, getUser(id))));
  }

  public void delete(Long id) {
    getUser(id);
    userRepository.deleteById(id);
  }

  private User getUser(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User id: [" + id + "] not found"));
  }

  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("Username username: [" + username + "] not found");
    }
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    for (Role role : user.getRoles()) {
      grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
  }
}
