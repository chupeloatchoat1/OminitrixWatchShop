package com.example.ominitrixw.controller;

import com.example.ominitrixw.dto.UserDTO;
import com.example.ominitrixw.entities.User;
import com.example.ominitrixw.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ominitrix/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @PostMapping(value = "/add")
    public UserDTO addUser(@RequestBody User newUser){
        UserDTO userDTO = null;
        try {
            userDTO = userService.addUser(newUser);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userDTO;
    }
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable String id){
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @PutMapping(value = "/update")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean updateUser(@RequestParam("email") String email,
                              @RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName,
                              @RequestParam("phone") String phone,
                              @RequestParam("address") String address,
                              @RequestParam("userID") String userID,
                              @RequestParam("fileName") MultipartFile multipartFile) throws IOException {
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images/avatar");
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path file = CURRENT_FOLDER.resolve(staticPath)
                .resolve(imagePath).resolve(multipartFile.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(multipartFile.getBytes());
        }
        User user = new User();
        user.setUserID(userID);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setPhone(phone);
        user.setAvatar(imagePath.resolve(multipartFile.getOriginalFilename()).toString());
        try {
            System.out.println(user.getEmail());
            userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @GetMapping(value = {"/findByID/{id}"})
    public UserDTO findUserByID(@PathVariable String id){
        UserDTO userDTO = new UserDTO();
        try {
            userDTO = userService.findUserByID(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userDTO;
    }
    @GetMapping(value = {"/findByEmail/{email}"})
    public UserDTO findUserByEmail(@PathVariable String email){
        UserDTO userDTO = new UserDTO();
        try {
            userDTO = userService.findUserByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userDTO;
    }
    @GetMapping(value = {""})
    public List<UserDTO> findAllUser(){
        List<UserDTO> userDTOList = new ArrayList<>();
        try {
            userDTOList = userService.findAllUser();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return userDTOList;
    }
    @PutMapping(value = "/change-OTP")
    public boolean updateOTP(@Param("email") String email, @Param("OTP") String OTP, @Param("expire") Timestamp OTPexpire){
        try{
            userService.updateOTP(email, OTP,OTPexpire);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @GetMapping (value="/sendEmail")
    public boolean sendEmail(@Param("email") String email){
        try{
            userService.sendEmail(email);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
