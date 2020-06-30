package com.onlinestore.store.controler;

import com.onlinestore.store.model.Category;
import com.onlinestore.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Integer> createNewCategory(@RequestBody Category category) {
        Integer categoryId = categoryService.createNewCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryId);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @PutMapping
    public ResponseEntity<Integer> updateCategory(@RequestBody Category category) {
        categoryService.updateCategory(category);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteCategory(@PathVariable Integer id) {
        categoryService.removeCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
