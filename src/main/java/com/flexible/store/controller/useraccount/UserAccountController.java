package com.flexible.store.controller.useraccount;

import com.flexible.store.controller.crud.CrudController;
import com.flexible.store.dto.useraccount.UserAccountDto;
import com.flexible.store.entity.UserAccountEntity;
import com.flexible.store.entity.type.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/user-accounts")
@RequiredArgsConstructor
public class UserAccountController extends CrudController<UserAccountEntity, UserAccountDto> {
    @Override
    public boolean hasPermissionToSave() {
        return super.authoritiesResolver.hasOneOfRolesAndIsActive(List.of(Role.values()));
    }

    @Override
    public boolean hasPermissionToRead() {
        return super.authoritiesResolver.hasOneOfRolesAndIsActive(List.of(Role.values()));
    }

    @Override
    public boolean hasPermissionToUpdate() {
        return super.authoritiesResolver.hasOneOfRolesAndIsActive(List.of(Role.values()));
    }

    @Override
    public boolean hasPermissionToDelete() {
        return super.authoritiesResolver.hasOneOfRolesAndIsActive(List.of(Role.values()));
    }
}
