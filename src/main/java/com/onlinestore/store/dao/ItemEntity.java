package com.onlinestore.store.dao;

import com.onlinestore.store.model.Category;
import com.onlinestore.store.model.Discount;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "item")
public class ItemEntity {
    @Id
    private int id;
    @Column
    private String name;
    @Column
    private BigDecimal price;
    @Column
    @OneToOne
    private DiscountEntity discount;
    @Column
    @OneToOne
    private CategoryEntity category;
    @Column
    private int count;
}
