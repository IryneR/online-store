package com.onlinestore.store.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class BasketItem {
    private Item item;
    private int count;
}
