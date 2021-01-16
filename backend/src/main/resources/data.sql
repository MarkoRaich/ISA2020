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


-- REGISTROVANI KORISNICI - PACIJENTI

-- INSERT INTO patient (username, password, first_name, last_name, address, city, phone_number, points, penalties) values
-- ('pacijent1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Marko', 'Markovic', 'Mose Pijade 3', 'Beograd', '02155333', 0, 0 );
-- insert into patient_authority(user_id, authority_id) VALUES (1,1);

-- INSERT INTO patient (username, password, first_name, last_name, address, city, phone_number, points, penalties) values
-- ('pacijent2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Petar', 'Petrovic', 'Boska Buhe 12', 'Kraljevo', '065772415', 0, 0 );
-- insert into patient_authority(user_id, authority_id) VALUES (2,1);

-- -- LEKOVI - PREPARATI

-- INSERT INTO drug (id, code, name) VALUES (1, '20022', 'Brufen');
-- INSERT INTO drug (id, code, name) VALUES (2, '20033', 'Aspirin');
-- INSERT INTO drug (id, code, name) VALUES (3, '20044', 'Andol');
-- INSERT INTO drug (id, code, name) VALUES (4, '20055', 'Paracetamol');
-- INSERT INTO drug (id, code, name) VALUES (5, '20066', 'Bromazepam');
-- INSERT INTO drug (id, code, name) VALUES (6, '200221', 'Brufen');
-- INSERT INTO drug (id, code, name) VALUES (7, '200222', 'Brufen');

-- -- APOTEKE

-- INSERT INTO pharmacy (id, name, address, rating) values (1, 'Jankovic', 'Narodnog Fronta 1, Novi Sad', 4.5);
-- INSERT INTO pharmacy (id, name, address, rating) values (2, 'Zegin', 'Bulevar Oslobodjenja 32, Novi Sad', 4.2);
-- INSERT INTO pharmacy (id, name, address, rating) values (3, 'Benu', 'Bulevar Cara Lazara 20, Novi Sad', 4.0);

-- -- INSERT INTO pharmacy_drug(drug_id, pharmacy_id) values (1, 1);
-- -- INSERT INTO pharmacy_drug(drug_id, pharmacy_id) values (1, 2);
-- -- INSERT INTO pharmacy_drug(drug_id, pharmacy_id) values (2, 3);

-- INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity, price) values (1, 1, 15, 220.10);
-- INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity, price) values (1, 2, 20, 272.24);
-- INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity, price) values (1, 3, 22, 195.72);
-- INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity, price) values (2, 1, 10, 360.12);
-- INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity, price) values (2, 2, 8, 521.40);
-- INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity, price) values (2, 3, 31, 620.20);
-- INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity, price) values (3, 1, 21, 700.82);
-- INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity, price) values (3, 2, 33, 440.23);
-- INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity, price) values (3, 3, 31, 390.23);
-- INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity, price) values (4, 3, 27, 287.92);
-- INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity, price) values (5, 3, 7, 855.81);
-- INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity, price) values (6, 2, 8, 1120.30);
-- INSERT INTO pharmacy_drug_details(drug_id, pharmacy_id, quantity, price) values (7, 1, 4, 790.70);

-- -- ADMINISTRATORI APOTEKE

-- INSERT INTO pharmacy_admin (username, password, first_name, last_name, address, city, phone_number) values
-- ('apotekaAdmin1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Jovan', 'Jovanovic', 'Sime Matavulja 8b', 'Pancevo', '065844584');     
-- insert into pharmacy_admin_authority(user_id, authority_id) VALUES (3,4);

-- INSERT INTO pharmacy_admin (username, password, first_name, last_name, address, city, phone_number) values
-- ('apotekaAdmin2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Stevan', 'Stevanovic', 'Karadjordeva 23', 'Nis', '0650084');     
-- insert into pharmacy_admin_authority(user_id, authority_id) VALUES (4,4);










