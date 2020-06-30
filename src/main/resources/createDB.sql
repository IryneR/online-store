CREATE TABLE basket (
                        id SERIAL,
                        total_price NUMERIC DEFAULT NULL,
                        count NUMERIC DEFAULT NULL,
                        PRIMARY KEY (id)
);

CREATE TABLE category (
                          id SERIAL,
                          name VARCHAR(255),
                          PRIMARY KEY (id)
);

CREATE TABLE discount (
                          id SERIAL,
                          name VARCHAR(255),
                          percent NUMERIC,
                          PRIMARY KEY (id)
);

CREATE TABLE item (
                      id SERIAL,
                      name VARCHAR (200),
                      price NUMERIC,
                      discount_id INTEGER,
                      category_id INTEGER ,
                      count NUMERIC DEFAULT NULL,
                      PRIMARY KEY (id),
                      FOREIGN KEY (discount_id) REFERENCES discount(id),
                      FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE basket_item (
                             id SERIAL,
                             item_id INTEGER DEFAULT NULL,
                             count NUMERIC DEFAULT NULL,
                             basket_id INTEGER DEFAULT NULL,
                             PRIMARY KEY (id),
                             FOREIGN KEY (basket_id) REFERENCES basket(id),
                             FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE user_order (
                            id SERIAL,
                            total_price NUMERIC DEFAULT NULL,
                            count NUMERIC DEFAULT NULL,
                            status VARCHAR (20),
                            user_id INTEGER,
                            PRIMARY KEY (id),
                            FOREIGN KEY (user_id) REFERENCES user_account(id)
);

CREATE TABLE order_item (
                            id SERIAL,
                            item_id INTEGER DEFAULT NULL,
                            count NUMERIC DEFAULT NULL,
                            order_id INTEGER DEFAULT NULL,
                            PRIMARY KEY (id),
                            FOREIGN KEY (order_id) REFERENCES user_order(id),
                            FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE user_account (
                              id SERIAL,
                              first_name varchar(255) DEFAULT NULL,
                              last_name varchar(255) DEFAULT NULL,
                              middle_name varchar(255) DEFAULT NULL,
                              credit NUMERIC,
                              basket_id INTEGER DEFAULT NULL,
                              PRIMARY KEY (id),
                              FOREIGN KEY (basket_id) REFERENCES basket(id)
);