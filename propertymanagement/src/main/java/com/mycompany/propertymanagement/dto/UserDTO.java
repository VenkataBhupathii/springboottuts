package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long Id;
    private String ownerName;
    @NotNull(message = "Owner Email is mandatory")
    @Size(min=1,max=50,message="Owner Email should be between 1 to 0 characters in length")
    @NotEmpty(message="Email cannot be empty")
    private String ownerEmail;
    private String phone;
    @NotNull(message="Password cannot be null")
    @NotEmpty(message="Password cannot be empty")
    private String password;


    private String houseNo;
    private String street;
    private String city;
    private String postalCode;
    private String country;
}
