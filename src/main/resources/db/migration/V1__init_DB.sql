set
search_path to eshop;

drop sequence if exists bucket_seq cascade;
drop sequence if exists categories_seq cascade;
drop sequence if exists order_details_seq cascade;
drop sequence if exists order_seq cascade;
drop sequence if exists product_seq cascade;
drop sequence if exists user_seq cascade;

create sequence bucket_seq start 1 increment 1;
create sequence categories_seq start 1 increment 1;
create sequence order_details_seq start 1 increment 1;
create sequence order_seq start 1 increment 1;
create sequence product_seq start 1 increment 1;
create sequence user_seq start 1 increment 1;

drop table if exists bucket cascade;
create table bucket
(
    id      int8 not null,
    user_id int8,
    primary key (id)
);

drop table if exists bucket_products cascade;
create table bucket_products
(
    bucket_id  int8 not null,
    product_id int8 not null
);

drop table if exists categories cascade;
create table categories
(
    id    int8 not null,
    title varchar(255),
    primary key (id)
);
drop table if exists categories_product cascade;
create table categories_product
(
    category_id int8 not null,
    product_id  int8 not null
);


drop table if exists order_details cascade;
create table order_details
(
    id         int8 not null,
    order_id   int8,
    amount     int8,
    price      int8,
    product_id int8,
    primary key (id)
);

drop table if exists orders cascade;
create table orders
(
    id      int8 not null,
    user_id int8,
    address varchar(255),
    created timestamp,
    status  varchar(255),
    updated timestamp,
    sum     int8,
    primary key (id)
);

drop table if exists orders_details cascade;
create table orders_details
(
    order_id   int8 not null,
    details_id int8 not null
);

drop table if exists product cascade;
create table product
(
    id          int8 not null,
    price       int8,
    title       varchar(255),
    category_id int8,
    primary key (id)
);

drop table if exists users cascade;
create table users
(
    id       int8 not null,
    username varchar(255),
    password varchar(255),
    role     varchar(255),
    email    varchar(255),
    phone    varchar(255),
    address  varchar(255),
    primary key (id)
);

alter table if exists orders_details add constraint UK_kk6y3pyhjt6kajomtjbhsoajo unique (details_id);
alter table if exists bucket add constraint FK327w43qqj7sg7250igsyxw7s3 foreign key (user_id) references users;
alter table if exists bucket_products add constraint FK3dwp02gip9thr6eec4qyst3r8 foreign key (product_id) references product;
alter table if exists bucket_products add constraint FKt70fe5opygmnfsbmmxoai26xs foreign key (bucket_id) references bucket;
alter table if exists categories_product add constraint FK1jur8uxoobv8aggclg334u9rp foreign key (product_id) references product;
alter table if exists categories_product add constraint FKaowtdchea267qddo62jnvta4f foreign key (category_id) references categories;
alter table if exists order_details add constraint FKjyu2qbqt8gnvno9oe9j2s2ldk foreign key (order_id) references orders;
alter table if exists order_details add constraint FKinivj2k1370kw224lavkm3rqm foreign key (product_id) references product;
alter table if exists orders add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users;
alter table if exists orders_details add constraint FKbblrq2kcscnyr9fsv8ptp98wy foreign key (details_id) references order_details;
alter table if exists orders_details add constraint FK5o977kj2vptwo70fu7w7so9fe foreign key (order_id) references orders;
alter table if exists product add constraint FKowomku74u72o6h8q0khj7id8q foreign key (category_id) references categories;