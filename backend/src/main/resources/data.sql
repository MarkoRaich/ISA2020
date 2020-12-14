
-- AUTHORITIES
INSERT INTO authority (name) VALUES ('ROLE_NORMAL_USER');


-- NORMAL USERS
INSERT INTO normal_user (username, password, first_name, last_name, email, country, phone_number, address, city)
VALUES ('user1', 'password', 'User1', 'Useric1', 'user1@gmail.com', 'Serbia', '+38112345678', 'Neka Adresa St. 1', 'Novi Sad');
INSERT INTO normal_user_authority (normal_user_id, authority_id) VALUES (1, 1);

INSERT INTO drug (id, code, name) VALUES (1, '20022', 'Brufen');
INSERT INTO drug (id, code, name) VALUES (2, '20033', 'Aspirin');
INSERT INTO drug (id, code, name) VALUES (3, '20044', 'Andol');
INSERT INTO drug (id, code, name) VALUES (4, '20055', 'Paracetamol');
INSERT INTO drug (id, code, name) VALUES (5, '20066', 'Bromazepam');
INSERT INTO drug (id, code, name) VALUES (6, '200221', 'Brufen');
INSERT INTO drug (id, code, name) VALUES (7, '200222', 'Brufen');

INSERT INTO pharmacy (id, api_key, name) values (1, '123ssdasda', 'Jankovic');
INSERT INTO pharmacy (id, api_key, name) values (2, '4654saddas', 'Zegin');
INSERT INTO pharmacy (id, api_key, name) values (3, '787qwqeqqq', 'Benu');

-- INSERT INTO pharmacy_drug(drug_id, pharmacy_id) values (1, 1);
-- INSERT INTO pharmacy_drug(drug_id, pharmacy_id) values (1, 2);
-- INSERT INTO pharmacy_drug(drug_id, pharmacy_id) values (2, 3);

INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (1, 1, 15);
INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (1, 2, 20);
INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (2, 3, 22);


