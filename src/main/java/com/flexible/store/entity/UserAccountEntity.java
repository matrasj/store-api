package com.flexible.store.entity;

import com.flexible.store.entity.type.Permission;
import com.flexible.store.entity.type.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "user_account")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserAccountEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;
    @Column(name = "last_edit_date")
    @UpdateTimestamp
    private LocalDateTime lastEditDate;
    @Column(name = "removed")
    private Boolean removed;
    @Column(name = "email")
    private String email;
    @Column(name = "help_email")
    private String helpEmail;
    @Column(name = "password")
    private String password;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "username")
    private String username;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<Permission> permissions = this.role.getPermissions();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role));
        permissions.forEach((permission -> authorities.add(new SimpleGrantedAuthority(permission.name()))));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
