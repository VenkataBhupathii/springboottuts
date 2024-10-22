package com.mycompany.propertymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Table(name="ADDRESS_TABLE")
@Getter
@Setter
public class AddressEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String houseNo;
    private String street;
    private String city;
    private String postalCode;
    private String country;

    @OneToOne
    @JoinColumn(name="USER_ID",nullable = false)
    private UserEntity userEntity;
}
