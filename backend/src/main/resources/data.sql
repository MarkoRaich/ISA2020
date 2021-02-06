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


-- DATE TIME INTERVAL
-- insert into date_time_interval (end_date_time, start_date_time) values ('10-01-2021 10:00', '31-12-2020 08:00');
-- insert into date_time_interval (end_date_time, start_date_time) values ('20-01-2021 10:00', '31-12-2020 08:00');
-- insert into date_time_interval (end_date_time, start_date_time) values ('10-01-2021 10:00', '31-12-2020 08:00');
insert into date_time_interval (start_date_time, end_date_time) values ('2019.12.01 08:00','2020.01.10 10:00');
insert into date_time_interval (start_date_time, end_date_time) values ('2018.12.01 08:00','2021.04.10 09:00');
insert into date_time_interval (start_date_time, end_date_time) values ('2018.08.01 08:00','2020.04.10 09:00');
insert into date_time_interval (start_date_time, end_date_time) values ('2018.10.22 08:00','2022.04.10 09:00');
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


-- DERMATOLOZI I NJIHOVA PRAVA POVEZANA
INSERT INTO dermatologist (username, password, first_name, last_name, work_hours_from, work_hours_to, phone_number, rating, status) VALUES ('dermatolog1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Sima', 'Djokic', '07:30','20:00', '0655584', 4, 'ACTIVE');     
INSERT INTO dermatologist_authority(user_id, authority_id) VALUES (1,3);

INSERT INTO dermatologist (username, password, first_name, last_name, work_hours_from, work_hours_to, phone_number, rating, status) VALUES ('dermatolog2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Petar', 'Belibrk', '09:30','18:00', '0658684', 3.2, 'ACTIVE');     
INSERT INTO dermatologist_authority(user_id, authority_id) VALUES (2,3);

INSERT INTO dermatologist (username, password, first_name, last_name, work_hours_from, work_hours_to, phone_number, rating, status) VALUES ('dermatolog3@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Stefan', 'Miljic', '08:00','14:00', '06477284', 4.7, 'ACTIVE');     
INSERT INTO dermatologist_authority(user_id, authority_id) VALUES (3,3);

-- DERMATOLOZI I APOTEKE U KOJIMA RADE
INSERT INTO pharmacy_dermatologist(pharmacy_id, dermatologist_id) VALUES (1,1);
INSERT INTO pharmacy_dermatologist(pharmacy_id, dermatologist_id) VALUES (2,2);
INSERT INTO pharmacy_dermatologist(pharmacy_id, dermatologist_id) VALUES (3,3);


-- FARMACEUTI I NJIHOVA PRAVA POVEZANA
INSERT INTO pharmacist (username, password, first_name, last_name, work_hour_from, work_hour_to, phone_number, rating, status, pharmacy_id) VALUES ('farmaceut1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Andrej', 'Dragojevic', '07:30','20:00', '0655584', 4.1, 'ACTIVE', 1);     
INSERT INTO pharmacist_authority(user_id, authority_id) VALUES (1,2);

INSERT INTO pharmacist (username, password, first_name, last_name, work_hour_from, work_hour_to, phone_number, rating, status, pharmacy_id) VALUES ('farmaceut2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Milica', 'Belibrk', '09:30','18:00', '0658684', 3.6, 'ACTIVE', 2);     
INSERT INTO pharmacist_authority(user_id, authority_id) VALUES (2,2);

INSERT INTO pharmacist (username, password, first_name, last_name, work_hour_from, work_hour_to, phone_number, rating, status, pharmacy_id) VALUES ('farmaceut3@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Gojko', 'Gojkovic', '08:00','14:00', '06477284', 4.4, 'ACTIVE', 3);     
INSERT INTO pharmacist_authority(user_id, authority_id) VALUES (3,2);


