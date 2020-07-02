package com.onlinestore.store.service;

import com.onlinestore.store.dao.CategoryEntity;
import com.onlinestore.store.model.Category;
import com.onlinestore.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        Iterable<CategoryEntity> categoryEntities = categoryRepository.findAll();
        List<Category> categories = new ArrayList<>();
        categoryEntities.forEach(categoryElement -> {
            Category category = new Category();
            category.setId(categoryElement.getId());
            category.setName(categoryElement.getName());
            categories.add(category);
        });
        return categories;
    }

    public void updateCategory(Category category) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(category.getId());
        if (optionalCategoryEntity.isPresent()) {
            CategoryEntity categoryEntity = optionalCategoryEntity.get();
            categoryEntity.setName(category.getName());

            categoryRepository.save(categoryEntity);
        }
    }

    public void removeCategory(int categoryId) {
        categoryRepository.deleteById(Integer.valueOf(categoryId));
    }

    public int createNewCategory(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(category.getName());

        categoryEntity = categoryRepository.save(categoryEntity);
        return categoryEntity.getId();
    }

}
