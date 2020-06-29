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
    private int id;
    @Column
    @OneToOne
    private ItemEntity item;
    @Column
    private int count;
}
