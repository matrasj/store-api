package com.flexible.store.dto.abstraction;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseDto {
    private Long id;
    private LocalDateTime createDate;
    private LocalDateTime lastEditDate;
    private Boolean removed;
}
