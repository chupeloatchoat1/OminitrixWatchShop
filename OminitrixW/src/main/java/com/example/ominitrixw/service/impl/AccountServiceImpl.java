package com.example.ominitrixw.service.impl;

import com.example.ominitrixw.dto.AccountDTO;
import com.example.ominitrixw.entities.Account;
import com.example.ominitrixw.entities.Bill;
import com.example.ominitrixw.entities.User;
import com.example.ominitrixw.repository.AccountRepository;
import com.example.ominitrixw.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Component
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ModelMapper modelMapper;

    private AccountDTO convertEntityToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO = modelMapper.map(account, AccountDTO.class);
        accountDTO.setUser_id(accountDTO.getUserName());
        return accountDTO;
    }
    private Account convertDTOToEntity(AccountDTO accountDTO) {
        Account account = new Account();
        account.setUserName(accountDTO.getUserName());
        account.setRole(accountDTO.isRole());
        account.setPassword(accountDTO.getPassword());
        account.setUser(new User(accountDTO.getUserName()));
        return account;
    }

    @Override
    public AccountDTO addAccount(AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountDTO.getUserName()).orElse(null);
        if (account == null) {
            account = convertDTOToEntity(accountDTO);
            System.out.println(account);
            accountRepository.save(account);
            return accountDTO;
        } else
            return null;
    }

    @Override
    public AccountDTO findAccountByID(String id) {
        Account account= accountRepository.findById(id).orElse(null);
        //System.out.println(account.getUser().getUserID());
        if (account == null)
            return null;
        else {
            return convertEntityToDTO(account);
        }
    }

    @Override
    public List<AccountDTO> findAllAccount() {
        return accountRepository.findAll().stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }



    @Override
    public boolean deleteAccount(String id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (Objects.nonNull(account)) {
            accountRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean changePassword(String id, String newPassword) {
        Account account = accountRepository.findById(id).orElse(null);
        if (Objects.nonNull(account)) {
            account.setPassword(newPassword);
            accountRepository.save(account);
            return true;
        }
        return false;
    }
}
