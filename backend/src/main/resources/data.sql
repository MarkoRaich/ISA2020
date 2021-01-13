-- PRAVA PRISTUPA

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

INSERT INTO patient (username, password, first_name, last_name, address, city, phone_number) values
('pacijent1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Marko', 'Markovic', 'Mose Pijade 3', 'Beograd', '02155333' );
insert into patient_authority(user_id, authority_id) VALUES (1,1);

INSERT INTO patient (username, password, first_name, last_name, address, city, phone_number) values
('pacijent2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Petar', 'Petrovic', 'Boska Buhe 12', 'Kraljevo', '065772415' );
insert into patient_authority(user_id, authority_id) VALUES (2,1);

-- LEKOVI - PREPARATI

INSERT INTO drug (id, code, name) VALUES (1, '20022', 'Brufen');
INSERT INTO drug (id, code, name) VALUES (2, '20033', 'Aspirin');
INSERT INTO drug (id, code, name) VALUES (3, '20044', 'Andol');
INSERT INTO drug (id, code, name) VALUES (4, '20055', 'Paracetamol');
INSERT INTO drug (id, code, name) VALUES (5, '20066', 'Bromazepam');
INSERT INTO drug (id, code, name) VALUES (6, '200221', 'Brufen');
INSERT INTO drug (id, code, name) VALUES (7, '200222', 'Brufen');

-- APOTEKE

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

-- ADMINISTRATORI APOTEKE

INSERT INTO pharmacy_admin (username, password, first_name, last_name, address, city, phone_number) values
('apotekaAdmin1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Jovan', 'Jovanovic', 'Sime Matavulja 8b', 'Pancevo', '065844584');     
insert into pharmacy_admin_authority(user_id, authority_id) VALUES (1,4);

INSERT INTO pharmacy_admin (username, password, first_name, last_name, address, city, phone_number) values
('apotekaAdmin2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Stevan', 'Stevanovic', 'Karadjordeva 23', 'Nis', '0650084');     
insert into pharmacy_admin_authority(user_id, authority_id) VALUES (2,4);










