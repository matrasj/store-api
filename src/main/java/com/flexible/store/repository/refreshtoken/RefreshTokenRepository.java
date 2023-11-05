package com.flexible.store.repository.refreshtoken;

import com.flexible.store.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    @Query(value = "SELECT rt FROM RefreshTokenEntity rt WHERE (rt.token = :token)")
    Optional<RefreshTokenEntity> findByToken(@Param("token") String token);
}
