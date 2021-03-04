

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

INSERT INTO patient (username, password, first_name, last_name, address, city, phone_number, points, penalties) VALUES ('pacijent1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Marko', 'Markovic', 'Mose Pijade 3', 'Beograd', '021553333', 2, 0 );
INSERT INTO patient_authority(user_id, authority_id) VALUES (1,1);

INSERT INTO patient (username, password, first_name, last_name, address, city, phone_number, points, penalties) VALUES ('pacijent2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Petar', 'Petrovic', 'Boska Buhe 12', 'Kraljevo', '065772415', 3, 0 );
INSERT INTO patient_authority(user_id, authority_id) VALUES (2,1);

INSERT INTO patient (username, password, first_name, last_name, address, city, phone_number, points, penalties) VALUES ('pacijent3@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Stefa', 'Jokic', 'Prvih Vojvodjanskih Brigada 12', 'Novi Sad', '0678435415', 2, 1 );
INSERT INTO patient_authority(user_id, authority_id) VALUES (3,1);

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

INSERT INTO pharmacy (name, address,description, rating) VALUES ('Jankovic', 'Novi Sad Rumenacka 15', 'Apoteka Jankovic ima veliki izbor preparata', 4.5);
INSERT INTO pharmacy (name, address, description, rating) VALUES ('Zegin', 'Novi Sad Bulevar Oslobodjenja 43', 'Tradicija pre svega, nalazimo se na idealnoj lokaciji', 4.2);
INSERT INTO pharmacy (name, address, description, rating) VALUES ('Benu', 'Novi Sad Bulevar Mihajla Pupina 9', 'Nesto za svakoga', 4.0);


-- APOTEKA I PACIJETNI PRETPLATIOCI NA PROMOCIJE

INSERT INTO pharmacy_subscriber (subscriber_id, pharmacy_id) VALUES (1,1);
INSERT INTO pharmacy_subscriber (subscriber_id, pharmacy_id) VALUES (1,2);
INSERT INTO pharmacy_subscriber (subscriber_id, pharmacy_id) VALUES (2,1);
INSERT INTO pharmacy_subscriber (subscriber_id, pharmacy_id) VALUES (2,3);
INSERT INTO pharmacy_subscriber (subscriber_id, pharmacy_id) VALUES (3,1);


-- KOLICINA LEKOVA U APOTEKAMA

INSERT INTO drug_quantity (pharmacy_id, drug_id, quantity, status) VALUES ( 1, 1, 200, 'ACTIVE');
INSERT INTO drug_quantity (pharmacy_id, drug_id, quantity, status) VALUES ( 1, 2, 500, 'ACTIVE');
INSERT INTO drug_quantity (pharmacy_id, drug_id, quantity, status) VALUES ( 1, 3, 70, 'ACTIVE');
INSERT INTO drug_quantity (pharmacy_id, drug_id, quantity, status) VALUES ( 1, 4, 45, 'DELETED');
INSERT INTO drug_quantity (pharmacy_id, drug_id, quantity, status) VALUES ( 1, 5, 300, 'ACTIVE');

INSERT INTO drug_quantity (pharmacy_id, drug_id, quantity, status) VALUES ( 2, 1, 200, 'DELETED');
INSERT INTO drug_quantity (pharmacy_id, drug_id, quantity, status) VALUES ( 2, 2, 300, 'ACTIVE');
INSERT INTO drug_quantity (pharmacy_id, drug_id, quantity, status) VALUES ( 2, 3, 200, 'DELETED');
INSERT INTO drug_quantity (pharmacy_id, drug_id, quantity, status) VALUES ( 2, 4, 300, 'ACTIVE');
INSERT INTO drug_quantity (pharmacy_id, drug_id, quantity, status) VALUES ( 2, 7, 200, 'DELETED');

INSERT INTO drug_quantity (pharmacy_id, drug_id, quantity, status) VALUES ( 3, 4, 80, 'ACTIVE');
INSERT INTO drug_quantity (pharmacy_id, drug_id, quantity, status) VALUES ( 3, 5, 65, 'DELETED');
INSERT INTO drug_quantity (pharmacy_id, drug_id, quantity, status) VALUES ( 3, 6, 17, 'ACTIVE');
INSERT INTO drug_quantity (pharmacy_id, drug_id, quantity, status) VALUES ( 3, 7, 40, 'ACTIVE');


-- DATE TIME INTERVAL

