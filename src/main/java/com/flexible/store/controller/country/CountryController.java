package com.flexible.store.controller.country;

import com.flexible.store.controller.crud.CrudController;
import com.flexible.store.dto.country.CountryDto;
import com.flexible.store.entity.CountryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.flexible.store.entity.type.Role.*;

@RestController
@RequestMapping("/api/country")
@RequiredArgsConstructor
public class CountryController extends CrudController<CountryEntity, CountryDto> {
    @Override
    public boolean hasPermissionToSave() {
        return super.authoritiesResolver.hasOneOfRolesAndIsActive(List.of(ADMIN, MODERATOR, EMPLOYEE));
    }

    @Override
    public boolean hasPermissionToRead() {
        return super.authoritiesResolver.hasOneOfRolesAndIsActive(List.of(ADMIN, MODERATOR, EMPLOYEE, CUSTOMER));
    }

    @Override
    public boolean hasPermissionToUpdate() {
        return super.authoritiesResolver.hasOneOfRolesAndIsActive(List.of(ADMIN, MODERATOR, EMPLOYEE));
    }

    @Override
    public boolean hasPermissionToDelete() {
        return super.authoritiesResolver.hasOneOfRolesAndIsActive(List.of(ADMIN, MODERATOR, EMPLOYEE));
    }
}
