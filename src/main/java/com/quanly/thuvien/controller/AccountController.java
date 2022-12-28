package com.quanly.thuvien.controller;

import com.quanly.thuvien.dto.AccountDTO;
import com.quanly.thuvien.dto.BookDTO;
import com.quanly.thuvien.model.AccountModel;
import com.quanly.thuvien.model.AuthorModel;
import com.quanly.thuvien.model.BookModel;
import com.quanly.thuvien.model.RoleModel;
import com.quanly.thuvien.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/thuvien/account")
@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<?> getPagable(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> response = new HashMap<>();
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<AccountDTO> data = accountService.get(paging);
            response.put("data", data.getContent());
            response.put("totalRecord", data.getTotalElements());
            response.put("totalPage", data.getTotalPages());
            response.put("success", true);
            response.put("message", "Ok");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    };

    @PostMapping()
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<?> create(@RequestBody AccountModel account) {
        Map<String, Object> response = new HashMap<>();
        try {
            AccountModel data = accountService.create(account);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    };

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            accountService.deleteById(id);
            response.put("success", true);
            response.put("message", "Delete successfully !");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    };
    @PutMapping("/{id}")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<?> update(@RequestBody AccountModel account, @PathVariable(value = "id") String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            AccountModel data = accountService.update(account, id);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Update successfully !");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    };

    @RequestMapping(value = "/getrole", method = RequestMethod.GET)
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<?> getAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<RoleModel> data = accountService.getAllRole();
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    };

    @GetMapping("/findbyid")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<?> getAccountById(@RequestParam() String id) {
        Map<String, Object> response = new HashMap<>();
        try {
            AccountDTO data = accountService.findAccountById(id);
            response.put("data", data);
            response.put("success", true);
            response.put("message", "Ok");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    };

//    @PutMapping("/change-password/{id}")
//    @CrossOrigin(origins = "*", maxAge = 3600)
//    public ResponseEntity<?> changePassword(@PathVariable(value = "id") String id, @RequestBody AccountModel model) {
//        Map<String, Object> response = new HashMap<>();
//        try {
//            AccountModel data = accountService.changePassword(model, id);
//            response.put("data", data);
//            response.put("success", true);
//            response.put("message", "Update password successfully !");
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            response.put("success", false);
//            response.put("message", e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    };


}