-- intervali za cenovnike
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2019.12.01 08:00', '2020.06.01 10:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2020.06.02 08:00', '2021.06.01 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.06.02 08:00', '2022.02.01 10:00');
-- intervali za preglede i savetovanja 2021
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.1.20 08:00', '2021.1.20 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.1.15 14:00', '2021.1.15 15:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.1.3 08:00',  '2021.1.3 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.1.22 14:00', '2021.1.22 15:30');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.10 14:00', '2021.2.10 15:30');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.20 08:00', '2021.2.20 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.15 14:00', '2021.2.15 15:00'); --10
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.3 08:00',  '2021.2.3 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.20 08:00', '2021.2.20 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.15 10:30', '2021.2.15 11:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.3.3 08:00',  '2021.3.3 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.3.20 08:00', '2021.3.20 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.3.20 09:00', '2021.3.20 10:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.3.20 10:00', '2021.3.20 11:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.3.16 14:00', '2021.3.16 15:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.3.16 15:00', '2021.3.16 16:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.3.16 16:00', '2021.3.16 17:00'); --20
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.4.3 08:00',  '2021.4.3 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.4.20 08:00', '2021.4.20 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.4.15 14:00', '2021.4.15 15:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.5.3 08:00',  '2021.5.3 09:00');
-- stari intervali za preglede i savetovanja
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2020.3.11 10:30', '2020.3.11 11:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2020.3.3 08:00',  '2020.3.3 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2020.3.20 08:00', '2020.3.20 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2019.4.20 09:00', '2019.4.20 10:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2019.9.20 10:00', '2019.9.20 11:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2019.7.16 14:00', '2019.7.16 15:00'); --30
--intervali za rezervacije lekova 5-6 dana
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.1.22 9:00', '2021.1.27 15:30');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.1.10 14:00', '2021.1.15 15:30');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.20 08:00', '2021.2.24 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.17 14:00', '2021.2.24 15:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.3 08:00',  '2021.2.11 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.14 08:00', '2021.2.17 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.15 10:30', '2021.2.19 11:00');
--stari intervali za rezervacije lekova
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2020.1.22 9:00', '2020.1.27 15:30');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2020.4.10 14:00', '2020.4.15 15:30');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2020.6.17 14:00', '2020.6.15 15:30'); --40
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2019.2.20 08:00', '2019.2.24 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2019.8.20 08:00', '2019.8.24 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2019.11.24 08:00', '2019.11.29 09:00');
-- interval za godisnji odmor
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.6.17 14:00', '2021.6.15 15:30');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.2.20 08:00', '2021.2.24 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.8.20 08:00', '2021.8.24 09:00');
insert into date_time_interval (start_date_time, end_date_time) VALUES ('2021.11.24 08:00', '2021.11.29 09:00');

-- CENE LEKOVA U APOTEKAMA - CENOVNICI

INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (1, 1, 420.0, 3); 
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (2, 1, 300.0, 3);
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (3, 1, 1000.0, 3);
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (4, 1, 550.0, 3);
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (5, 1, 799.0, 3);
 
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (1, 1, 520.0, 2);
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (2, 1, 350.0, 2);
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (3, 1, 860.0, 2);
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (4, 1, 420.0, 2); 
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (5, 1, 1399.0, 2);

INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (1, 1, 359.0, 1);
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (2, 1, 590.0, 1);
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (3, 1, 490.0, 1);
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (4, 1, 590.0, 1);
INSERT INTO drug_price (drug_id, pharmacy_id, price, interval_id) VALUES (7, 1, 390.0, 1);


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

INSERT INTO dermatologist (username, password, first_name, last_name,  phone_number, rating, status) VALUES ('dermatolog4@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Zoran', 'Cvetic', '064785484', 1.6, 'ACTIVE');     
INSERT INTO dermatologist_authority(user_id, authority_id) VALUES (4,3);

-- RADNO VREME DERMATOLOGA I GERUND IZMEDJU APOTEKE I DERMATOLOGA

INSERT INTO dermatologist_work_hours(dermatologist_id, pharmacy_id, status, time_from, time_to) VALUES (1, 1, 'ACTIVE', '08:00', '11:00');
INSERT INTO dermatologist_work_hours(dermatologist_id, pharmacy_id, status, time_from, time_to) VALUES (1, 2, 'ACTIVE', '12:30', '17:30');
INSERT INTO dermatologist_work_hours(dermatologist_id, pharmacy_id, status, time_from, time_to) VALUES (2, 1, 'ACTIVE', '14:00', '21:00');
INSERT INTO dermatologist_work_hours(dermatologist_id, pharmacy_id, status, time_from, time_to) VALUES (3, 2, 'ACTIVE', '11:00', '17:00');
INSERT INTO dermatologist_work_hours(dermatologist_id, pharmacy_id, status, time_from, time_to) VALUES (3, 3, 'ACTIVE', '18:00', '19:00');


