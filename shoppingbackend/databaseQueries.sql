CREATE TABLE category(
	id IDENTITY,
	name VARCHAR(50),
	description VARCHAR(255),
	image_url VARCHAR(50),
	is_active BOOLEAN,
	
	CONSTRAINT pk_category_id PRIMARY KEY(id)
);

INSERT INTO category(name,description,image_URL,is_active) VALUES ('Laptop','Laptop Description over here','CAT_01.png',true);
INSERT INTO category(name,description,image_URL,is_active) VALUES ('Mobile','Mobile Description over here','CAT_02.png',true);

CREATE TABLE user_detail (
	id IDENTITY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	role VARCHAR(50),
	enabled BOOLEAN,
	password VARCHAR(60),
	email VARCHAR(100),
	contact_number VARCHAR(15),	
	CONSTRAINT pk_user_id PRIMARY KEY(id),
);

INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Sujan', 'Maharjan', 'ADMIN', true, '$2a$10$nDARFm5jrW15Bn8ZkD2kD.V8Dg6md40pQMulGGzTNzIXH272NYBya', 'sujan.mhrzn2@gmail.com', '9860059613');

INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Aayush', 'Bhujel', 'SUPPLIER', true, '$2a$10$UgP9kcJbhWMkjW2O.cjgeu8wnKaNRV/ZHBBD3sS1JvQ4ypoGETrSK', 'aayush@gmail.com', '9841234567');

INSERT INTO user_detail 
(first_name, last_name, role, enabled, password, email, contact_number) 
VALUES ('Ravi', 'Kandel', 'USER', true, '$2b$10$cE6UjWozs9XesonmqIkA2utLg3vxjvjrQ6lR3gMtkVEXKqTynvOdi', 'ravi@gmail.com', '9818123456');


CREATE TABLE product (
	id IDENTITY,
	code VARCHAR(20),
	name VARCHAR(50),
	brand VARCHAR(50),
	description VARCHAR(255),
	unit_price DECIMAL(10,2),
	quantity INT,
	is_active BOOLEAN,
	category_id INT,
	supplier_id INT,
	purchases INT DEFAULT 0,
	views INT DEFAULT 0,
	CONSTRAINT pk_product_id PRIMARY KEY (id),
 	CONSTRAINT fk_product_category_id FOREIGN KEY (category_id) REFERENCES category (id),
	CONSTRAINT fk_product_supplier_id FOREIGN KEY (supplier_id) REFERENCES user_detail(id),	
);	


INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABC123DEFX', 'iPhone 7s', 'Apple', 'This is one of the best phone available in the market right now!', 18000, 5, true, 2, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDDEF123DEFX', 'Samsung s8', 'Samsung', 'A smart phone by samsung!', 32000, 2, true, 2, 3 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDPQR123WGTX', 'Google Pixel 2', 'Google', 'This is one of the best android smart phone available in the market right now!', 57000, 5, true, 2, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDMNO123PQRX', ' Macbook Pro', 'Apple', 'This is one of the best laptops available in the market right now!', 54000, 3, true, 1, 2 );

INSERT INTO product (code, name, brand, description, unit_price, quantity, is_active, category_id, supplier_id)
VALUES ('PRDABCXYZDEFX', 'HP Probook 450 G2', 'HP', 'This is one of the best laptop series from HP that can be used!', 67500, 5, true, 1, 3 );


-- The Cart Line Table to store the cart details
CREATE TABLE cart_line(
	id IDENTITY,
	cart_id int,
	total DECIMAL(10,2),
	product_id int,
	product_count int,
	buying_price DECIMAL(10,2),
	is_available boolean,
	CONSTRAINT fk_cartline_cart_id FOREIGN KEY (cart_id) REFERENCES cart(id),
	CONSTRAINT fk_cartline_product_id FOREIGN KEY (product_id) REFERENCES product(id),
	CONSTRAINT pk_cartline_id PRIMARY KEY (id)
);



