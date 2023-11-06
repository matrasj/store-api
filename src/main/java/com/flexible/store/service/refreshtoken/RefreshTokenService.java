package com.flexible.store.service.refreshtoken;

import com.flexible.store.entity.RefreshTokenEntity;

public interface RefreshTokenService {
    RefreshTokenEntity verifyExpiration(RefreshTokenEntity token);
}
