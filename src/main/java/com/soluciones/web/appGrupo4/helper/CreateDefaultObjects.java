package com.soluciones.web.appGrupo4.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soluciones.web.appGrupo4.model.Response;
import com.soluciones.web.appGrupo4.model.entities.E_Language;
import com.soluciones.web.appGrupo4.model.entities.E_Movie;
import com.soluciones.web.appGrupo4.model.entities.E_Person;
import com.soluciones.web.appGrupo4.repository.I_language_db;
import com.soluciones.web.appGrupo4.repository.I_movie_db;
import com.soluciones.web.appGrupo4.repository.I_person_db;

@Component
public class CreateDefaultObjects implements ICreateDefaultObjects {
    
    @Autowired
    private I_language_db languageInterface;

    @Autowired
    private I_person_db personInterface;

    @Autowired
    private I_movie_db movieInterface;

    @Override
    public Response<E_Language> createDefaultLanguages() {

        Response<E_Language> defaultLanguages = new Response<>();

        try {
            E_Language langDef = new E_Language();
            langDef.setNameLanguage("");
            langDef.setIconLanguage("/resources/icons/international.svg");
            languageInterface.save(langDef);

            E_Language langCast = new E_Language();
            langCast.setNameLanguage("Castellano");
            langCast.setIconLanguage("/resources/icons/flags/spain.svg");
            languageInterface.save(langCast);

            E_Language langLat = new E_Language();
            langLat.setNameLanguage("Latino");
            langLat.setIconLanguage("/resources/icons/flags/mexico.svg");
            languageInterface.save(langLat);

            E_Language langEng = new E_Language();
            langEng.setNameLanguage("Inglés");
            langEng.setIconLanguage("/resources/icons/flags/united-states.svg");
            languageInterface.save(langEng);

            E_Language langJap = new E_Language();
            langJap.setNameLanguage("Japonés");
            langJap.setIconLanguage("/resources/icons/flags/japan.svg");
            languageInterface.save(langJap);


            defaultLanguages.setState(true);
            defaultLanguages.setMessage("Lenguajes creados por defecto exitosos");

            return defaultLanguages;

        } catch (Exception e) {
            defaultLanguages.setState(false);
            defaultLanguages.setMessage("Fallo al crear lenguajes por defecto");
            defaultLanguages.setErrorMessage( e.getMessage() );
            return defaultLanguages;
        }

    }


    @Override
    public Response<E_Person> createDefaultPersons() {
        Response<E_Person> defaultPersons = new Response<>();

        try {

            E_Person p1 = new E_Person();
            p1.setName("Makoto");
            p1.setLastname("Shinkai");
            p1.setAge("1973-02-09");
            personInterface.save(p1);

            E_Person p2 = new E_Person();
            p2.setName("Michael B.");
            p2.setLastname("Jordan");
            p2.setAge("1987-02-09");
            personInterface.save(p2);

            E_Person p3 = new E_Person();
            p3.setName("Gorou");
            p3.setLastname("Taniguchi");
            p3.setAge("1966-10-18");
            personInterface.save(p3);

            E_Person p4 = new E_Person();
            p4.setName("Edward");
            p4.setLastname("Berger");
            p4.setAge("1970-00-00");
            personInterface.save(p4);

            E_Person p5 = new E_Person();
            p5.setName("Marc");
            p5.setLastname("Forster");
            p5.setAge("1969-11-30");
            personInterface.save(p5);

            defaultPersons.setState(true);
            defaultPersons.setMessage("Personas creadas por defecto exitosas");

            return defaultPersons;

            // E_Person 
        } catch (Exception e) {
            e.printStackTrace();
            defaultPersons.setState(false);
            defaultPersons.setMessage("Fallo al crear personas por defecto");
            defaultPersons.setErrorMessage( e.getMessage() );
            return defaultPersons;

        } 
    };

    @Override
    public Response<E_Movie> createDefaultMovies() {
        Response<E_Movie> movieDefault = new Response<>();

        try {
            E_Movie defaultMovie = new E_Movie();
            defaultMovie.setCoverUrl("");
            defaultMovie.setName("");
            defaultMovie.setDuration(0);
            defaultMovie.setRate(0.0);
            defaultMovie.setReleaseDate("0000-00-00");
            defaultMovie.setSynopsis("");
            movieInterface.save(defaultMovie);

            movieDefault.setState(true);
            movieDefault.setMessage("Peliculas creadas por defecto exitosas");
            return movieDefault;

        } catch (Exception e) {

            e.printStackTrace();
            movieDefault.setState(false);
            movieDefault.setMessage("Fallo al crear peliculas por defecto");
            movieDefault.setErrorMessage( e.getMessage() );
            return movieDefault;
        }
    }
}
