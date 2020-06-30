package com.onlinestore.store.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "basket_item")
public class BasketItemEntity {
    @Id
    private int id;

    @OneToOne
    @Column
    private ItemEntity item;

    @Column
    private int count;

    @ManyToOne
    @Column(name = "basket_id")
    private BasketEntity basket;
}
