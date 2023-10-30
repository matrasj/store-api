package com.flexible.store.service.crud.impl;
import com.flexible.store.dto.abstraction.BaseDto;
import com.flexible.store.entity.abstraction.BaseEntity;
import com.flexible.store.exception.shared.EntityNotFoundException;
import com.flexible.store.mapper.abstraction.EntityMapper;
import com.flexible.store.service.crud.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.function.Consumer;

public abstract class CrudServiceImpl<TEntity extends BaseEntity, TDto extends BaseDto> implements CrudService<TEntity, TDto> {
    @Autowired
    protected JpaRepository<TEntity, Long> repository;
    @Autowired
    protected EntityMapper<TEntity, TDto> mapper;

    @Override
    @Transactional
    public TEntity save(TDto dto) {
        this.setNotRemoved.accept(dto);
        return this.repository.save(this.mapper.toEntity(dto));
    }

    @Override
    @Transactional
    public List<TEntity> saveAll(List<TDto> dtoList) {
        dtoList.forEach(this.setNotRemoved);
        return this.repository.saveAll(this.mapper.toEntityList(dtoList));
    }

    @Override
    public TEntity getById(Long id) {
        return this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<TEntity> getAll() {
        return this.repository.findAll();
    }

    @Override
    public void delete(Long id) {
        TEntity byId = this.getById(id);
        byId.setRemoved(true);
        this.repository.save(byId);
    }

    private final Consumer<TDto> setNotRemoved = (dto) -> dto.setRemoved(Boolean.FALSE);
}
