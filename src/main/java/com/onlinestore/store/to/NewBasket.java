package com.onlinestore.store.to;

import com.onlinestore.store.model.BasketItem;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewBasket {
    int userId;
    List<BasketItem> items;
}

