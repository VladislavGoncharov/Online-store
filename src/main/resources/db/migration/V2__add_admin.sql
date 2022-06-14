INSERT INTO users (id,username,password,role,email,phone)
VALUES (1,'admin'
       ,'$2a$10$nemuLAKxiQvuVj6Z/y6IluaPgupL5HQh5mHMjTVtpfG9tbHHAuDGa'
       ,'ADMIN','veyvik87@gmail.com','89992459509' );

ALTER SEQUENCE user_seq RESTART WITH 2;