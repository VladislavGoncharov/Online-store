INSERT INTO categories (id, title)
VALUES (1, 'Алкоголь'),
       (2, 'Молочная продукция'),
       (3, 'Хлебобулочные изделия'),
       (4, 'Овощи');

ALTER SEQUENCE categories_seq RESTART WITH 5;

INSERT INTO product (id, price, title, category_id)
VALUES (1, 250, 'Сыр', 2),
       (2, 65, 'Молоко', 2),
       (3, 105, 'Масло', 2),
       (4, 35, 'Хлеб', 3),
       (5, 25, 'Булочка', 3),
       (6, 30, 'Батон', 3),
       (7, 160, 'Водка', 1),
       (8, 110, 'Помидор', 4),
       (9, 90, 'Огурец', 4);

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
