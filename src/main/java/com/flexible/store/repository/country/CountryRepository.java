package com.flexible.store.repository.country;

import com.flexible.store.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
    @Query("SELECT ce FROM CountryEntity ce WHERE (ce.removed <> TRUE) AND (ce.name = :name)")
    Optional<CountryEntity> findNotRemovedByName(@Param("name") String name);

    @Query("SELECT ce FROM CountryEntity ce WHERE (ce.removed <> TRUE) AND (ce.code = :code)")
    Optional<CountryEntity> findNotRemovedByCode(@Param("code") String code);
}
