package com.github.nut077.article.dto.mapper;

import com.github.nut077.article.dto.UserDto;
import com.github.nut077.article.entity.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper extends BaseMapper<User, UserDto> {
}
