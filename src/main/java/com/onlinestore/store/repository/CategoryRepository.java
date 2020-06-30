package com.onlinestore.store.repository;

import com.onlinestore.store.dao.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

}