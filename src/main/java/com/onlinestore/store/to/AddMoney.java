package com.onlinestore.store.to;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AddMoney {
    int id;
    BigDecimal credit;
}
