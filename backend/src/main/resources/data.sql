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

INSERT INTO patient (username, password, first_name, last_name, address, city, phone_number, points, penalties) VALUES ('pacijent1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Marko', 'Markovic', 'Mose Pijade 3', 'Beograd', '02155333', 0, 0 );
INSERT INTO patient_authority(user_id, authority_id) VALUES (1,1);

INSERT INTO patient (username, password, first_name, last_name, address, city, phone_number, points, penalties) VALUES ('pacijent2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Petar', 'Petrovic', 'Boska Buhe 12', 'Kraljevo', '065772415', 0, 0 );
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

INSERT INTO pharmacy (name, address,description, rating) VALUES ('Jankovic', 'Novi Sad Narodnog Fronta 1', 'Apoteka Jankovic ima veliki izbor preparata', 4.5);
INSERT INTO pharmacy (name, address, description, rating) VALUES ('Zegin', 'Novi Sad Bulevar Oslobodjenja 32', 'Tradicija pre svega, nalazimo se na idealnoj lokaciji', 4.2);
INSERT INTO pharmacy (name, address, description, rating) VALUES ('Benu', 'Novi Sad Bulevar Cara Lazara 20', 'Nesto za svakoga', 4.0);
INSERT INTO pharmacy (name, address, description, rating) VALUES ('Betty', 'Beograd Zemunska 50', 'Vama na usluzi', 2.5);
INSERT INTO pharmacy (name, address, description, rating) VALUES ('Zlatni Lav', 'Zrenjanin Balkanska 20', 'Jel imate mozda...? Imamo.', 3.8);

-- KOLICINA LEKOVA U APOTEKAMA

INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity, status) VALUES ( 1, 1, 200, 'ACTIVE');
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity, status) VALUES ( 1, 2, 500, 'ACTIVE');
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity, status) VALUES ( 2, 1, 70, 'ACTIVE');
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity, status) VALUES ( 2, 3, 45, 'ACTIVE');
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity, status) VALUES ( 3, 1, 300, 'ACTIVE');
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity, status) VALUES ( 4, 1, 200, 'DELETED');
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity, status) VALUES ( 4, 3, 80, 'ACTIVE');
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity, status) VALUES ( 5, 1, 65, 'DELETED');
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity, status) VALUES ( 5, 2, 17, 'ACTIVE');
INSERT INTO drug_quantity (drug_id, pharmacy_id, quantity, status) VALUES ( 5, 3, 40, 'ACTIVE');


-- DATE TIME INTERVAL
-- insert into date_time_interval (end_date_time, start_date_time) values ('10-01-2021 10:00', '31-12-2020 08:00');
-- insert into date_time_interval (end_date_time, start_date_time) values ('20-01-2021 10:00', '31-12-2020 08:00');
-- insert into date_time_interval (end_date_time, start_date_time) values ('10-01-2021 10:00', '31-12-2020 08:00');
insert into date_time_interval (start_date_time, end_date_time) values ('2019.12.01 08:00','2020.01.10 10:00');
insert into date_time_interval (start_date_time, end_date_time) values ('2020.12.01 08:00','2021.04.10 10:00');
insert into date_time_interval (start_date_time, end_date_time) values ('2020.12.01 08:00','2021.02.17 10:00');
insert into date_time_interval (start_date_time, end_date_time) values ('2020.12.01 08:00','2021.03.20 10:00');
-- insert into date_time_interval (start_date_time, end_date_time) values ('31-12-2019 08:00','10-01-2020 10:00');

-- CENE LEKOVA U APOTEKAMA
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) values (1, 1, 420.0, 1); -- interval_id 
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) values (1, 2, 520.0, 2);
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) values (2, 2, 320.0, 3);
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) values (3, 3, 550.0, 4);


-- ADMINISTRATORI APOTEKE I NJIHOVA PRAVA POVEZANA

INSERT INTO pharmacy_admin (username, password, first_name, last_name, phone_number, pharmacy_id, status) VALUES ('apotekaAdmin1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Jovan', 'Jovanovic', '065844584', 1, 'ACTIVE');     
INSERT INTO pharmacy_admin_authority(user_id, authority_id) VALUES (1,4);

