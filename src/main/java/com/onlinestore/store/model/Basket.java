package com.onlinestore.store.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Basket {
    private int id;
    private List<BasketItem> itemList;
    private BigDecimal totalPrice;
    private int count;
}
