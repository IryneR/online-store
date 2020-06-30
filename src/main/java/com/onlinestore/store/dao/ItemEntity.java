package com.onlinestore.store.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @JoinColumn(name = "discount_id")
    @OneToOne
    private DiscountEntity discount;

    @JoinColumn(name = "category_id")
    @OneToOne
    private CategoryEntity category;

    @Column
    private int count;
}