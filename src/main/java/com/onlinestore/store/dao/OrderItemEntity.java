package com.onlinestore.store.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "order_item")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "item_id")
    @OneToOne
    private ItemEntity item;

    @Column
    private int count;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
