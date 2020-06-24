package com.onlinestore.store.dao;

import com.onlinestore.store.model.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "basketItem")
public class BasketItemEntity {
    @Id
    private int id;
    @Column
    @ManyToOne
    private ItemEntity item;
    @Column
    private int count;
}
