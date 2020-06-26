package com.onlinestore.store.service;

import com.onlinestore.store.dao.CategoryEntity;
import com.onlinestore.store.dao.DiscountEntity;
import com.onlinestore.store.dao.ItemEntity;
import com.onlinestore.store.dao.UserAccountEntity;
import com.onlinestore.store.model.Category;
import com.onlinestore.store.model.Discount;
import com.onlinestore.store.model.Item;
import com.onlinestore.store.model.UserAccount;
import com.onlinestore.store.repository.ItemRepository;
import com.onlinestore.store.repository.UserAccountRepository;
import com.onlinestore.store.to.AddMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
            item.setName(itemEntity.getName());
            item.setCount(itemEntity.getCount());
            item.setPrice(itemEntity.getPrice());

            CategoryEntity categoryEntity = itemEntity.getCategory();
            Category category = new Category();
            category.setName(categoryEntity.getName());
            item.setCategory(category);

            DiscountEntity discountEntity = itemEntity.getDiscount();
            Discount discount = new Discount();
            discount.setName(discountEntity.getName());
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
            item.setName(itemEntity.getName());
            item.setCount(itemEntity.getCount());
            item.setPrice(itemEntity.getPrice());

            CategoryEntity categoryEntity = itemEntity.getCategory();
            Category category = new Category();
            category.setName(categoryEntity.getName());
            item.setCategory(category);

            DiscountEntity discountEntity = itemEntity.getDiscount();
            Discount discount = new Discount();
            discount.setName(discountEntity.getName());
            item.setDiscount(discount);
            return Optional.of(item);
        }
        return Optional.empty();
    }

    public void updateItem(Item item) {
        Optional<ItemEntity> optionalItemEntity = itemRepository.findById(item.getId());
        if (optionalItemEntity.isPresent()) {
            ItemEntity itemEntity = optionalItemEntity.get();
            itemEntity.setName(item.getName());
            itemEntity.setCount(item.getCount());
            itemEntity.setPrice(item.getPrice());

            Category category = item.getCategory();
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setName(category.getName());
            itemEntity.setCategory(categoryEntity);

            Discount discount = item.getDiscount();
            DiscountEntity discountEntity = new DiscountEntity();
            discountEntity.setName(discount.getName());
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
        categoryEntity.setName(category.getName());
        itemEntity.setCategory(categoryEntity);

        Discount discount = item.getDiscount();
        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.setName(discount.getName());
        itemEntity.setDiscount(discountEntity);

        itemEntity = itemRepository.save(itemEntity);
        return itemEntity.getId();
    }


}
