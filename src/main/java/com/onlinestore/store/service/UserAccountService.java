package com.onlinestore.store.service;

import com.onlinestore.store.dao.BasketEntity;
import com.onlinestore.store.dao.UserAccountEntity;
import com.onlinestore.store.model.UserAccount;
import com.onlinestore.store.repository.UserAccountRepository;
import com.onlinestore.store.to.AddMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public List<UserAccount> getAllUserAccounts() {
        Iterable<UserAccountEntity> userAccountEntityList = userAccountRepository.findAll();
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccountEntityList.forEach(userAccountEntity -> {
            UserAccount userAccount = new UserAccount();
            userAccount.setId(userAccountEntity.getId());
            userAccount.setLastName(userAccountEntity.getLastName());
            userAccount.setFirstName(userAccountEntity.getFirstName());
            userAccount.setCredit(userAccount.getCredit());
            userAccount.setMiddleName(userAccountEntity.getMiddleName());
            userAccounts.add(userAccount);
        });
        return userAccounts;
    }

    public Optional<UserAccount> getUserAccount(int userId) {
        UserAccount userAccount = new UserAccount();
        Optional<UserAccountEntity> optionalUser = userAccountRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserAccountEntity userAccountEntity = optionalUser.get();
            userAccount.setCredit(userAccountEntity.getCredit());
            userAccount.setLastName(userAccountEntity.getLastName());
            userAccount.setMiddleName(userAccountEntity.getMiddleName());
            userAccount.setFirstName(userAccountEntity.getFirstName());
            return Optional.of(userAccount);
        }
        return Optional.empty();
    }

    public void updateUser(UserAccount userAccount) {
        Optional<UserAccountEntity> optionalUser = userAccountRepository.findById(userAccount.getId());
        if (optionalUser.isPresent()) {
            UserAccountEntity userAccountEntity = optionalUser.get();
            userAccountEntity.setCredit(userAccount.getCredit());
            userAccountEntity.setLastName(userAccount.getLastName());
            userAccountEntity.setMiddleName(userAccount.getMiddleName());
            userAccountEntity.setFirstName(userAccount.getFirstName());
            userAccountRepository.save(userAccountEntity);
        }
    }

    public void removeUser(int userId) {
        userAccountRepository.deleteById(Integer.valueOf(userId));
    }

    public int createNewUser(UserAccount user) {
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setFirstName(user.getFirstName());
        userAccountEntity.setMiddleName(user.getMiddleName());
        userAccountEntity.setLastName(user.getLastName());
        userAccountEntity.setCredit(BigDecimal.ZERO);
        BasketEntity basketEntity = new BasketEntity();
        basketEntity.setTotalPrice(BigDecimal.ZERO);
        userAccountEntity.setBasket(basketEntity);
        userAccountEntity = userAccountRepository.save(userAccountEntity);

        return userAccountEntity.getId();
    }

    public boolean addMoney(AddMoney credit) {
        Optional<UserAccountEntity> optionalUser = userAccountRepository.findById(credit.getId());
        if (optionalUser.isPresent()) {
            UserAccountEntity userAccountEntity = optionalUser.get();
            userAccountEntity.setCredit(credit.getCredit());
            userAccountRepository.save(userAccountEntity);
            return true;
        }
        return false;
    }
}


