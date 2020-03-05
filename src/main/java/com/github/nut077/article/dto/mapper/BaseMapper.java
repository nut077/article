package com.github.nut077.article.dto.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.List;

public interface BaseMapper<E, D> {

  D mapToDto(E entity);
  List<D> mapToListDto(Collection<E> entity);
  @InheritInverseConfiguration
  E mapToEntity(D dto);
  @InheritInverseConfiguration
  E mapToEntity(D dto, @MappingTarget E entity);
}
