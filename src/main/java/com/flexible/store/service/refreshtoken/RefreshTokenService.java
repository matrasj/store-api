package com.flexible.store.service.refreshtoken;

import com.flexible.store.dto.refreshtoken.RefreshTokenDto;
import com.flexible.store.entity.RefreshTokenEntity;
import com.flexible.store.service.crud.CrudService;

import java.util.Optional;

public interface RefreshTokenService extends CrudService<RefreshTokenEntity, RefreshTokenDto> {
    Optional<RefreshTokenEntity> findByToken(String refreshToken);
    RefreshTokenEntity verifyExpiration(RefreshTokenEntity token);
}
