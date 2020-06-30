package com.onlinestore.store.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketItem {
    private Item item;
    private int count;
}
