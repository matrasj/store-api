package com.flexible.store.service.confrimationtoken.impl;

import com.flexible.store.dto.confirmationtoken.ConfirmationTokenDto;
import com.flexible.store.entity.ConfirmationTokenEntity;
import com.flexible.store.service.crud.impl.CrudServiceImpl;
import com.flexible.store.service.confrimationtoken.ConfirmationTokenService;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenServiceImpl extends CrudServiceImpl<ConfirmationTokenEntity, ConfirmationTokenDto> implements ConfirmationTokenService {
}
