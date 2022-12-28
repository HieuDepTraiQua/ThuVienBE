package com.quanly.thuvien.service;

import com.quanly.thuvien.dto.AccountDTO;
import com.quanly.thuvien.dto.BookDTO;
import com.quanly.thuvien.model.*;
import com.quanly.thuvien.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountModel create(AccountModel account) {
        Optional<AccountModel> opAccount  = accountRepository.findByUsername(account.getUsername());
        if (opAccount.isPresent()) {
            throw new EntityNotFoundException("Username is exist!");
        }
        Optional<AccountModel> opPhone  = accountRepository.findByPhoneNumber(account.getPhoneNumber());
        if (opPhone.isPresent()) {
            throw new EntityNotFoundException("Phone Number is exist!");
        }
        Optional<RoleModel> opRole = roleRepository.findById(account.getRoleId());
        if (!opRole.isPresent()) {
            throw new EntityNotFoundException("Role Id not found!");
        }
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        account.setUsername(account.getUsername().trim().toLowerCase());
        return accountRepository.save(account);
    };

    public void deleteById(String id) {
        Optional<AccountModel> optional = accountRepository.findById(id);
        if (!optional.isPresent()) {
            throw new EntityNotFoundException("Id not found!");
        } else {
            accountRepository.deleteById(id);
        }
    };

    public AccountModel update(AccountModel account, String id) {
        Optional<AccountModel> optional = accountRepository.findById(id);
        if (!optional.isPresent()) {
            throw new EntityNotFoundException("Id not found!");
        } else {
            account.setId(id);
            if (account.getFullname() == null || account.getFullname().isEmpty()) {
                account.setFullname(optional.get().getFullname());
            }
            if (account.getUsername() == null || account.getUsername().isEmpty()) {
                account.setUsername(optional.get().getUsername());
            }
            if (account.getPassword() == null || account.getPassword().isEmpty()) {
                account.setPassword(optional.get().getPassword());
            }
            if (account.getPhoneNumber() == null || account.getPhoneNumber().isEmpty()) {
                account.setPhoneNumber(optional.get().getPhoneNumber());
            }
            if (account.getAddress() == null || account.getAddress().isEmpty()) {
                account.setAddress(optional.get().getAddress());
            }
            if (account.getRoleId() == null || account.getRoleId().isEmpty()) {
                account.setRoleId(optional.get().getRoleId());
            }
            return accountRepository.save(account);
        }
    };

    public AccountDTO findAccountById(String id){
        return getAccountDto(accountRepository.findById(id).get());
    }
    public AccountDTO getAccountDto(AccountModel account) {
        AccountDTO dto = new AccountDTO();
        Optional<RoleModel> opRole = roleRepository.findById(account.getRoleId());
        if (!opRole.isPresent()) {
            throw new EntityNotFoundException("Role Id not found!");
        }
        dto.setRoleTitle(opRole.get().getTitle());
        BeanUtils.copyProperties(account, dto);
        return dto;
    };
    public List<AccountDTO> getListAccountDto(List<AccountModel> list) {
        List<AccountDTO> listDto = new ArrayList<>();
        for (AccountModel account : list) {
            AccountDTO dto = getAccountDto(account);
            listDto.add(dto);
        }
        return listDto;
    };

    public Page<AccountDTO> get(Pageable pageable) {
        List<AccountModel> list = accountRepository.findAll();
        return new PageImpl<>(getListAccountDto(list), pageable, list.size());
    };

    public List<RoleModel> getAllRole() {
        return roleRepository.findAll();
    };

//    public AccountModel changePassword(AccountModel account, String id) {
//        Optional<AccountModel> optional = accountRepository.findById(id);
//        if (account.getOldPassword() == null
//                || !bCryptPasswordEncoder.matches(account.getOldPassword(), optional.get().getPassword())) {
//            throw new EntityNotFoundException("Old pasword is not correct");
//        }
//        if (!optional.isPresent()) {
//            throw new EntityNotFoundException("Account Id not found!");
//        }
//        account.setId(id);
//        if (account.getName() == null || account.getName().isEmpty()) {
//            account.setName(optional.get().getName());
//        }
//        if (account.getUsername() == null || account.getUsername().isEmpty()) {
//            account.setUsername(optional.get().getUsername());
//        }
//        if (account.getAddress() == null || account.getAddress().isEmpty()) {
//            account.setAddress(optional.get().getAddress());
//        }
//        if (account.getAvatar() == null || account.getAvatar().isEmpty()) {
//            account.setAvatar(optional.get().getAvatar());
//        }
//        if (account.getPhone() == null || account.getPhone().isEmpty()) {
//            account.setPhone(optional.get().getPhone());
//        }
//        if (account.getEmail() == null || account.getEmail().isEmpty()) {
//            account.setEmail(optional.get().getEmail());
//        }
//        if (account.getRoleId() == null || account.getRoleId().isEmpty()) {
//            account.setRoleId(optional.get().getRoleId());
//        }
//        if (account.getPassword() == null || account.getPassword().isEmpty()) {
//            account.setPassword(optional.get().getPassword());
//        } else {
//            account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
//        }
//        return accountRepository.save(account);
//
//    }

}
