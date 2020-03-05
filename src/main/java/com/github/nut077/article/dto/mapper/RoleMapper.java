package com.github.nut077.article.dto.mapper;

import com.github.nut077.article.dto.RoleDto;
import com.github.nut077.article.entity.Role;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RoleMapper extends BaseMapper<Role, RoleDto> {
}
