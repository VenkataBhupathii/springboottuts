package com.mycompany.propertymanagement.controllers;

import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
   private UserService userService;

    
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {

        userDTO=userService.register(userDTO);

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@Valid @RequestBody UserDTO userDTO) {
        UserDTO loggedInUser = userService.login(userDTO.getOwnerEmail(), userDTO.getPassword());

        if (loggedInUser != null) {
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }


}