INSERT INTO pharmacy_admin (username, password, first_name, last_name,  phone_number, pharmacy_id, status) VALUES ('apotekaAdmin2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Stevan', 'Stevanovic', '0650084', 2, 'ACTIVE');     
INSERT INTO pharmacy_admin_authority(user_id, authority_id) VALUES (2,4);

INSERT INTO pharmacy_admin (username, password, first_name, last_name, phone_number, pharmacy_id, status) VALUES ('apotekaAdmin3@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Slavisa', 'Jelicic', '0643284', 3, 'ACTIVE');     
INSERT INTO pharmacy_admin_authority(user_id, authority_id) VALUES (3,4);

-- FARMACEUTI
INSERT INTO pharmacist (username, password, first_name, last_name, phone_number, work_hours_from, work_hours_to, rating, status, pharmacy_id ) VALUES ('farmaceut1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Tijana', 'Dejanovic',  '555333000', '08:00','16:00', 2.4, 'ACTIVE', 1);     
INSERT INTO pharmacist_authority(user_id, authority_id) VALUES (1,2);

INSERT INTO pharmacist (username, password, first_name, last_name, phone_number, work_hours_from, work_hours_to, rating, status, pharmacy_id ) VALUES ('farmaceut2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Jovana', 'Grbic',  '555444000', '10:00','18:00', 4, 'ACTIVE', 1);     
INSERT INTO pharmacist_authority(user_id, authority_id) VALUES (2,2);

INSERT INTO pharmacist (username, password, first_name, last_name, phone_number, work_hours_from, work_hours_to, rating, status, pharmacy_id ) VALUES ('farmaceut3@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Zorana', 'Toroman',  '558953000', '14:00','21:00', 3.8, 'ACTIVE', 2);     
INSERT INTO pharmacist_authority(user_id, authority_id) VALUES (3,2);


-- DERMATOLOZI I NJIHOVA PRAVA POVEZANA
INSERT INTO dermatologist (username, password, first_name, last_name, phone_number, rating, status) VALUES ('dermatolog1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Sima', 'Djokic',  '0655584', 4, 'ACTIVE');     
INSERT INTO dermatologist_authority(user_id, authority_id) VALUES (1,3);

INSERT INTO dermatologist (username, password, first_name, last_name, phone_number, rating, status) VALUES ('dermatolog2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Petar', 'Belibrk',  '0658684', 3.2, 'ACTIVE');     
INSERT INTO dermatologist_authority(user_id, authority_id) VALUES (2,3);

INSERT INTO dermatologist (username, password, first_name, last_name,  phone_number, rating, status) VALUES ('dermatolog3@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Stefan', 'Miljic', '06477284', 4.7, 'ACTIVE');     
INSERT INTO dermatologist_authority(user_id, authority_id) VALUES (3,3);

-- DERMATOLOZI I APOTEKE U KOJIMA RADE
--INSERT INTO pharmacy_dermatologist(pharmacy_id, dermatologist_id) VALUES (1,1);
--INSERT INTO pharmacy_dermatologist(pharmacy_id, dermatologist_id) VALUES (2,2);
--INSERT INTO pharmacy_dermatologist(pharmacy_id, dermatologist_id) VALUES (3,3);


-- EXAMINATION 
-- INSERT INTO examination(description, name, complaint_id, dermatologist_id, examination_report_id, interval_id, patient_id, pharmacy_id)  
INSERT INTO examination(description, name, dermatologist_id, patient_id) values ('Opsti pregled', 'Opsti pregled pacijenta', 1, 1);
INSERT INTO examination(description, name, dermatologist_id, patient_id) values ('Pregled sa Dermoskopom ', 'Pregled pacijenta sa Dermoskopom', 2, 2);
INSERT INTO examination(description, name, dermatologist_id, patient_id) values ('PH nalaz', 'PH nalaz za pacijenta', 1, 2);
INSERT INTO examination(description, name, dermatologist_id, patient_id) values ('Dermoskopija', 'Dermoskopija za pacijenta', 2, 1);
INSERT INTO examination(description, name, dermatologist_id, patient_id) values ('Opsta hirurgija', 'Opsta hirurgija', 3, 1);

-- EXAMINATION PRICE
INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) 
values (1, 1, 4000.0, 1);
INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) 
values (2, 2, 5000.0, 2);
INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) 
values (3, 3, 4230.0, 2);
INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) 
values (4, 3, 7730.0, 1);
INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) 
values (5, 2, 5670.0, 3);