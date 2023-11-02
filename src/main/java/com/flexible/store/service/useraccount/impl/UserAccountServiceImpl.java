package com.flexible.store.service.useraccount.impl;

import com.flexible.store.dto.useraccount.UserAccountDto;
import com.flexible.store.entity.UserAccountEntity;
import com.flexible.store.service.crud.impl.CrudServiceImpl;
import com.flexible.store.service.useraccount.UserAccountService;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl extends CrudServiceImpl<UserAccountEntity, UserAccountDto> implements UserAccountService {
}
