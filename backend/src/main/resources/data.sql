-- PRAVA PRISTUPA
INSERT INTO authority (name) VALUES ('ROLE_NORMAL_USER');

INSERT INTO authority (name) VALUES ('ROLE_PATIENT');
INSERT INTO authority (name) VALUES ('ROLE_PHARMACIST');
INSERT INTO authority (name) VALUES ('ROLE_DERMATOLOGIST');
INSERT INTO authority (name) VALUES ('ROLE_PHARMACY_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_SYSTEM_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_SUPPLIER');

/*SIFRA ZA SVE JE KORISNIKE je 123
 * KADA PRODJE 10X KROZ BCRYPT ENKODER POSTANE :
 * '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC' 
**/

-- KORISNICI
INSERT INTO normal_user (username, password, first_name, last_name, email, country, phone_number, address, city) VALUES
('user1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'User1', 'Useric1', 'user1@gmail.com', 'Serbia', '+38112345678', 'Neka Adresa St. 1', 'Novi Sad');
-- KREIRANJE POVEZNE TABELE KORISNIKA SA PRAVIMA PRISTUPA
INSERT INTO normal_user_authority (normal_user_id, authority_id) VALUES (1, 1);

INSERT INTO normal_user (username, password, first_name, last_name, email, country, phone_number, address, city) VALUES
('user2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'User2', 'Useric2', 'user2@gmail.com', 'Serbia', '+38112345678', 'Neka Adresa St. 1', 'Novi Sad');








INSERT INTO drug (id, code, name) VALUES (1, '20022', 'Brufen');
INSERT INTO drug (id, code, name) VALUES (2, '20033', 'Aspirin');
INSERT INTO drug (id, code, name) VALUES (3, '20044', 'Andol');
INSERT INTO drug (id, code, name) VALUES (4, '20055', 'Paracetamol');
INSERT INTO drug (id, code, name) VALUES (5, '20066', 'Bromazepam');
INSERT INTO drug (id, code, name) VALUES (6, '200221', 'Brufen');
INSERT INTO drug (id, code, name) VALUES (7, '200222', 'Brufen');

INSERT INTO pharmacy (id, name, address) values (1, 'Jankovic', 'Narodnog Fronta 1, Novi Sad');
INSERT INTO pharmacy (id, name, address) values (2, 'Zegin', 'Bulevar Oslobodjenja 32, Novi Sad');
INSERT INTO pharmacy (id, name, address) values (3, 'Benu', 'Bulevar Cara Lazara 20, Novi Sad');

-- INSERT INTO pharmacy_drug(drug_id, pharmacy_id) values (1, 1);
-- INSERT INTO pharmacy_drug(drug_id, pharmacy_id) values (1, 2);
-- INSERT INTO pharmacy_drug(drug_id, pharmacy_id) values (2, 3);

INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (1, 1, 15);
INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (1, 2, 20);
INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (1, 3, 22);
INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (2, 1, 10);
INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (2, 2, 8);
INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (2, 3, 31);
INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (3, 1, 21);
INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (3, 2, 33);
INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (3, 3, 31);
INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (4, 3, 27);
INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (5, 3, 7);
INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (6, 2, 8);
INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity) values (7, 1, 4);
