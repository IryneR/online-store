package com.onlinestore.store.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private Item item;
    private int count;
}
