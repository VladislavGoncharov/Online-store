INSERT INTO product (id, price, title)
VALUES   (1, 450, 'Cheese'),
         (2, 45, 'Beer'),
         (3, 65, 'Milk'),
         (4, 115, 'Tomato'),
         (5, 58, 'Bread');

ALTER SEQUENCE product_seq RESTART WITH 6;