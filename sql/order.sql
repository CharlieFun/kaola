DROP TABLE IF EXISTS orderdetail;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS shop_cart;

CREATE TABLE orders(
  id BIGINT AUTO_INCREMENT,
  user_id BIGINT,
  createtime DATETIME,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id)
)charset=utf8 ENGINE=InnoDB;

CREATE TABLE orderdetail(
  id BIGINT AUTO_INCREMENT,
  order_id BIGINT,
  product_id BIGINT,
  amount INT,
  current_price DOUBLE,
  PRIMARY KEY (id),
  FOREIGN KEY (order_id) REFERENCES orders(id),
  FOREIGN KEY (product_id) REFERENCES product(id)
)charset=utf8 ENGINE=InnoDB;

CREATE TABLE shop_cart(
  id BIGINT AUTO_INCREMENT,
  user_id BIGINT,
  product_id BIGINT,
  num INT,
  PRIMARY KEY (id),
  INDEX (user_id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (product_id) REFERENCES product(id)
)charset=utf8 ENGINE=InnoDB;
