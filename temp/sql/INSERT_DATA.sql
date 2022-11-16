USE trailersdb;

/* == Ingresar los sigueintesd datos */

INSERT INTO genre (id_genre, name_genre) 
    VALUES 
    ('5495f127-2d65-4c5c-b77e-bb16fe523243', 'Acción'), 
    ('dd744a35-bf12-444e-8923-0add496dad5f', 'Aventura'), 
    ('a9bc512e-8611-4792-8056-9109aefeb64c', 'Bélico'), 
    ('a0ec5375-d708-4979-909c-a4b9d415a7cd', 'Ciencia Ficción'), 
    ('7d239b62-f5c0-4a5f-a58a-37cf729772c1', 'Comedia'), 
    ('38493612-0dc2-4686-9b57-f89c4933ed22', 'Comedia Oscura'), 
    ('4912cc06-fb21-4a8b-9646-4859767b5717', 'Crimen'), 
    ('eae9cb18-c40e-4321-b404-c1d179398ec0', 'Deporte'), 
    ('10817ff1-d5de-4634-8961-e8f001f710b2', 'Drama'), 
    ('d0602991-5e19-4118-8425-e25fd1eb9d6a', 'Familiar'), 
    ('5db452da-7356-4657-a212-ebfac54135b3', 'Fantasía'), 
    ('f1658db5-5654-4d55-8405-6184b524769c', 'Misterio'), 
    ('c4f78868-b909-4121-b5ac-f332afca8265', 'Musical'), 
    ('2e213cc6-15a0-4f15-ab60-a4ae6dc2a835', 'Romance'), 
    ('d5294e4a-bdd6-4c59-84c0-155b68a638f8', 'Superhéroes'), 
    ('8d11e6c1-bb9f-4f03-ad7c-3d1972663cf5', 'Suspenso'), 
    ('8c00170f-7dd4-4783-9fe6-d40e063bccf9', 'Thriller');
insert into country (id_country, url_icon_country, name_country) 
    values ('4aaf1316-20fc-4cb2-8e13-e74baea69f6f', '/resources/icons/flags/united-states.svg', 'United States');
insert into country (id_country, url_icon_country, name_country)
    values ('0cc1ff58-dd9b-451a-9d35-6fe838c8365f', '/resources/icons/flags/argentina.svg', 'Argentina');
insert into country (id_country, url_icon_country, name_country)
    values ('c70cebb8-5609-48e0-981a-d7aeac770199', '/resources/icons/flags/venezuela.svg', 'Venezuela');
insert into country (id_country, url_icon_country, name_country)
    values ('72d683ab-7bbe-434c-9892-3268ddd6cdfd', '/resources/icons/flags/uruguay.svg', 'Uruguay');
insert into country (id_country, url_icon_country, name_country)
    values ('df538471-e443-42d3-aaba-d94cdb812f4c', '/resources/icons/flags/taiwan.svg', 'Taiwan');
insert into country (id_country, url_icon_country, name_country)
    values ('0404c5ca-72a5-4ff8-99fa-083f8f6a2f26', '/resources/icons/flags/spain.svg', 'Spain');
insert into country (id_country, url_icon_country, name_country)
    values ('41a97082-8c0e-479f-b047-001bc53d576f', '/resources/icons/flags/peru.svg', 'Peru');
insert into country (id_country, url_icon_country, name_country)
    values ('69b16865-2c91-460a-b469-123ebfdf1c9e', '/resources/icons/flags/paraguay.svg', 'Paraguay');
insert into country (id_country, url_icon_country, name_country)
    values ('683f2ae1-86b7-4d8e-901e-ec32d388485f', '/resources/icons/flags/mexico.svg', 'Mexico');
insert into country (id_country, url_icon_country, name_country)
    values ('7503cd7f-5e68-40e8-bb16-cc80d7424d63', '/resources/icons/flags/japan.svg', 'Japan');
insert into country (id_country, url_icon_country, name_country)
    values ('68acf167-2d96-41e2-8ab8-f9e5dec8c858', '/resources/icons/flags/india.svg', 'India');
insert into country (id_country, url_icon_country, name_country)
    values ('7cb6e7e8-0db2-4d44-b272-6ec235ba918a', '/resources/icons/flags/indonesia.svg', 'Indonesia');
insert into country (id_country, url_icon_country, name_country)
    values ('da461942-8a66-497c-992d-1a7647e29736', '/resources/icons/flags/france.svg', 'France');
insert into country (id_country, url_icon_country, name_country)
    values ('0f18c253-b28d-4db9-8cf9-77d739011121', '/resources/icons/flags/ecuador.svg', 'Ecuador');
insert into country (id_country, url_icon_country, name_country)
    values ('e068270a-788c-497e-ba62-d66b3644ee4e', '/resources/icons/flags/cuba.svg', 'Cuba');
insert into country (id_country, url_icon_country, name_country)
    values ('7f23367f-5dc0-4ea5-abab-f175bb34713a', '/resources/icons/flags/costa-rica.svg', 'Costa Rica');
insert into country (id_country, url_icon_country, name_country)
    values ('dc714446-04ad-4524-91b9-a4983ea10090', '/resources/icons/flags/colombia.svg', 'Colombia');
insert into country (id_country, url_icon_country, name_country)
    values ('73bec110-d54d-4101-a22b-1a721e201f47', '/resources/icons/flags/chile.svg', 'Chile');
insert into country (id_country, url_icon_country, name_country)
    values ('c66a9c28-bb2e-4f87-b55e-29834ce1fb07', '/resources/icons/flags/china.svg', 'China');