-- CENA PREGLEDA TIP

INSERT INTO examination_type(name, description, price, pharmacy_id) VALUES ('Opsti pregled', 'Opsti pregled pacijenta', 2000, 1);
INSERT INTO examination_type(name, description, price, pharmacy_id) VALUES ('Pregled sa Dermoskopom ', 'Pregled pacijenta sa Dermoskopom', 3500, 1);
INSERT INTO examination_type(name, description, price, pharmacy_id) VALUES ('Dermoskopija', 'Dermoskopija za pacijenta', 4000, 1);

INSERT INTO examination_type(name, description, price, pharmacy_id) VALUES ('Opsti pregled', 'Opsti pregled pacijenta', 1450, 2);
INSERT INTO examination_type(name, description, price, pharmacy_id) VALUES ('Pregled sa Dermoskopom ', 'Pregled pacijenta sa Dermoskopom', 5000, 2);
INSERT INTO examination_type(name, description, price, pharmacy_id) VALUES ('Dermoskopija', 'Dermoskopija za pacijenta', 2500, 2);


-- PREGLEDI
-- prosli
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 1,    1, 1, 4, 'DONE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 1,    2, 1, 5, 'DONE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 1,    2, 1, 6, 'DONE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 1,    1, 2, 7, 'DONE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 2,    1, 2, 8, 'DONE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 2,    2, 3, 9, 'DONE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 2,    1, 1, 10, 'DONE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 2,    2, 2, 11, 'DONE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 1,    2, 3, 12, 'DONE'); 
--slobodni
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 1, null, 1, 14,  'AVAILABLE'); --null od pacijenta znaci slobodan termin
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 1, null, 1, 15,  'AVAILABLE');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 1, null, 1, 16,  'AVAILABLE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 2, null, 2, 17,  'AVAILABLE');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 2, null, 2, 18,  'AVAILABLE');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 2, null, 2, 19,  'AVAILABLE');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 1, null, 3, 16,  'AVAILABLE'); 
--rezervisani i otkazani
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 2,    1, 1, 21,  'BOOKED'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 1,    2, 3, 22,  'BOOKED');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 1, 	 1, 2, 23, 'CANCELED');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 2, 	 2, 1, 24,  'BOOKED');
--stari pregledi odradjeni
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 1,    2, 1, 30, 'DONE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 1,    2, 1, 29, 'DONE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 1,    1, 2, 28, 'DONE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 2,    1, 2, 27, 'DONE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 2,    2, 3, 26, 'DONE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 1, 2,    2, 2, 25, 'DONE'); 
-- druge apoteke
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 2, 1, null, 2, 9, 'AVAILABLE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 2, 3, null, 1, 8, 'AVAILABLE');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 2, 1, null, 3, 9, 'AVAILABLE'); 
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 2, 3, 	 1, 1, 7, 'BOOKED');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 2, 1, 	 1, 1, 7, 'BOOKED');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 2, 3,    1, 4, 8, 'CANCELED');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 2, 3,    1, 1, 11, 'DONE'); 

INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 3, 3, null, 1, 8, 'AVAILABLE');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 3, 3, null, 3, 8, 'AVAILABLE');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 3, 3, null, 3, 4, 'AVAILABLE');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 3, 3,    1, 3, 9, 'BOOKED');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 3, 3,    1, 3, 5, 'BOOKED');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 3, 3,    1, 3, 6, 'CANCELED');
INSERT INTO examination( pharmacy_id, dermatologist_id, patient_id, exam_type_id, interval_id, status) VALUES ( 3, 3,    1, 3, 11, 'DONE');

-- SAVETOVANJA

INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (1, 1, 25, 'DONE', 'Opste Konsultacije', 'Opste konsultacije za pacijenta');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (1, 1, 26, 'DONE', 'Konsultacije u vezi lekova', 'Potrebni saveti za uzimanje lekova');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (1, 2, 27, 'DONE', 'Konsultacije za zdrav zivot', 'Opste konsultacije u vezi zdravih navika');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (1, 2, 28, 'DONE', 'Konsultacije ishrane', 'Opste konsultacije za ishranu');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (2, 1, 29, 'DONE', 'Konsultacije fizicke aktivnosti', 'Opste konsultacije u vezi fizicke aktivnosti');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (2, 1, 30, 'DONE', 'Konsultacije za studente', 'Opste konsultacije za studente farmacije');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (2, 2, 18, 'AVAILABLE', 'Konsultacije u vezi terapije', 'Opste konsultacije u vezi terapije');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (2, 2, 19, 'AVAILABLE', 'Konsultacije/Savetovanje', 'Savetovanje');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (1, 2, 20, 'AVAILABLE', 'Konsultacije specijalne', 'Specijane konsultacije');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (2, 2, 21, 'AVAILABLE', 'Konsultacije specijalne', 'Specijane konsultacijeeee');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (2, 1, 22, 'AVAILABLE', 'Konsultacije specijalne', 'Specijane konsultacije sa mogucnoscu pregleda');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (2, 2, 23, 'AVAILABLE', 'Konsultacije specijalne', 'Specijane konsultacije uz solju kafe');

INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (2, 1, 24, 'AVAILABLE', 'Konsultacije opste', 'Konsultacije opste');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (1, 1, 8, 'AVAILABLE', 'Konsultacije za pacijenta', 'Specijane konsultacije za pacijenta');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (1, 2, 9, 'AVAILABLE', 'Konsultacije specijalne', 'Konsultacije za studente');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (2, 1, 10, 'AVAILABLE', 'Konsultacije specijalne', 'Konsultacije za decu');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (1, 1, 11, 'AVAILABLE', 'Konsultacije specijalne', 'Provera lekova');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (1, 2, 12, 'PREDEF_BOOKED', 'Konsultacije specijalne', 'Saveti za lekove');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (1, 2, 13, 'PREDEF_BOOKED', 'Konsultacije specijalne', 'Konsultacije u vreme pauze');

INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (1, 1, 14, 'PREDEF_BOOKED', 'Konsultacije specijelne', 'Konsultacije u vezi sporta');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (2, 2, 15, 'PREDEF_BOOKED', 'Konsultacije specijalne', 'Konsultacije u vezi ishrane');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (2, 1, 16, 'PREDEF_BOOKED', 'Konsultacije specijalne', 'Konsultacije u vezi psihickog zravlja');
INSERT INTO consultation(pharmacist_id, patient_id, interval_id, status, name, description) VALUES (2, 2, 17, 'PREDEF_BOOKED', 'Konsultacije specijalne', 'Konsultacije u vezi fizickog zdravlja');


-- CENE SAVETOVANJA

INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (1, 1, 4000.0, 3);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (2, 1, 5000.0, 2);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (3, 1, 4230.0, 1);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (4, 1, 7730.0, 2);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (5, 1, 2000.0, 2);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (6, 1, 2350.0, 3);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (7, 1, 5670.0, 1);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (8, 1, 1200.0, 1);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (9, 1, 170.0, 2);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (10, 1, 255.0, 3);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (11, 1, 376.0, 1);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (12, 1, 810.0, 2);

INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (13, 1, 330.0, 2);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (14, 1, 279.0, 3);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (15, 1, 1250.0, 1);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (16, 1, 1000.0, 1);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (17, 1, 557.0, 2);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (18, 1, 450, 2);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (19, 1, 880.0, 3);

INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (20, 1, 920.0, 3);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (21, 1, 390.0, 3);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (22, 1, 1500.0, 2);
INSERT INTO consultation_price(consultation_id, pharmacy_id, price, interval_id) VALUES (23, 1, 2200.0, 2);


-- REZERVACIJE LEKOVA

