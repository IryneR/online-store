package com.onlinestore.store.dao;

import com.onlinestore.store.model.BasketItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "basket")
public class BasketEntity {
    @Id
        private int id;
    @Column
    @OneToMany
        private List<BasketItemEntity> itemList;
    @Column
        private BigDecimal totalPrice;
    @Column
        private int count;

}
