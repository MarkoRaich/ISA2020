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
INSERT INTO patient_authority(user_id, authority_id) VALUES (1,1);

INSERT INTO patient (username, password, first_name, last_name, address, city, phone_number, points, penalties) values ('pacijent2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Petar', 'Petrovic', 'Boska Buhe 12', 'Kraljevo', '065772415', 0, 0 );
INSERT INTO patient_authority(user_id, authority_id) VALUES (2,1);

-- LEKOVI

INSERT INTO drug (name, code, type, manufacturer, composition, form, prescription, notes) VALUES ('Brufen', '020150', 'ANALGESIC', 'GALENIKA', 'Ibuprofen, inactive ingredients microcrystalline cellulose, croscarmellose sodium, lactose monohydrate, colloidal anhydrous silica, sodium lauryl sulfate, magnesium stearate, hypromellose, purified talc, titanium dioxide.', 'POWDER', 'WITHOUT_PRESCRIPTION', 'Moze izazvati nezeljena dejstva ukoliko se ne prate uputstva farmaceuta' );
INSERT INTO drug (name, code, type, manufacturer, composition, form, prescription, notes) VALUES ('Paracetamol', '160150', 'ANALGESIC', 'GALENIKA', 'paracetamol, maize starch, potassium sorbate, purified talc, stearic acid, povidone, and soluble starch', 'CAPSULE', 'WITHOUT_PRESCRIPTION', 'Moze izazvati nezeljena dejstva ukoliko se ne prate uputstva farmaceuta' );
INSERT INTO drug (name, code, type, manufacturer, composition, form, prescription, notes) VALUES ('Aspirin', '020180', 'ANALGESIC', 'BAYER BITTERFELD GMBH', 'aspirin', 'CAPSULE', 'WITHOUT_PRESCRIPTION', 'cuvati van domasaja dece, ne mesati sa alkoholom i drugim lekovima' );
INSERT INTO drug (name, code, type, manufacturer, composition, form, prescription, notes) VALUES ('Amoksicilin', '027150', 'ANTIBIOTIC', 'PLIVA HRVATSKA D.O.O.', 'amoksicilin, magnezijum-stearat, celuloza, mikrokristalna PH 102','PILL', 'PRESCRIPTION', 'Ovaj lek je propisan lično Vama i ne smete ga dati drugome. Drugome ovaj lek može da škodi čak i ako ima znake bolesti slične Vašim.' );
INSERT INTO drug (name, code, type, manufacturer, composition, form, prescription, notes) VALUES ('Bromazepam', '024450', 'ANESTHETIC', 'PLIVA HRVATSKA D.O.O.', 'bromazepam, lactose, microcrystalline cellulose (460), talc (553), magnesium stearate (470)','PILL', 'PRESCRIPTION', 'Cuvati van domasaja dece');
INSERT INTO drug (name, code, type, manufacturer, composition, form, prescription, notes) VALUES ('Probiotic', '020450', 'ANTIBIOTIC', 'HEMOFARM AD VRŠAC', 'L. helveticus, L. rhamnosus, B. longum i S. boulardii', 'CAPSULE', 'WITHOUT_PRESCRIPTION', 'Uzimati u odredjeno vreme' );
INSERT INTO drug (name, code, type, manufacturer, composition, form, prescription, notes) VALUES ('Vitamin C', '020960', 'SUPPLEMENT', 'HEMOFARM AD VRŠAC','vitamin C', 'POWDER', 'WITHOUT_PRESCRIPTION', 'Poboljsava imunitet uzimati u ogranicenim kolicinama' );

-- ZAMENSKI LEKOVI
INSERT INTO replacement_drug(drug_id, repl_drug_id) VALUES (1,2);
INSERT INTO replacement_drug(drug_id, repl_drug_id) VALUES (1,3);


-- APOTEKE

INSERT INTO pharmacy (name, address,description, rating) values ('Jankovic', 'Novi Sad Narodnog Fronta 1', 'Apoteka Jankovic ima veliki izbor preparata', 4.5);
INSERT INTO pharmacy (name, address, description, rating) values ('Zegin', 'Novi Sad Bulevar Oslobodjenja 32', 'Tradija pre svega, nalazimo se na idealnoj lokaciji', 4.2);
INSERT INTO pharmacy (name, address, description, rating) values ('Benu', 'Novi Sad Bulevar Cara Lazara 20', 'Nesto za svakoga', 4.0);

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
INSERT INTO pharmacy_admin_authority(user_id, authority_id) VALUES (1,4);

INSERT INTO pharmacy_admin (username, password, first_name, last_name, address, city, phone_number, pharmacy_id) values ('apotekaAdmin2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Stevan', 'Stevanovic', 'Karadjordeva 23', 'Nis', '0650084', 2);     
INSERT INTO pharmacy_admin_authority(user_id, authority_id) VALUES (2,4);

INSERT INTO pharmacy_admin (username, password, first_name, last_name, address, city, phone_number, pharmacy_id) values ('apotekaAdmin3@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Slavisa', 'Jelicic', 'Narodnog Fronta 88', 'Novi Sad', '0643284', 3);     
INSERT INTO pharmacy_admin_authority(user_id, authority_id) VALUES (3,4);









