INSERT INTO categories (id, title)
VALUES (1, 'Alcohol'),
       (2, 'Dairy products'),
       (3, 'Bakery products'),
       (4, 'Vegetables');

ALTER SEQUENCE categories_seq RESTART WITH 5;

INSERT INTO product (id, price, title, category_id)
VALUES (1, 450, 'Cheese', 2),
       (2, 45, 'Milk', 2),
       (3, 65, 'Oil', 2),
       (4, 115, 'Bread', 3),
       (5, 115, 'Bun', 3),
       (6, 115, 'Loaf', 3),
       (7, 115, 'Vodka', 1),
       (8, 115, 'Tomato', 4),
       (9, 58, 'Сucumber', 4);

ALTER SEQUENCE product_seq RESTART WITH 10;


INSERT INTO categories_product (category_id, product_id)
VALUES (2, 1),
       (2, 2),
       (2, 3),
       (3, 4),
       (3, 5),
       (3, 6),
       (1, 7),
       (4, 8),
       (4, 9);
