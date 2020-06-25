package com.onlinestore.store.service;

import com.onlinestore.store.dao.UserAccountEntity;
import com.onlinestore.store.model.UserAccount;
import com.onlinestore.store.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

       /* public List<UserAccount> getAllUserAccounts() {
            return (List<UserAccount>) userAccountRepository.findAll();
        }

        public Optional<UserAccount> getUserAccount(int userId) {
            return userAccountRepository.findById(userId);
        }*/

     /*   public int updateUser( UserAccount userAccount) {
            Optional<UserAccount> optionalUser = getUser(user.getUserUid());
            if(optionalUser.isPresent()){
                return .updateUser( user);
            }
            return -1;
        }


        public int removeUser(UUID userUid) {
            Optional<User> optionalUser = getUser(userUid);
            if(optionalUser.isPresent()){
                return  userDao.deleteUserByUserUid(userUid);
            }
            return -1;
        }*/

    public int createNewUser(UserAccount user) {
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setFirstName(user.getFirstName());
        userAccountEntity.setMiddleName(user.getMiddleName());
        userAccountEntity.setLastName(user.getLastName());
        userAccountEntity.setCredit(BigDecimal.ZERO);
        userAccountEntity = userAccountRepository.save(userAccountEntity);
        return userAccountEntity.getId();
    }
}


