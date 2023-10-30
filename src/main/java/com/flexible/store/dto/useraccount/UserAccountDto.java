package com.flexible.store.dto.useraccount;

import com.flexible.store.dto.abstraction.BaseDto;
import com.flexible.store.entity.type.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAccountDto extends BaseDto {
    private String email;
    private String helpEmail;
    private String password;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String username;
    private Boolean active;
    private Role role;
}
