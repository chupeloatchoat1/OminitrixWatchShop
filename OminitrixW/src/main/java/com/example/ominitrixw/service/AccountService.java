package com.example.ominitrixw.service;

import com.example.ominitrixw.dto.AccountDTO;
import com.example.ominitrixw.entities.Account;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface AccountService {
    public AccountDTO addAccount(AccountDTO accountDTO);
    public AccountDTO findAccountByID(String id);
    public List<AccountDTO> findAllAccount();
    public boolean deleteAccount(String id);
    public boolean changePassword(String id, String newPassword);
}
