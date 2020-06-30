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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @Column
    private int count;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    private BasketEntity basket;
}
