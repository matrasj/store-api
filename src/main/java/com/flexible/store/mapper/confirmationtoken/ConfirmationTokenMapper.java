package com.flexible.store.mapper.confirmationtoken;

import com.flexible.store.dto.confirmationtoken.ConfirmationTokenDto;
import com.flexible.store.entity.ConfirmationTokenEntity;
import com.flexible.store.mapper.abstraction.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ConfirmationTokenMapper implements EntityMapper<ConfirmationTokenEntity, ConfirmationTokenDto> {
}
