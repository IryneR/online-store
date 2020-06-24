package com.onlinestore.store.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Item {
    private int id;
    private String name;
    private BigDecimal price;
    private Discount discount;
    private Category category;
    private int count;
}
