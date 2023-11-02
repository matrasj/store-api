package com.flexible.store.service.useraccount.impl;

import com.flexible.store.dto.useraccount.ConfirmationTokenDto;
import com.flexible.store.entity.ConfirmationTokenEntity;
import com.flexible.store.service.crud.impl.CrudServiceImpl;
import com.flexible.store.service.useraccount.ConfirmationTokenService;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenServiceImpl extends CrudServiceImpl<ConfirmationTokenEntity, ConfirmationTokenDto> implements ConfirmationTokenService {
}
