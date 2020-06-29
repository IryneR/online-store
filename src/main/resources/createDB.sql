CREATE TABLE user_account (
  	id SERIAL,
  	first_name varchar(255) DEFAULT NULL,
  	last_name varchar(255) DEFAULT NULL,
  	middle_name varchar(255) DEFAULT NULL,
  	basket_id int DEFAULT NULL,
	credit NUMERIC,
	PRIMARY KEY (id)
);

CREATE TABLE basket (
  	id SERIAL,
  	bastet_item_id
  	total_price NUMERIC DEFAULT NULL,
  	count NUMERIC DEFAULT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE basket (
  	id SERIAL,
  	itemList
  	totalPrice NUMERIC DEFAULT NULL,
  	count NUMERIC DEFAULT NULL,
	PRIMARY KEY (id)
);