package com.onlinestore.store.repository;

import com.onlinestore.store.dao.UserAccountEntity;
import com.onlinestore.store.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccountEntity, Integer> {

}
