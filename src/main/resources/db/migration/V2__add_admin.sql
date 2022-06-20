INSERT INTO users (id, username, password, role, email, phone, address)
VALUES ( 1, 'админ'
            ,'$2y$10$M80lZHMal6rL3SJfn6Mfju9oIPcquA7uBmcwL31EXEy6rz/2iKQqi'
            ,'ADMIN', 'veyvik87@gmail.com', '89992459509',''),
        (2,'модератор'
            ,'$2y$10$M80lZHMal6rL3SJfn6Mfju9oIPcquA7uBmcwL31EXEy6rz/2iKQqi'
            ,'MODERATOR','moderator@gmail.com','89996982145',''),
        (3,'юзер'
            ,'$2y$10$M80lZHMal6rL3SJfn6Mfju9oIPcquA7uBmcwL31EXEy6rz/2iKQqi'
            ,'USER','user@gmail.com','89998595785','');

ALTER SEQUENCE user_seq RESTART WITH 4;