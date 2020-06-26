package com.onlinestore.store.controler;

import com.onlinestore.store.model.UserAccount;
import com.onlinestore.store.service.UserAccountService;
import com.onlinestore.store.to.AddMoney;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping
    public ResponseEntity<Integer> createNewUserAccount(@RequestBody UserAccount userAccount) {
        Integer userId = userAccountService.createNewUser(userAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserAccount> getUserAccount(@PathVariable Integer id) {
        Optional<UserAccount> optionalUserAccount = userAccountService.getUserAccount(id);
        if (optionalUserAccount.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalUserAccount.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<List<UserAccount>> getAllUsersAccounts() {
        List<UserAccount> userAccounts = userAccountService.getAllUserAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(userAccounts);
    }

    @PutMapping
    public ResponseEntity<Integer> updateUserAccount(@RequestBody UserAccount newUserAccount, @PathVariable Integer id) {
        userAccountService.updateUser(newUserAccount);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteUserAccount(@PathVariable Integer id) {
        userAccountService.removeUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<Integer> addMoney(@RequestBody AddMoney credit) {
        userAccountService.addMoney(credit);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
