package com.flexible.store.service.crud;

import com.flexible.store.dto.abstraction.BaseDto;
import com.flexible.store.entity.abstraction.BaseEntity;

import java.util.List;

public interface CrudService<TEntity extends BaseEntity, TDto extends BaseDto> {
    TEntity save(TDto dto);
    List<TEntity> saveAll(List<TDto> dtoList);
    TEntity getById(Long id);
    List<TEntity> getAll();
    void delete(Long id);

}