-- EXAMINATION 
-- INSERT INTO examination(description, name, complaint_id, dermatologist_id, examination_report_id, interval_id, patient_id, pharmacy_id)  
INSERT INTO examination(description, name, dermatologist_id, patient_id, status) values ('Opsti pregled', 'Opsti pregled pacijenta', 1, 1, 1);
INSERT INTO examination(description, name, dermatologist_id, patient_id, status) values ('Pregled sa Dermoskopom ', 'Pregled pacijenta sa Dermoskopom', 2, 2, 0);
INSERT INTO examination(description, name, dermatologist_id, patient_id, status) values ('PH nalaz', 'PH nalaz za pacijenta', 1, 2, 2);
INSERT INTO examination(description, name, dermatologist_id, patient_id, status) values ('Dermoskopija', 'Dermoskopija za pacijenta', 2, 1, 3);
INSERT INTO examination(description, name, dermatologist_id, patient_id, status) values ('Opsta hirurgija', 'Opsta hirurgija', 3, 1, 3);
INSERT INTO examination(description, name, dermatologist_id, patient_id, status) values ('Opsta pregled i Hirurgija', 'Opsta hirurgija prilikom pregleda pacijenta', 2, 1, 4);
INSERT INTO examination(description, name, dermatologist_id, patient_id, status) values ('Pregled pacijenta', 'Pregled pacijenta', 1, 1, 4);


-- EXAMINATION PRICE
INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) 
values (1, 1, 4000.0, 1);
INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) 
values (2, 2, 5000.0, 2);
INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) 
values (3, 3, 4230.0, 3);
INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) 
values (4, 3, 7730.0, 4);
INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) 
values (5, 2, 5670.0, 5);

-- CONSULTATION
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status) values ('Opste Konsultacije', 'Opste konsultacije za pacijenta', 1, 1, 1);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status) values ('Konsultacije u vezi lekova', 'Potrebni saveti za uzimanje lekova', 1, 2, 0);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status) values ('Konsultacije za zdrav zivot', 'Opste konsultacije u vezi zdravih navika', 2, 2, 2);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status) values ('Konsultacije ishrane', 'Opste konsultacije za ishranu', 2, 1, 3);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status) values ('Konsultacije fizicke aktivnosti', 'Opste konsultacije u vezi fizicke aktivnosti', 2, 1, 3);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status) values ('Konsultacije za studente', 'Opste konsultacije za studente farmacije', 2, 1, 1);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status) values ('Konsultacije u vezi terapije', 'Opste konsultacije u vezi terapije', 2, 1, 3);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status) values ('Konsultacije/Savetovanje', 'Savetovanje', 2, 1, 4);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status) values ('Konsultacije specijalne', 'Specijane konsultacije', 1, 1, 4);

-- CONSULTATION PRICE
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) 
values (1, 1, 4000.0, 3);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) 
values (2, 2, 5000.0, 2);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) 
values (3, 3, 4230.0, 1);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) 
values (4, 3, 7730.0, 5);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) 
values (5, 2, 2000.0, 2);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) 
values (6, 2, 2350.0, 3);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) 
values (7, 2, 5670.0, 4);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) 
values (8, 2, 1200.0, 1);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) 
values (9, 2, 170.0, 5);

-- RESERVATION
INSERT INTO reservation(generated_key, status, drug_id, interval_id, patient_id, pharmacy_id)
values ('12345', 0, 1, 1, 1, 1);
INSERT INTO reservation(generated_key, status, drug_id, interval_id, patient_id, pharmacy_id)
values ('22111', 1, 1, 2, 1, 2);
INSERT INTO reservation(generated_key, status, drug_id, interval_id, patient_id, pharmacy_id)
values ('34522', 2, 1, 3, 1, 3);
INSERT INTO reservation(generated_key, status, drug_id, interval_id, patient_id, pharmacy_id)
values ('21566', 3, 1, 2, 2, 2);
INSERT INTO reservation(generated_key, status, drug_id, interval_id, patient_id, pharmacy_id)
values ('76444', 0, 1, 3, 1, 2);
INSERT INTO reservation(generated_key, status, drug_id, interval_id, patient_id, pharmacy_id)
values ('54433', 1, 1, 1, 2, 3);
INSERT INTO reservation(generated_key, status, drug_id, interval_id, patient_id, pharmacy_id)
values ('44798', 0, 1, 2, 2, 3);

-- PROMOTION
INSERT INTO promotion(content, period_id, pharmacy_id) 
values ('Brufen -20%', 1, 1);
INSERT INTO promotion(content, period_id, pharmacy_id) 
values ('Paracetamol -30%', 2, 1);
INSERT INTO promotion(content, period_id, pharmacy_id) 
values ('Aspirin -10%', 2, 2);
INSERT INTO promotion(content, period_id, pharmacy_id) 
values ('Bromazepam -15%', 3, 3);
INSERT INTO promotion(content, period_id, pharmacy_id) 
values ('Cafetin -20%', 1, 4);
