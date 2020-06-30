package com.onlinestore.store.repository;

import com.onlinestore.store.dao.UserAccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccountEntity, Integer> {

}
