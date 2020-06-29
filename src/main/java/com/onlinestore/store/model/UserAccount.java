package com.onlinestore.store.model;

import com.onlinestore.store.dao.BasketEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UserAccount {
    private Integer id;
    private String firstName;
    private String lastName;
    private String middleName;
    private BigDecimal credit;
    private Basket basket;

}
