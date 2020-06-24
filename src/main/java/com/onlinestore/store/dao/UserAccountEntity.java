package com.onlinestore.store.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "userAccount")
public class UserAccountEntity {
    @Id
    private Integer id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String middleName;
    @Column
    private BigDecimal credit;
    @Column
    @OneToOne
    private BasketEntity basketId;
}
