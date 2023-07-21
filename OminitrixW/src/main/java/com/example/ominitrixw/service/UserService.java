package com.example.ominitrixw.service;

import com.example.ominitrixw.dto.UserDTO;
import com.example.ominitrixw.entities.User;
import com.example.ominitrixw.entities.Watch;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface UserService {
    public UserDTO addUser(User user);
    public boolean deleteUser(String id);
    public boolean updateUser(User newUser);
    public UserDTO findUserByID(String id);
    public UserDTO findUserByEmail(String email);
    public List<UserDTO> findAllUser();
    public boolean updateOTP(String email, String OTP, Timestamp hetHanOTP);
    public boolean sendEmail(String email);
}
