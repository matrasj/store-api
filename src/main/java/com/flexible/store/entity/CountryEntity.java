package com.flexible.store.entity;

import com.flexible.store.entity.abstraction.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Entity
@Table(name = "country")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CountryEntity extends BaseEntity implements Serializable {
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
}
