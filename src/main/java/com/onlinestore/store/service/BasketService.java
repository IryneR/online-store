package com.onlinestore.store.service;

import com.onlinestore.store.dao.*;
import com.onlinestore.store.model.*;
import com.onlinestore.store.repository.BasketRepository;
import com.onlinestore.store.repository.ItemRepository;
import com.onlinestore.store.repository.UserAccountRepository;
import com.onlinestore.store.to.NewBasket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BasketService {
    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private ItemRepository itemRepository;

    private static final int NUMBER_OF_DISCOUNTS = 3;

    public boolean pay(NewBasket basket) {
        UserAccountEntity userAccountEntity = userAccountRepository.findById(basket.getUserId()).get();
        BigDecimal totalPrice = userAccountEntity.getBasket().getTotalPrice();
        BigDecimal userCredit = userAccountEntity.getCredit();

        if (userCredit.compareTo(totalPrice) >= 0) {
            BasketEntity basketEntity = userAccountEntity.getBasket();

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setCount(basketEntity.getCount());
            orderEntity.setStatus(OrderStatus.PAID.name());
            orderEntity.setTotalPrice(basketEntity.getTotalPrice());

            List<OrderItemEntity> orderItemEntities = new ArrayList<>();
            for (BasketItemEntity basketItemEntity : basketEntity.getItemList()) {
                orderItemEntities.add(createOrderItemEntity(basketItemEntity));
                basketItemEntity.getItem().setCount(basketItemEntity.getItem().getCount() - basketItemEntity.getCount());
                itemRepository.save(basketItemEntity.getItem());
            }
            basketEntity.setTotalPrice(BigDecimal.ZERO);
            basketEntity.setCount(0);
            basketEntity.setItemList(new ArrayList<>());

            orderEntity.setItemList(orderItemEntities);

            userAccountEntity.getOrders().add(orderEntity);
            userAccountEntity.setCredit(userCredit.subtract(totalPrice));

            userAccountRepository.save(userAccountEntity);
            return true;
        } else {
            return false;
        }
    }

    public void deleteItemById(int userId, int itemId) {
        BasketEntity basketEntity = userAccountRepository.findById(userId).get().getBasket();
        List<BasketItemEntity> basketItemsEntity = basketEntity.getItemList();
        Iterator<BasketItemEntity> iterator = basketItemsEntity.iterator();
        while (iterator.hasNext()) {
            BasketItemEntity basketItemEntity = iterator.next();
            int count = basketItemEntity.getItem().getCount();

            if (basketItemEntity.getItem().getId() == itemId) {
                if (count > 1) {
                    basketItemEntity.getItem().setCount(count - 1);
                } else {
                    iterator.remove();
                }
            }
        }
        basketRepository.save(basketEntity);
    }

    public void deleteAllItemsById(int userId, int itemId) {
        BasketEntity basketEntity = userAccountRepository.findById(userId).get().getBasket();
        List<BasketItemEntity> basketItemsEntity = basketEntity.getItemList();
        Iterator<BasketItemEntity> iterator = basketItemsEntity.iterator();

        while (iterator.hasNext()) {
            BasketItemEntity basketItemEntity = iterator.next();
            if (basketItemEntity.getItem().getId() == itemId) {
                iterator.remove();
            }
        }
        basketRepository.save(basketEntity);
    }

    public void deleteAllItems(int userId) {
        BasketEntity basketEntity = userAccountRepository.findById(userId).get().getBasket();
        List<BasketItemEntity> basketItemsEntity = basketEntity.getItemList();
        Iterator<BasketItemEntity> iterator = basketItemsEntity.iterator();

        while (iterator.hasNext()) {
            iterator.remove();
        }
        basketRepository.save(basketEntity);
    }

    //replace items
    public void addItem(NewBasket basket) {
        BasketEntity basketEntity = userAccountRepository.findById(basket.getUserId()).get().getBasket();

        List<BasketItem> basketItems = basket.getItems();
        List<BasketItemEntity> basketItemEntities = new ArrayList<>();

        Map<Integer, BasketItemEntity> idToItem = basketEntity.getItemList()
                .stream()
                .collect(Collectors.toMap(BasketItemEntity::getId, basketItemEntity -> basketItemEntity));

        int count = 0;

        for (BasketItem basketItem : basketItems) {
            Integer storeItemCount = idToItem.get(basketItem.getItem().getId()).getCount();
            if ((basketItem.getCount() <= storeItemCount) && (basketItem.getCount() != 0)) {
                basketItemEntities.add(createBasketItemEntity(basketItem));
                count = count + basketItem.getCount();
            } else if ((basketItem.getCount() > storeItemCount)) {
                basketItem.setCount(storeItemCount);
                basketItemEntities.add(createBasketItemEntity(basketItem));
                count = count + basketItem.getCount();
            }
        }

        basketEntity.setItemList(basketItemEntities);
        basketEntity.setTotalPrice(calculateTotalPrice(basketEntity.getItemList()));
        basketEntity.setCount(count);

        basketRepository.save(basketEntity);
    }

    public OrderItemEntity createOrderItemEntity(BasketItemEntity basketItemEntity) {
        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setCount(basketItemEntity.getCount());
        orderItemEntity.setItem(basketItemEntity.getItem());
        return orderItemEntity;
    }

    public BasketItemEntity createBasketItemEntity(BasketItem basketItem) {
        //create basket item entity
        BasketItemEntity basketItemEntity = new BasketItemEntity();
        basketItemEntity.setCount(basketItem.getCount());

        //create item entity
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(basketItem.getItem().getId());
        itemEntity.setCount(basketItem.getItem().getCount());
        itemEntity.setName(basketItem.getItem().getName());
        itemEntity.setPrice(basketItem.getItem().getPrice());

        //create category entity
        CategoryEntity categoryEntity = new CategoryEntity();
        Category category = basketItem.getItem().getCategory();
        categoryEntity.setId(category.getId());
        categoryEntity.setName(category.getName());
        itemEntity.setCategory(categoryEntity);

        //create discount entity
        DiscountEntity discountEntity = new DiscountEntity();
        Discount discount = basketItem.getItem().getDiscount();
        discountEntity.setId(discount.getId());
        discountEntity.setName(discount.getName());
        discountEntity.setPercent(discount.getPercent());
        itemEntity.setDiscount(discountEntity);

        basketItemEntity.setItem(itemEntity);

        return basketItemEntity;
    }

    public BigDecimal calculateTotalPrice(List<BasketItemEntity> basketItemsEntity) {
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal discount = BigDecimal.ZERO;
        List<BigDecimal> discounts = new ArrayList<>();

        for (BasketItemEntity basketItemEntity : basketItemsEntity) {
            BigDecimal priceForItem = basketItemEntity.getItem().getPrice().multiply(BigDecimal.valueOf(basketItemEntity.getCount()));
            sum = sum.add(priceForItem);
            BigDecimal discountForItem = BigDecimal.valueOf(basketItemEntity.getItem().getDiscount().getPercent()).divide(BigDecimal.valueOf(100)).multiply(priceForItem);
            discounts.add(discountForItem);
            discount = discount.add(discountForItem);
        }
        if (discounts.size() > NUMBER_OF_DISCOUNTS) {
            discount = BigDecimal.ZERO;
            Collections.sort(discounts, Collections.reverseOrder());
            for (int i = 0; i < NUMBER_OF_DISCOUNTS; i++) {
                discount = discount.add(discounts.get(i));
            }
        }
        return sum.subtract(discount);
    }


}
