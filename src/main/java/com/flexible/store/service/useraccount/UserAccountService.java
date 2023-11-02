package com.flexible.store.service.useraccount;

import com.flexible.store.dto.useraccount.UserAccountDto;
import com.flexible.store.entity.UserAccountEntity;
import com.flexible.store.service.crud.CrudService;

import java.util.Optional;

public interface UserAccountService extends CrudService<UserAccountEntity, UserAccountDto> {
    Optional<UserAccountEntity> findByEmail(String email);
}
