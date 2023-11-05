package com.flexible.store.entity;

import com.flexible.store.entity.abstraction.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "country")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CountryEntity extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
}
