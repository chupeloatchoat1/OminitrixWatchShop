package com.example.ominitrixw.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String userID;
    private String avatar;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String OTP;
    private Timestamp OTPexpire;
}