INSERT INTO reservation(pharmacy_id, drug_id, patient_id, quantity, interval_id, generated_key, status, selling_price) VALUES (1, 1, 1, 5, 31, '12345', 'COMPLETED', 550);
INSERT INTO reservation(pharmacy_id, drug_id, patient_id, quantity, interval_id, generated_key, status, selling_price) VALUES (1, 4, 1, 2, 32, '17745', 'COMPLETED', 790);
INSERT INTO reservation(pharmacy_id, drug_id, patient_id, quantity, interval_id, generated_key, status, selling_price) VALUES (1, 6, 2, 15, 33, '98545', 'COMPLETED', 495);
INSERT INTO reservation(pharmacy_id, drug_id, patient_id, quantity, interval_id, generated_key, status, selling_price) VALUES (1, 1, 1, 49, 34, '54345', 'COMPLETED', 399);
INSERT INTO reservation(pharmacy_id, drug_id, patient_id, quantity, interval_id, generated_key, status, selling_price) VALUES (1, 3, 2, 23, 35, '17885', 'COMPLETED', 880);
INSERT INTO reservation(pharmacy_id, drug_id, patient_id, quantity, interval_id, generated_key, status, selling_price) VALUES (1, 3, 1, 18, 36, '99988', 'COMPLETED', 99);
INSERT INTO reservation(pharmacy_id, drug_id, patient_id, quantity, interval_id, generated_key, status, selling_price) VALUES (1, 2, 1, 17, 37, '00700', 'COMPLETED', 489);
--prosle godine rezervacije
INSERT INTO reservation(pharmacy_id, drug_id, patient_id, quantity, interval_id, generated_key, status, selling_price) VALUES (1, 3, 2, 23, 38, '17785', 'COMPLETED', 1500);
INSERT INTO reservation(pharmacy_id, drug_id, patient_id, quantity, interval_id, generated_key, status, selling_price) VALUES (1, 3, 1, 50, 39, '914418', 'COMPLETED', 1399);
INSERT INTO reservation(pharmacy_id, drug_id, patient_id, quantity, interval_id, generated_key, status, selling_price) VALUES (1, 2, 1, 70, 37, '00568', 'COMPLETED', 780);
INSERT INTO reservation(pharmacy_id, drug_id, patient_id, quantity, interval_id, generated_key, status, selling_price) VALUES (1, 4, 2, 18, 40, '81639', 'COMPLETED', 326);
INSERT INTO reservation(pharmacy_id, drug_id, patient_id, quantity, interval_id, generated_key, status, selling_price) VALUES (1, 5, 1, 60, 41, '111888', 'COMPLETED', 490);
INSERT INTO reservation(pharmacy_id, drug_id, patient_id, quantity, interval_id, generated_key, status, selling_price) VALUES (1, 6, 2, 9, 42, '3358', 'COMPLETED', 1600);


-- NARUDZBENICE

INSERT INTO purchase_order(status, pharmacy_id, pharmacy_admin_id, deadline) VALUES ( 'AWAITING', 1, 1, '2021.04.10 09:00');
INSERT INTO purchase_order(status, pharmacy_id, pharmacy_admin_id, deadline) VALUES ( 'AWAITING', 1, 1, '2021.02.22 19:00');
INSERT INTO purchase_order(status, pharmacy_id, pharmacy_admin_id, deadline) VALUES ( 'FINISHED', 1, 1, '2021.02.10 09:00');
INSERT INTO purchase_order(status, pharmacy_id, pharmacy_admin_id, deadline) VALUES ( 'FINISHED', 1, 1, '2021.01.11 19:00');

INSERT INTO purchase_order(status, pharmacy_id, pharmacy_admin_id, deadline) VALUES ( 'AWAITING', 2, 2, '2021.05.10 09:00');
INSERT INTO purchase_order(status, pharmacy_id, pharmacy_admin_id, deadline) VALUES ( 'AWAITING', 2, 2, '2021.03.11 19:00');


-- STAVKE NARUDZBENICE

INSERT INTO purchase_order_item(purchase_order_id, drug_id, quantity) VALUES (1, 1, 50);
INSERT INTO purchase_order_item(purchase_order_id, drug_id, quantity) VALUES (1, 2, 70);
INSERT INTO purchase_order_item(purchase_order_id, drug_id, quantity) VALUES (1, 3, 10);

INSERT INTO purchase_order_item(purchase_order_id, drug_id, quantity) VALUES (2, 1, 50);
INSERT INTO purchase_order_item(purchase_order_id, drug_id, quantity) VALUES (2, 2, 50);

INSERT INTO purchase_order_item(purchase_order_id, drug_id, quantity) VALUES (3, 3, 300);
INSERT INTO purchase_order_item(purchase_order_id, drug_id, quantity) VALUES (3, 4, 50);

INSERT INTO purchase_order_item(purchase_order_id, drug_id, quantity) VALUES (4, 5, 100);

INSERT INTO purchase_order_item(purchase_order_id, drug_id, quantity) VALUES (5, 3, 45);
INSERT INTO purchase_order_item(purchase_order_id, drug_id, quantity) VALUES (5, 7, 60);

