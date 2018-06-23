CREATE TABLE receipts (
	id INT PRIMARY KEY AUTO_INCREMENT,
	buyer VARCHAR(100) NOT NULL,
  date timestamp NOT NULL
);

CREATE TABLE products (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL UNIQUE,
	price DOUBLE NOT NULL
);

CREATE TABLE receipt_products (
	receipt_id INT NOT NULL,
	product_id INT NOT NULL,
	FOREIGN KEY (receipt_id)
	    REFERENCES receipts(id),
  FOREIGN KEY (product_id)
	    REFERENCES products(id)
);