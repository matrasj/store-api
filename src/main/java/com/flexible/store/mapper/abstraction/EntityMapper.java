package com.flexible.store.mapper.abstraction;

import java.util.List;

public interface EntityMapper<TEntity, TDto>  {
    TEntity toEntity(TDto dto);
    TDto toDto(TEntity entity);
    List<TEntity> toEntityList(List<TDto> dtoList);
    List<TDto> toDtoList(List<TEntity> toEntityList);
}
