package com.flexible.store.mapper.refreshtoken;

import com.flexible.store.dto.refreshtoken.RefreshTokenDto;
import com.flexible.store.entity.RefreshTokenEntity;
import com.flexible.store.mapper.abstraction.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class RefreshTokenMapper implements EntityMapper<RefreshTokenEntity, RefreshTokenDto> {
}
