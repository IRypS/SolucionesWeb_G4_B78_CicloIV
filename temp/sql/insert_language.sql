USE trailersdb;

INSERT INTO language (id_language, name_language, url_icon_language) 
    VALUES ('c66f3e3f-5c0c-40ad-ab40-1f62c04a2f3a', 'Inglés', '/resources/icons/flags/united-states.svg'),
        ('d695b6b9-9b07-4638-b6ac-7a29b49c9891', 'Latino', '/resources/icons/flags/mexico.svg'),
        ('c450303a-4777-44d7-80cd-cf7f98c8b86a', 'Castellano', '/resources/icons/flags/spain.svg'),
        ('be6dc5f5-963b-4bcc-85e7-bdd13fae0095', 'Japonés', '/resources/icons/flags/japan.svg');

DROP TABLE language;
DELETE FROM language;