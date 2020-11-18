insert into user(id, name) values (1, 'Aca');

--AUTHORITIES
INSERT INTO authority (name) VALUES ('ROLE_NORMAL_USER');


-- NORMAL USERS
INSERT INTO normal_user (username, password, first_name, last_name, email, country, phone_number, address, city)
VALUES ('user1', '$2a$10$l8J.2UoFqfOwj9t7GRAtAen1/t8Sz2HfAxYT9LehVxq58wa9LihEi', 'User1', 'Useric1', 'user1@gmail.com', 'Serbia', '+38112345678', 'Neka Adresa St. 1', 'Novi Sad');
INSERT INTO normal_user_authority (normal_user_id, authority_id) VALUES (1, 1);