insert into country (id_country, url_icon_country, name_country)
    values ('b9f005ab-ace3-439a-bbf6-ae9cf6bfbe76', '/resources/icons/flags/brazil.svg', 'Brazil');
insert into country (id_country, url_icon_country, name_country)
    values ('7b16a526-ae13-48f6-9d86-3f02653e0fb9', '/resources/icons/flags/germany.svg', 'Germany');
insert into country (id_country, url_icon_country, name_country)
    values ('e1838e46-b226-4ba2-85bd-9c923675f973', '/resources/icons/flags/new-zealand.svg', 'New Zealand');
insert into country (id_country, url_icon_country, name_country)
    values ('48905e9d-675b-4231-b0b1-cd18145cc94f', '/resources/icons/flags/norway.svg', 'Norway');
insert into country (id_country, url_icon_country, name_country)
    values ('0', '/resources/icons/international.svg', 'Seleccione un país');
INSERT INTO language (id_language, name_language, url_icon_language) 
    VALUES ('0', 'undefined', '/resources/icons/international.svg'),
        ('c66f3e3f-5c0c-40ad-ab40-1f62c04a2f3a', 'Inglés', '/resources/icons/flags/united-states.svg'),
        ('d695b6b9-9b07-4638-b6ac-7a29b49c9891', 'Latino', '/resources/icons/flags/mexico.svg'),
        ('c450303a-4777-44d7-80cd-cf7f98c8b86a', 'Castellano', '/resources/icons/flags/spain.svg'),
        ('be6dc5f5-963b-4bcc-85e7-bdd13fae0095', 'Japonés', '/resources/icons/flags/japan.svg');
INSERT INTO activity (id_activity, name_activity) 
	VALUES ('6a819380-bb3b-471e-9aa2-b5374a300189', 'actor'),
    ('58769771-c7e1-4665-9786-4ce702a2dd35', 'director'), 
    ('3812fcea-1051-458a-9236-4ab1d1c192e2', 'guionista');
INSERT INTO person (id_person, name_person, last_name_person, birthday_person, country_id)
    VALUES('0ba1983b-8507-4b1c-97a3-a6a4b388f68b', 'Makoto', 'Shinkai', '1973-02-09','7503cd7f-5e68-40e8-bb16-cc80d7424d63');
INSERT INTO person (id_person, name_person, last_name_person, birthday_person, country_id)
    VALUES('1d64a05a-c63a-4c02-9b50-c0344d576e83', 'Michael B.', 'Jordan', '1987-02-09', '4aaf1316-20fc-4cb2-8e13-e74baea69f6f');
INSERT INTO person (id_person, name_person, last_name_person, birthday_person, country_id)
    VALUES('12bb8865-b31e-4c10-859d-9cc30f09de46', 'Gorou', 'Taniguchi', '1966-10-18', '7503cd7f-5e68-40e8-bb16-cc80d7424d63');
INSERT INTO person (id_person, name_person, last_name_person, birthday_person, country_id)
    VALUES('5cfb5b20-aeb1-4e5c-89f1-41dc229739e4', 'Edward', 'Berger', '1970-00-00', '7b16a526-ae13-48f6-9d86-3f02653e0fb9');
INSERT INTO person (id_person, name_person, last_name_person, birthday_person, country_id)
    VALUES('7f9eb166-4e2e-4327-a217-7ec599388152', 'Marc', 'Forster', '1969-11-30', '7b16a526-ae13-48f6-9d86-3f02653e0fb9');
INSERT INTO person (id_person, name_person, last_name_person, birthday_person, country_id)
    VALUES('3524b0fe-e7ab-42d8-9ab4-e4094630969f', 'Jaume', 'Collet-Serra', '1974-03-23', '0404c5ca-72a5-4ff8-99fa-083f8f6a2f26');
INSERT INTO person (id_person, name_person, last_name_person, birthday_person, country_id)
    VALUES('be51f051-b90a-4ceb-963a-3fefad0a5b8a', 'Russell', 'Crowe', '1964-04-07', 'e1838e46-b226-4ba2-85bd-9c923675f973');
INSERT INTO person (id_person, name_person, last_name_person, birthday_person, country_id)
    VALUES('178b918b-f16b-4b9f-a7ac-dc796881f777', 'Sean', 'Anders', '1983-08-19', '4aaf1316-20fc-4cb2-8e13-e74baea69f6f');
INSERT INTO person (id_person, name_person, last_name_person, birthday_person, country_id)
    VALUES('f0115446-b9e3-4a62-b07c-1f49881bed5d', 'Peter', 'Farrelly', '1956-12-17', '4aaf1316-20fc-4cb2-8e13-e74baea69f6f');
INSERT INTO person (id_person, name_person, last_name_person, birthday_person, country_id)
    VALUES('3609c90b-95c7-424f-bbee-2f509bdfdefc', 'Tommy', 'Wirkola', '1979-12-06', '48905e9d-675b-4231-b0b1-cd18145cc94f');
INSERT INTO person (id_person, name_person, last_name_person, birthday_person, country_id)
    VALUES('b6ba0c94-3a14-428e-8b67-dfc3f737173a', 'Jason', 'Moore', '1970-10-22', '4aaf1316-20fc-4cb2-8e13-e74baea69f6f');
INSERT INTO person (id_person, name_person, last_name_person, birthday_person, country_id)
    VALUES('0', 'Seleccione una persona', '', '0000-00-00', '0');
INSERT INTO movie (id_movie, cover_movie, name_movie, duration_movie, rate_movie, realease_movie, synopsis_movie) 
    VALUES ('0', '', 'Seleccione una pelicula', 0, 0.0, '0000-00-00', '');
