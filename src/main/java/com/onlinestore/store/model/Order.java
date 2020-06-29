package com.onlinestore.store.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Order {
    private int id;
    private List<OrderItem> itemList;
    private BigDecimal totalPrice;
    private int count;
    private OrderStatus status;
}
