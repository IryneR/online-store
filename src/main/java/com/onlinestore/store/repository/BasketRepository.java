package com.onlinestore.store.repository;

import com.onlinestore.store.dao.BasketEntity;
import com.onlinestore.store.dao.CategoryEntity;
import org.springframework.data.repository.CrudRepository;


public interface BasketRepository extends CrudRepository<BasketEntity, Integer> {

}
