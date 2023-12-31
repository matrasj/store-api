package com.flexible.store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserAddressEntity {
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
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "company")
    private String company;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "city")
    private String city;
    @Column(name = "street_and_home_number")
    private String streetAndHomeNumber;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "country_id")
    private Long countryId;
    @Column(name = "user_account_id")
    private Long userAccountId;
}
