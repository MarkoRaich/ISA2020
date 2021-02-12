

-- ROLE I PRAVA PRISTUPA

INSERT INTO authority (name) VALUES ('ROLE_PATIENT');
INSERT INTO authority (name) VALUES ('ROLE_PHARMACIST');
INSERT INTO authority (name) VALUES ('ROLE_DERMATOLOGIST');
INSERT INTO authority (name) VALUES ('ROLE_PHARMACY_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_SYSTEM_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_SUPPLIER');

/*
	SIFRA ZA SVE JE KORISNIKE je 123!
 *  KADA PRODJE 10X KROZ BCRYPT ENKODER POSTANE :
 * '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC' 
**/


-- REGISTROVANI KORISNICI - PACIJENTI I NJIHOVA PRAVA PRISTUPA POVEZANA

INSERT INTO patient (username, password, first_name, last_name, address, city, phone_number, points, penalties) VALUES ('pacijent1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Marko', 'Markovic', 'Mose Pijade 3', 'Beograd', '021553333', 0, 0 );
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

insert into date_time_interval (start_date_time, end_date_time) VALUES ('2019.12.01 08:00','2022.01.10 10:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2018.12.01 08:00','2021.04.10 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2018.08.01 08:00','2020.04.10 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2018.10.22 08:00','2022.04.10 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2020.12.01 08:00','2021.02.17 10:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2020.12.01 08:00','2021.03.20 10:00');
-- KRACI ZA PREGLEDE I SAVETOVANJA
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.20 08:00','2021.2.20 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.15 14:00','2021.2.15 15:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.3 08:00','2021.2.3 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.22 14:00','2021.2.20 15:30');



-- CENE LEKOVA U APOTEKAMA

INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (1, 1, 420.0, 1); -- > interval_id 
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (1, 2, 520.0, 2);
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (2, 2, 320.0, 3);
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (3, 3, 550.0, 4);


-- ADMINISTRATORI APOTEKE I NJIHOVA PRAVA POVEZANA

INSERT INTO pharmacy_admin (username, password, first_name, last_name, phone_number, pharmacy_id, status) VALUES ('apotekaAdmin1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Jovan', 'Jovanovic', '065844584', 1, 'ACTIVE');     
INSERT INTO pharmacy_admin_authority(user_id, authority_id) VALUES (1,4);

INSERT INTO pharmacy_admin (username, password, first_name, last_name,  phone_number, pharmacy_id, status) VALUES ('apotekaAdmin2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Stevan', 'Stevanovic', '0650084', 2, 'ACTIVE');     
INSERT INTO pharmacy_admin_authority(user_id, authority_id) VALUES (2,4);

INSERT INTO pharmacy_admin (username, password, first_name, last_name, phone_number, pharmacy_id, status) VALUES ('apotekaAdmin3@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Slavisa', 'Jelicic', '0643284', 3, 'ACTIVE');     
INSERT INTO pharmacy_admin_authority(user_id, authority_id) VALUES (3,4);


-- FARMACEUTI I NJIHOVA PRAVA POVEZANA

INSERT INTO pharmacist (username, password, first_name, last_name, phone_number, work_hours_from, work_hours_to, rating, status, pharmacy_id, working_status ) VALUES ('farmaceut1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Tijana', 'Dejanovic',  '555333000', '08:00','16:00', 2.4, 'ACTIVE', 1, 'FREE');     
INSERT INTO pharmacist_authority(user_id, authority_id) VALUES (1,2);

INSERT INTO pharmacist (username, password, first_name, last_name, phone_number, work_hours_from, work_hours_to, rating, status, pharmacy_id, working_status ) VALUES ('farmaceut2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Jovana', 'Grbic',  '555444000', '10:00','18:00', 4, 'ACTIVE', 1, 'FREE');     
INSERT INTO pharmacist_authority(user_id, authority_id) VALUES (2,2);

