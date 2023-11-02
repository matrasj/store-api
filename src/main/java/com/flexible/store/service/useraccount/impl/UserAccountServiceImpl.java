package com.flexible.store.service.useraccount.impl;

import com.flexible.store.dto.useraccount.UserAccountDto;
import com.flexible.store.entity.UserAccountEntity;
import com.flexible.store.repository.useraccount.UserAccountRepository;
import com.flexible.store.service.crud.impl.CrudServiceImpl;
import com.flexible.store.service.useraccount.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl extends CrudServiceImpl<UserAccountEntity, UserAccountDto> implements UserAccountService {
    private final UserAccountRepository userAccountRepository;

    @Override
    public Optional<UserAccountEntity> findByEmail(String email) {
        return this.userAccountRepository.findByEmail(email);
    }
}
