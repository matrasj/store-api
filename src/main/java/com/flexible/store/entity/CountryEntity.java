package com.flexible.store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "country")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CountryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;
    @Column(name = "last_edit_date")
    @UpdateTimestamp
    private LocalDateTime lastEditDate;
    @Column(name = "removed")
    private Boolean removed;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
}
