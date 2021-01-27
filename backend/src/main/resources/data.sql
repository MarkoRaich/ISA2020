INSERT INTO authority (name) VALUES ('ROLE_PATIENT');
INSERT INTO authority (name) VALUES ('ROLE_PHARMACIST');
INSERT INTO authority (name) VALUES ('ROLE_DERMATOLOGIST');
INSERT INTO authority (name) VALUES ('ROLE_PHARMACY_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_SYSTEM_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_SUPPLIER');

/*
SIFRA ZA SVE JE KORISNIKE je 123!
 * KADA PRODJE 10X KROZ BCRYPT ENKODER POSTANE :
 * '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC' 
**/


-- REGISTROVANI KORISNICI - PACIJENTI I NJIHOVA PRAVA PRISTUPA POVEZANA

INSERT INTO patient (username, password, first_name, last_name, address, city, phone_number, points, penalties) values ('pacijent1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Marko', 'Markovic', 'Mose Pijade 3', 'Beograd', '02155333', 0, 0 );
insert into patient_authority(user_id, authority_id) VALUES (1,1);

INSERT INTO patient (username, password, first_name, last_name, address, city, phone_number, points, penalties) values ('pacijent2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Petar', 'Petrovic', 'Boska Buhe 12', 'Kraljevo', '065772415', 0, 0 );
insert into patient_authority(user_id, authority_id) VALUES (2,1);

-- LEKOVI

INSERT INTO drug (code, name, type) VALUES ('20022', 'Brufen', 'ANESTHETIC');
INSERT INTO drug (code, name, type) VALUES ('20033', 'Aspirin','ANTIHISTAMINIC');
INSERT INTO drug (code, name, type) VALUES ('20044', 'Andol', 'ANTIHISTAMINIC');
INSERT INTO drug (code, name, type) VALUES ('20055', 'Paracetamol', 'ANTIBIOTIC');
INSERT INTO drug (code, name, type) VALUES ('20066', 'Bromazepam', 'ANESTHETIC' );

-- APOTEKE

INSERT INTO pharmacy (name, address, city, description, rating) values ('Jankovic', 'Narodnog Fronta 1', 'Novi Sad', 'Apoteka Jankovic ima veliki izbor preparata', 4.5);
INSERT INTO pharmacy (name, address, city, description, rating) values ('Zegin', 'Bulevar Oslobodjenja 32', 'Novi Sad', 'Tradija pre svega, nalazimo se na idealnoj lokaciji', 4.2);
INSERT INTO pharmacy (name, address, city, description, rating) values ('Benu', 'Bulevar Cara Lazara 20', 'Novi Sad', 'Nesto za svakoga', 4.0);

-- KOLICINA LEKOVA U APOTEKAMA

INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity) VALUES ( 1, 1, 200);
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity) VALUES ( 1, 2, 500);
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity) VALUES ( 2, 1, 70);
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity) VALUES ( 2, 3, 45);
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity) VALUES ( 3, 1, 300);
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity) VALUES ( 4, 1, 200);
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity) VALUES ( 4, 3, 80);
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity) VALUES ( 5, 1, 65);
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity) VALUES ( 5, 2, 17);
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity) VALUES ( 5, 3, 40);


-- -- ADMINISTRATORI APOTEKE I NJIHOVA PRAVA POVEZANA

INSERT INTO pharmacy_admin (username, password, first_name, last_name, address, city, phone_number, pharmacy_id) values ('apotekaAdmin1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Jovan', 'Jovanovic', 'Sime Matavulja 8b', 'Pancevo', '065844584', 1);     
insert into pharmacy_admin_authority(user_id, authority_id) VALUES (1,4);

INSERT INTO pharmacy_admin (username, password, first_name, last_name, address, city, phone_number, pharmacy_id) values ('apotekaAdmin2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Stevan', 'Stevanovic', 'Karadjordeva 23', 'Nis', '0650084', 2);     
insert into pharmacy_admin_authority(user_id, authority_id) VALUES (2,4);

INSERT INTO pharmacy_admin (username, password, first_name, last_name, address, city, phone_number, pharmacy_id) values ('apotekaAdmin3@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Slavisa', 'Jelicic', 'Narodnog Fronta 88', 'Novi Sad', '0643284', 3);     
insert into pharmacy_admin_authority(user_id, authority_id) VALUES (3,4);









