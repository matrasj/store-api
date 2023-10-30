package com.flexible.store.mapper.useraccount;

import com.flexible.store.dto.useraccount.UserAccountDto;
import com.flexible.store.entity.UserAccountEntity;
import com.flexible.store.mapper.abstraction.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class UserAccountMapper implements EntityMapper<UserAccountEntity, UserAccountDto> {
}
