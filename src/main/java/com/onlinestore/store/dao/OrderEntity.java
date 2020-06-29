package com.onlinestore.store.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "order")
public class OrderEntity {
    @Id
    private int id;

    @Column(name = "order_item_id")
    @OneToMany
    private List<OrderItemEntity> itemList;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column
    private int count;

    @Column
    private String status;
}
