package com.onlinestore.store.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity(name = "user_account")
public class UserAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column
    private BigDecimal credit;

    @Column(name = "basket_id")
    @OneToOne
    private BasketEntity basket;

    @Column(name = "order_id")
    @OneToMany
    private List<OrderEntity> orders;
}
