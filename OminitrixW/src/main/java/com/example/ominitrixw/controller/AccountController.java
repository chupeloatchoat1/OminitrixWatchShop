package com.example.ominitrixw.controller;

import com.example.ominitrixw.dto.AccountDTO;
import com.example.ominitrixw.entities.Account;
import com.example.ominitrixw.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ominitrix/account")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;

    @PostMapping(value = "/add")
    public AccountDTO addAccount(@RequestBody AccountDTO accountDTO) {
        try {
            System.out.println(accountDTO.getUserName());
            accountDTO = accountService.addAccount(accountDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return accountDTO;
    }


    @GetMapping("/{id}")
    public AccountDTO findAccountByID(@PathVariable String id) {
        AccountDTO accountDTO = new AccountDTO();
        try {
            accountDTO = accountService.findAccountByID(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return accountDTO;
    }

    @GetMapping(value = {""})
    public List<AccountDTO> findAllAccount() {
        List<AccountDTO> accountDTOList = new ArrayList<>();
        try {
            accountDTOList = accountService.findAllAccount();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return accountDTOList;
    }



    @DeleteMapping("/{id}")
    public boolean deleteAccount(@PathVariable String id) {
        try{
            System.out.println(id);
            accountService.deleteAccount(id);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @PutMapping(value = "/changePass")
    public boolean changePassword(@Param("id") String id, @Param("newPassword")String newPassword) {
        try{
            accountService.changePassword(id, newPassword);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //public boolean checkOTP()

}
