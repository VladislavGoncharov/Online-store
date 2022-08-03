set search_path to eshop;

alter table product ADD column img varchar(50);

update product set img = '1_cheese' where id = 1;
update product set img = '2_milk' where id = 2;
update product set img = '3_butter' where id = 3;
update product set img = '4_bread' where id = 4;
update product set img = '5_buf' where id = 5;
update product set img = '6_loaf' where id = 6;
update product set img = '7_vodka' where id = 7;
update product set img = '8_tomato' where id = 8;
update product set img = '9_cucumber' where id = 9;