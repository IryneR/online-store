CREATE TABLE user_account (
  	id SERIAL,
  	first_name varchar(255) DEFAULT NULL,
  	last_name varchar(255) DEFAULT NULL,
  	middle_name varchar(255) DEFAULT NULL,
  	credit NUMERIC,
  	basket_id NUMERIC DEFAULT NULL,
  	order_id NUMERIC DEFAULT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE basket (
  	id SERIAL,
  	total_price NUMERIC DEFAULT NULL,
  	count NUMERIC DEFAULT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE basket_item (
  	id SERIAL,
  	item_id NUMERIC DEFAULT NULL,
  	count NUMERIC DEFAULT NULL,
  	basket_id NUMERIC DEFAULT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (basket_id) REFERENCES basket(id),
	FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE item (
  	id SERIAL,
  	name VARCHAR (200),
  	price NUMERIC,
  	discount_id NUMERIC,
  	category_id NUMERIC ,
  	count NUMERIC DEFAULT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (discount_id) REFERENCES discount(id),
	FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE category (
  	id SERIAL,
  	name VARCHAR(255),
	PRIMARY KEY (id)
	);

CREATE TABLE discount (
  	id SERIAL,
  	name VARCHAR(255),
	PRIMARY KEY (id)
	);

CREATE TABLE order (
  	id SERIAL,
  	total_price NUMERIC DEFAULT NULL,
  	count NUMERIC DEFAULT NULL,
  	status VARCHAR (20),
	PRIMARY KEY (id)

);

CREATE TABLE order_item (
  	id SERIAL,
  	item_id NUMERIC DEFAULT NULL,
  	count NUMERIC DEFAULT NULL,
  	order_id NUMERIC DEFAULT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (order_id) REFERENCES order(id),
	FOREIGN KEY (item_id) REFERENCES item(id)
);