package com.onlinestore.store.controler;

import com.onlinestore.store.model.UserAccount;
import com.onlinestore.store.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping()
    public ResponseEntity<Integer> createNewUserAccount(@RequestBody UserAccount userAccount){
       Integer userId = userAccountService.createNewUser(userAccount);
       return ResponseEntity.status(HttpStatus.CREATED).body(userId);
    }
}