INSERT INTO purchase_order_item(purchase_order_id, drug_id, quantity) VALUES (6, 2, 80);
INSERT INTO purchase_order_item(purchase_order_id, drug_id, quantity) VALUES (6, 4, 99);

-- DOBAVLJACI I NJIHOVA PRAVA POVEZANA

INSERT INTO supplier(username, password, first_name, last_name, status) VALUES ('dobavljac1@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Djordjo', 'Pazzini', 'ACTIVE');     
INSERT INTO supplier_authority(user_id, authority_id) VALUES (1,6);
INSERT INTO supplier(username, password, first_name, last_name, status) VALUES ('dobavljac2@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Francesko', 'Bonnati', 'ACTIVE');   
INSERT INTO supplier_authority(user_id, authority_id) VALUES (2,6);
INSERT INTO supplier(username, password, first_name, last_name, status) VALUES ('dobavljac3@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Djanfranko', 'Tatalja', 'ACTIVE');   
INSERT INTO supplier_authority(user_id, authority_id) VALUES (3,6);
INSERT INTO supplier(username, password, first_name, last_name, status) VALUES ('dobavljac4@email.com', '$2y$10$v2VTp3FNpFNRhm0KInx5iuBrD4bGUl6thtbV9bIDMT0dnQK1o.UJC', 'Djuzepe', 'Sorrento', 'ACTIVE');   
INSERT INTO supplier_authority(user_id, authority_id) VALUES (4,6);

-- PONUDE ZA NARUDZBENICE

INSERT INTO supplier_offer(purchase_order_id, supplier_id, offer, delivery_deadline, status) VALUES (1, 1, 35000, '2021.04.17 09:00', 'AWAITING');
INSERT INTO supplier_offer(purchase_order_id, supplier_id, offer, delivery_deadline, status) VALUES (1, 2, 38000, '2021.04.15 09:00', 'AWAITING');
INSERT INTO supplier_offer(purchase_order_id, supplier_id, offer, delivery_deadline, status) VALUES (1, 3, 19000, '2021.04.30 09:00', 'AWAITING');

INSERT INTO supplier_offer(purchase_order_id, supplier_id, offer, delivery_deadline, status) VALUES (2, 1, 45000, '2021.02.27 09:00', 'AWAITING');
INSERT INTO supplier_offer(purchase_order_id, supplier_id, offer, delivery_deadline, status) VALUES (2, 4, 60000, '2021.03.05 09:00', 'AWAITING');

INSERT INTO supplier_offer(purchase_order_id, supplier_id, offer, delivery_deadline, status) VALUES (3, 1, 1000, '2021.02.17 09:00', 'ACCEPTED');
INSERT INTO supplier_offer(purchase_order_id, supplier_id, offer, delivery_deadline, status) VALUES (3, 2, 5400, '2021.02.25 09:00', 'DENIED');
INSERT INTO supplier_offer(purchase_order_id, supplier_id, offer, delivery_deadline, status) VALUES (3, 3, 8000, '2021.02.11 09:00', 'DENIED');
INSERT INTO supplier_offer(purchase_order_id, supplier_id, offer, delivery_deadline, status) VALUES (3, 4, 7600, '2021.03.30 09:00', 'DENIED');


-- ZAHTEVI ZA GODISNJI ODMOR FARMACEUT

INSERT INTO vacation_request_pharm(pharmacist_id, interval_id, status) VALUES (1, 47, 'AWAITING');
INSERT INTO vacation_request_pharm(pharmacist_id, interval_id, status) VALUES (1, 44, 'AWAITING');
INSERT INTO vacation_request_pharm(pharmacist_id, interval_id, status) VALUES (2, 45, 'AWAITING');
INSERT INTO vacation_request_pharm(pharmacist_id, interval_id, status) VALUES (2, 46, 'AWAITING');


-- ZAHTEVI GODISNJI ODMOR DERMATOLOG

INSERT INTO vacation_request_derm(pharmacy_id, dermatologist_id, interval_id, status) VALUES (1, 2, 47, 'AWAITING');
INSERT INTO vacation_request_derm(pharmacy_id, dermatologist_id, interval_id, status) VALUES (1, 2, 44, 'AWAITING');
INSERT INTO vacation_request_derm(pharmacy_id, dermatologist_id, interval_id, status) VALUES (1, 1, 45, 'AWAITING');
INSERT INTO vacation_request_derm(pharmacy_id, dermatologist_id, interval_id, status) VALUES (1, 1, 46, 'AWAITING');