INSERT INTO pharmacist (username, password, first_name, last_name, phone_number, work_hours_from, work_hours_to, rating, status, pharmacy_id, working_status ) VALUES ('farmaceut3@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Zorana', 'Toroman',  '558953000', '14:00','21:00', 3.8, 'ACTIVE', 2, 'FREE');     
INSERT INTO pharmacist_authority(user_id, authority_id) VALUES (3,2);


-- DERMATOLOZI I NJIHOVA PRAVA POVEZANA

INSERT INTO dermatologist (username, password, first_name, last_name, phone_number, rating, status) VALUES ('dermatolog1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Sima', 'Djokic',  '0655584', 4, 'ACTIVE');     
INSERT INTO dermatologist_authority(user_id, authority_id) VALUES (1,3);

INSERT INTO dermatologist (username, password, first_name, last_name, phone_number, rating, status) VALUES ('dermatolog2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Petar', 'Belibrk',  '0658684', 3.2, 'ACTIVE');     
INSERT INTO dermatologist_authority(user_id, authority_id) VALUES (2,3);

INSERT INTO dermatologist (username, password, first_name, last_name,  phone_number, rating, status) VALUES ('dermatolog3@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Stefan', 'Miljic', '06477284', 4.7, 'ACTIVE');     
INSERT INTO dermatologist_authority(user_id, authority_id) VALUES (3,3);


-- DERMATOLOZI I APOTEKE U KOJIMA RADE

-- INSERT INTO pharmacy_dermatologist(pharmacy_id, dermatologist_id) VALUES (1,1);
-- INSERT INTO pharmacy_dermatologist(pharmacy_id, dermatologist_id) VALUES (2,2);
-- INSERT INTO pharmacy_dermatologist(pharmacy_id, dermatologist_id) VALUES (3,3);


-- RADNO VREME DERMATOLOGA I GERUND IZMEDJU APOTEKE I DERMATOLOGA

INSERT INTO derm_work_hours(pharmacy_id, dermatologist_id, status, time_from, time_to) VALUES (1,1, 'ACTIVE', '08:00','16:00');
INSERT INTO derm_work_hours(pharmacy_id, dermatologist_id, status, time_from, time_to) VALUES (1,2, 'ACTIVE', '08:00','12:00');
INSERT INTO derm_work_hours(pharmacy_id, dermatologist_id, status, time_from, time_to) VALUES (2,1, 'ACTIVE', '14:00','21:00');
INSERT INTO derm_work_hours(pharmacy_id, dermatologist_id, status, time_from, time_to) VALUES (2,3, 'ACTIVE', '11:00','17:00');


-- CENA PREGLEDA TIP

-- INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) VALUES (1, 1, 4000.0, 1);
-- INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) VALUES (2, 2, 5000.0, 2);
-- INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) VALUES (3, 3, 4230.0, 3);
-- INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) VALUES (4, 3, 7730.0, 4);
-- INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) VALUES (5, 2, 5670.0, 5);
-- INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) VALUES (6, 1, 1430.0, 1);
-- INSERT INTO examination_price(examination_id, pharmacy_id, price, interval_id) VALUES (7, 1, 2200.0, 1);

INSERT INTO examination_type(name, description, price, pharmacy_id) VALUES ('Opsti pregled', 'Opsti pregled pacijenta', 2000, 1);
INSERT INTO examination_type(name, description, price, pharmacy_id) VALUES ('Pregled sa Dermoskopom ', 'Pregled pacijenta sa Dermoskopom', 3500, 1);
INSERT INTO examination_type(name, description, price, pharmacy_id) VALUES ('Dermoskopija', 'Dermoskopija za pacijenta', 4000, 1);

-- PREGLEDI

