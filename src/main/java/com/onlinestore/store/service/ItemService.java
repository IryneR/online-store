package com.onlinestore.store.service;

import com.onlinestore.store.dao.CategoryEntity;
import com.onlinestore.store.dao.DiscountEntity;
import com.onlinestore.store.dao.ItemEntity;
import com.onlinestore.store.model.Category;
import com.onlinestore.store.model.Discount;
import com.onlinestore.store.model.Item;
import com.onlinestore.store.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAlItems() {
        Iterable<ItemEntity> itemsEntity = itemRepository.findAll();
        List<Item> items = new ArrayList<>();
        itemsEntity.forEach(itemEntity -> {
            Item item = new Item();
            item.setId(itemEntity.getId());
            item.setName(itemEntity.getName());
            item.setCount(itemEntity.getCount());
            item.setPrice(itemEntity.getPrice());

            CategoryEntity categoryEntity = itemEntity.getCategory();
            Category category = new Category();
            category.setId(categoryEntity.getId());
            category.setName(categoryEntity.getName());
            item.setCategory(category);

            DiscountEntity discountEntity = itemEntity.getDiscount();
            Discount discount = new Discount();
            discount.setName(discountEntity.getName());
            discount.setId(discountEntity.getId());
            discount.setPercent(discountEntity.getPercent());
            item.setDiscount(discount);

            items.add(item);
        });
        return items;
    }

    public Optional<Item> getItem(int userId) {
        Item item = new Item();
        Optional<ItemEntity> optionalItemEntity = itemRepository.findById(userId);
        if (optionalItemEntity.isPresent()) {
            ItemEntity itemEntity = optionalItemEntity.get();
            item.setId(itemEntity.getId());
            item.setName(itemEntity.getName());
            item.setCount(itemEntity.getCount());
            item.setPrice(itemEntity.getPrice());

            CategoryEntity categoryEntity = itemEntity.getCategory();
            Category category = new Category();
            category.setId(categoryEntity.getId());
            category.setName(categoryEntity.getName());
            item.setCategory(category);

            DiscountEntity discountEntity = itemEntity.getDiscount();
            Discount discount = new Discount();
            discount.setId(discountEntity.getId());
            discount.setName(discountEntity.getName());
            discount.setPercent(discountEntity.getPercent());
            item.setDiscount(discount);
            return Optional.of(item);
        }
        return Optional.empty();
    }

    public void updateItem(Item item) {
        Optional<ItemEntity> optionalItemEntity = itemRepository.findById(item.getId());
        if (optionalItemEntity.isPresent()) {
            ItemEntity itemEntity = optionalItemEntity.get();
            itemEntity.setId(item.getId());
            itemEntity.setName(item.getName());
            itemEntity.setCount(item.getCount());
            itemEntity.setPrice(item.getPrice());

            Category category = item.getCategory();
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(category.getId());
            categoryEntity.setName(category.getName());
            itemEntity.setCategory(categoryEntity);

            Discount discount = item.getDiscount();
            DiscountEntity discountEntity = new DiscountEntity();
            discountEntity.setId(discount.getId());
            discountEntity.setName(discount.getName());
            discountEntity.setPercent(discount.getPercent());
            itemEntity.setDiscount(discountEntity);

            itemRepository.save(itemEntity);
        }
    }

    public void removeItem(int itemId) {
        itemRepository.deleteById(Integer.valueOf(itemId));
    }

    public int createNewItem(Item item) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setName(item.getName());
        itemEntity.setCount(item.getCount());
        itemEntity.setPrice(item.getPrice());

        Category category = item.getCategory();
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(category.getId());
        categoryEntity.setName(category.getName());
        itemEntity.setCategory(categoryEntity);

        Discount discount = item.getDiscount();
        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setId(discount.getId());
        discountEntity.setName(discount.getName());
        discountEntity.setPercent(discount.getPercent());
        itemEntity.setDiscount(discountEntity);

        itemEntity = itemRepository.save(itemEntity);
        return itemEntity.getId();
    }


}
