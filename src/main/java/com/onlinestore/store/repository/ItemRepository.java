package com.onlinestore.store.repository;

import com.onlinestore.store.dao.ItemEntity;
import com.onlinestore.store.dao.UserAccountEntity;
import org.springframework.data.repository.CrudRepository;


public interface ItemRepository extends CrudRepository<ItemEntity, Integer> {

}