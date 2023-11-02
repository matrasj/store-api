package com.flexible.store.controller.crud;

import com.flexible.store.dto.abstraction.BaseDto;
import com.flexible.store.entity.abstraction.BaseEntity;
import com.flexible.store.exception.shared.NoPermissionToResourceException;
import com.flexible.store.mapper.abstraction.EntityMapper;
import com.flexible.store.service.auth.AuthoritiesResolver;
import com.flexible.store.service.crud.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
public abstract class CrudController<TEntity extends BaseEntity, TDto extends BaseDto> {
    @Autowired
    protected CrudService<TEntity, TDto> service;
    @Autowired
    protected EntityMapper<TEntity, TDto> mapper;
    @Autowired
    protected AuthoritiesResolver authoritiesResolver;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TDto> save(@RequestBody TDto dto) {
        this.checkPermissionForCreate();
        return ResponseEntity.ok(
                this.mapper.toDto(
                        this.service.save(dto)
                )
        );
    }

    @PostMapping(value = "/list")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<List<TDto>> saveList(@RequestBody List<TDto> list) {
        this.checkPermissionForCreate();
        return ResponseEntity.ok(
                this.mapper.toDtoList(
                        this.service.saveAll(list)
                )
        );
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TDto> getById(@PathVariable Long id) {
        this.checkPermissionForRead();
        return ResponseEntity.ok(
                this.mapper.toDto(
                        this.service.getById(id)
                )
        );
    }

    @GetMapping(value = "/list")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TDto>> getList() {
        this.checkPermissionForRead();
        return ResponseEntity.ok(
                this.mapper.toDtoList(
                        this.service.getAll()
                )
        );
    }
    public abstract boolean hasPermissionToSave();
    public abstract boolean hasPermissionToRead();
    public abstract boolean hasPermissionToUpdate();
    public abstract boolean hasPermissionToDelete();
    public void checkPermissionForCreate() {
        if (!this.hasPermissionToSave()) {
            throw new NoPermissionToResourceException();
        }
    }

    public void checkPermissionForRead() {
        if (!this.hasPermissionToRead()) {
            throw new NoPermissionToResourceException();
        }
    }

    public void checkPermissionForUpdate() {
        if (!this.hasPermissionToUpdate()) {
            throw new NoPermissionToResourceException();
        }
    }

    public void checkPermissionForDelete() {
        if (!this.hasPermissionToDelete()) {
            throw new NoPermissionToResourceException();
        }
    }
}
