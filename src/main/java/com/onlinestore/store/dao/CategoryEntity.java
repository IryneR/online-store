package com.onlinestore.store.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
        private int id;
    @Column
        private String name;
}
