package com.onlinestore.store.service;

import com.onlinestore.store.dao.DiscountEntity;
import com.onlinestore.store.dao.DiscountEntity;
import com.onlinestore.store.model.Discount;
import com.onlinestore.store.model.Discount;
import com.onlinestore.store.repository.CategoryRepository;
import com.onlinestore.store.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    public List<Discount> getAllDiscounts() {
        Iterable<DiscountEntity> discountsEntity = discountRepository.findAll();
        List<Discount> discounts = new ArrayList<>();
        discountsEntity.forEach(discountElement -> {
            Discount discount = new Discount();
            discount.setName(discountElement.getName());
            discounts.add(discount);
        });
        return discounts;
    }

   /* public Optional<Category> getCategory(int itemId) {
        Category item = new Category();
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(itemId);
        if (optionalItemEntity.isPresent()) {
            ItemEntity itemEntity = optionalItemEntity.get();
            item.setName(itemEntity.getName());
            item.setCount(itemEntity.getCount());
            item.setPrice(itemEntity.getPrice());

            return Optional.of(item);
        }
        return Optional.empty();
    }*/

    public void updateDiscount(Discount discount) {
        Optional<DiscountEntity> optionalDiscountEntity = discountRepository.findById(discount.getId());
        if (optionalDiscountEntity.isPresent()) {
            DiscountEntity categoryEntity = optionalDiscountEntity.get();
            categoryEntity.setName(discount.getName());

            discountRepository.save(categoryEntity);
        }
    }

    public void removeDiscount(int discountId) {
        discountRepository.deleteById(Integer.valueOf(discountId));
    }

    public int createNewDiscount(Discount discount) {
        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setName(discount.getName());

        discountEntity = discountRepository.save(discountEntity);
        return discountEntity.getId();
    }

}