INSERT INTO examination( dermatologist_id, patient_id, status, interval_id, pharmacy_id, exam_type_id) values ( 1, null, 0, 7, 1, 1);
INSERT INTO examination( dermatologist_id, patient_id, status, interval_id, pharmacy_id, exam_type_id) values ( 2, null, 0, 7, 1, 2);
INSERT INTO examination( dermatologist_id, patient_id, status, interval_id, pharmacy_id, exam_type_id) values ( 1, null, 0, 8, 1, 1); -- null za pacijenta
INSERT INTO examination( dermatologist_id, patient_id, status, interval_id, pharmacy_id, exam_type_id) values ( 2,    1, 2, 9, 3, 3); -- null ako je predefinisan
INSERT INTO examination( dermatologist_id, patient_id, status, interval_id, pharmacy_id, exam_type_id) values ( 3, 	  1, 3, 10, 2, 1);
INSERT INTO examination( dermatologist_id, patient_id, status, interval_id, pharmacy_id, exam_type_id) values ( 2, 	  1, 4, 7, 3, 1);
INSERT INTO examination( dermatologist_id, patient_id, status, interval_id, pharmacy_id, exam_type_id) values ( 1,    1, 4, 9, 2, 1);
INSERT INTO examination( dermatologist_id, patient_id, status, interval_id, pharmacy_id, exam_type_id) values ( 3,    1, 2, 9, 1, 2); -- postavlja se prilikom zakazivanja pregleda
INSERT INTO examination( dermatologist_id, patient_id, status, interval_id, pharmacy_id, exam_type_id) values ( 3, 	  1, 1, 8, 1, 3);
INSERT INTO examination( dermatologist_id, patient_id, status, interval_id, pharmacy_id, exam_type_id) values ( 2,    1, 3, 9, 3, 3); -- null ako je predefinisan
INSERT INTO examination( dermatologist_id, patient_id, status, interval_id, pharmacy_id, exam_type_id) values ( 3, 	  1, 1, 7, 1, 1);
INSERT INTO examination( dermatologist_id, patient_id, status, interval_id, pharmacy_id, exam_type_id) values ( 2, 	  1, 1, 7, 3, 1);
INSERT INTO examination( dermatologist_id, patient_id, status, interval_id, pharmacy_id, exam_type_id) values ( 1,    1, 4, 8, 2, 1);
INSERT INTO examination( dermatologist_id, patient_id, status, interval_id, pharmacy_id, exam_type_id) values ( 2,    1, 1, 8, 1, 2); -- postavlja se prilikom zakazivanja pregleda
INSERT INTO examination( dermatologist_id, patient_id, status, interval_id, pharmacy_id, exam_type_id) values ( 3, 	  1, 1, 8, 1, 3);


-- SAVETOVANJA

INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Opste Konsultacije', 'Opste konsultacije za pacijenta', 1, 1, 0, 1);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije u vezi lekova', 'Potrebni saveti za uzimanje lekova', 1, 2, 0, 1);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije za zdrav zivot', 'Opste konsultacije u vezi zdravih navika', 2, 1, 2, 1); -- kada je status 2 znaci da pacijent nije postavljen jos
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije ishrane', 'Opste konsultacije za ishranu', 2, 1, 3, 2);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije fizicke aktivnosti', 'Opste konsultacije u vezi fizicke aktivnosti', 2, 1, 3, 2);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije za studente', 'Opste konsultacije za studente farmacije', 2, 2, 1, 2);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije u vezi terapije', 'Opste konsultacije u vezi terapije', 3, 1, 0, 3);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije/Savetovanje', 'Savetovanje', 2, 1, 4, 3);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije specijalne', 'Specijane konsultacije', 2, 1, 4, 4);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije specijalne', 'Specijane konsultacijeeee', 3, 1, 0, 4);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije specijalne', 'Specijane konsultacije sa mogucnoscu pregleda', 2, 1, 2, 4);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije specijalne', 'Specijane konsultacije uz solju kafe', 1, 1, 0, 4);

INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije opste', 'Konsultacije opste', 1, 1, 1, 2);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije za pacijenta', 'Specijane konsultacije za pacijenta', 1, 1, 1, 3);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije specijalne', 'Konsultacije za studente', 1, 1, 1, 4);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije specijalne', 'Konsultacije za decu', 2, 1, 1, 8);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije specijalne', 'Provera lekova', 1, 1, 1, 7);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije specijalne', 'Saveti za lekove', 2, 1, 1, 6);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije specijalne', 'Konsultacije u vreme pavze', 1, 1, 1, 4);

INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije specijelne', 'Konsultacije u vezi sporta', 1, 1, 3, 7);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije specijalne', 'Konsultacije u vezi ishrane', 1, 1, 4, 6);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije specijalne', 'Konsultacije u vezi psihickog zravlja', 1, 1, 4, 5);
INSERT INTO consultation(description, name, pharmacist_id, patient_id, status, interval_id) values ('Konsultacije specijalne', 'Konsultacije u vezi fizickog zdravlja', 1, 1, 3, 3);
-- CENE SAVETOVANJA

INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (1, 1, 4000.0, 3);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (2, 2, 5000.0, 2);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (3, 3, 4230.0, 1);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (4, 3, 7730.0, 5);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (5, 2, 2000.0, 2);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (6, 2, 2350.0, 3);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (7, 2, 5670.0, 4);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (8, 2, 1200.0, 1);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (9, 2, 170.0, 2);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (10, 1, 255.0, 3);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (11, 1, 376.0, 5);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (12, 1, 810.0, 5);

INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (13, 1, 330.0, 5);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (14, 2, 279.0, 6);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (15, 2, 1250.0, 7);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (16, 1, 1000.0, 6);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (17, 2, 557.0, 7);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (18, 1, 450, 2);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (19, 1, 880.0, 4);

INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (20, 1, 920.0, 4);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (21, 2, 390.0, 7);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (22, 2, 1500.0, 5);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (23, 1, 2200.0, 2);

-- REZERVACIJE LEKOVA

INSERT INTO reservation(generated_key, status, drug_id, interval_id, patient_id, pharmacy_id, quantity) VALUES ('12345', 0, 1, 1, 1, 1, 19);
INSERT INTO reservation(generated_key, status, drug_id, interval_id, patient_id, pharmacy_id, quantity) VALUES ('22111', 1, 1, 2, 1, 2, 30);
INSERT INTO reservation(generated_key, status, drug_id, interval_id, patient_id, pharmacy_id, quantity) VALUES ('34522', 2, 1, 3, 1, 3, 21);
INSERT INTO reservation(generated_key, status, drug_id, interval_id, patient_id, pharmacy_id, quantity) VALUES ('21566', 3, 1, 2, 2, 2, 12);
INSERT INTO reservation(generated_key, status, drug_id, interval_id, patient_id, pharmacy_id, quantity) VALUES ('76444', 0, 1, 3, 1, 2, 7);
INSERT INTO reservation(generated_key, status, drug_id, interval_id, patient_id, pharmacy_id, quantity) VALUES ('54433', 1, 1, 1, 2, 3, 8);
INSERT INTO reservation(generated_key, status, drug_id, interval_id, patient_id, pharmacy_id, quantity) VALUES ('44798', 0, 1, 2, 2, 3, 31);


-- PROMOCIJE

INSERT INTO promotion(content, period_id, pharmacy_id) VALUES ('Brufen -20%', 1, 1);
INSERT INTO promotion(content, period_id, pharmacy_id) VALUES ('Paracetamol -30%', 2, 1);
INSERT INTO promotion(content, period_id, pharmacy_id) VALUES ('Aspirin -10%', 2, 2);
INSERT INTO promotion(content, period_id, pharmacy_id) VALUES ('Bromazepam -15%', 3, 3);
INSERT INTO promotion(content, period_id, pharmacy_id) VALUES ('Cafetin -20%', 1, 4);
