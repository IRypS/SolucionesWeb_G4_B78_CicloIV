package com.soluciones.web.appGrupo4.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.soluciones.web.appGrupo4.model.Trailer;

@Repository
public class TrailersRepository implements ITrailersRepository {

    Trailer trailer1 = new Trailer();
    Trailer trailer2 = new Trailer();
    Trailer trailer3 = new Trailer();
    Trailer trailer4 = new Trailer();
    Trailer trailer5 = new Trailer();
    Trailer trailer6 = new Trailer();
    Trailer trailer7 = new Trailer();
    Trailer trailer8 = new Trailer();
    Trailer trailer9 = new Trailer();
    Trailer trailer10 = new Trailer();
    Trailer trailer11 = new Trailer();
    Trailer trailer12 = new Trailer();
    // Trailer trailer13 = new Trailer();
    
    @Override
    public List<Trailer> getAllTrailersObjects() {

        trailer1.setId("D09aGdEDK2U");
        trailer1.setTitle("TERMINATOR 7: End Of War (2022) Official Trailer Teaser - Arnold Schwarzenegger");
        trailer1.setLanguageId("c66f3e3f-5c0c-40ad-ab40-1f62c04a2f3a");

        trailer2.setId("jaAiz_-ZLvI");
        trailer2.setTitle("CREED 3 Tráiler Español (2023)");
        trailer2.setLanguageId("c450303a-4777-44d7-80cd-cf7f98c8b86a");

        trailer3.setId("fbZyLF16pgE");
        trailer3.setTitle("ONE PIECE FILM RED Último Tráiler Español Latino (Nuevo, 2022)");
        trailer3.setLanguageId("d695b6b9-9b07-4638-b6ac-7a29b49c9891");

        trailer4.setId("XvqWZOPJN0Y");
        trailer4.setTitle("SIN NOVEDAD EN EL FRENTE Tráiler Español Latino Subtitulado (2022)");
        trailer4.setLanguageId("c66f3e3f-5c0c-40ad-ab40-1f62c04a2f3a");
        trailer4.setSubtitleId("d695b6b9-9b07-4638-b6ac-7a29b49c9891");

        trailer5.setId("4QJOLNASevs");
        trailer5.setTitle("EL PEOR VECINO DEL MUNDO Tráiler Español (2022) Tom Hanks");
        trailer5.setLanguageId("c450303a-4777-44d7-80cd-cf7f98c8b86a");

        trailer6.setId("yerGXcQEXUo");
        trailer6.setTitle("L'IMMENSITÁ Tráiler Español (2022) Penélope Cruz");
        trailer6.setLanguageId("c450303a-4777-44d7-80cd-cf7f98c8b86a");

        trailer7.setId("UajwHWUXY7k");
        trailer7.setTitle("BLACK ADAM \"Hay Héroes y Hay Villanos\" Tráiler Español Latino Subtitulado (2022)");
        trailer7.setLanguageId("c66f3e3f-5c0c-40ad-ab40-1f62c04a2f3a");
        trailer7.setSubtitleId("d695b6b9-9b07-4638-b6ac-7a29b49c9891");

        trailer8.setId("jNj2QUD_jCI");
        trailer8.setTitle("POKER FACE Tráiler (2022)");
        trailer8.setLanguageId("c66f3e3f-5c0c-40ad-ab40-1f62c04a2f3a");

        trailer9.setId("bjki2_w8Rtk");
        trailer9.setTitle("SPIRITED Tráiler (2022) Ryan Reynolds, Will Ferrell");
        trailer9.setLanguageId("c66f3e3f-5c0c-40ad-ab40-1f62c04a2f3a");

        trailer10.setId("lMVtVRffw9o");
        trailer10.setTitle("YO ME ENCARGO DE LA CERVEZA Tráiler Español (2022) Zac Efron");
        trailer10.setLanguageId("c450303a-4777-44d7-80cd-cf7f98c8b86a");

        trailer11.setId("AYoLBi0tL_0");
        trailer11.setTitle("NOCHE SIN PAZ Tráiler Español Latino Subtitulado (2022)");
        trailer11.setLanguageId("c66f3e3f-5c0c-40ad-ab40-1f62c04a2f3a");
        trailer11.setSubtitleId("d695b6b9-9b07-4638-b6ac-7a29b49c9891");

        trailer12.setId("4NrgOIDHNGg");
        trailer12.setTitle("UNA BODA EXPLOSIVA Tráiler Español (2022) Jennifer Lopez");
        trailer12.setLanguageId("c450303a-4777-44d7-80cd-cf7f98c8b86a");


        List<Trailer> trailerList = Arrays.asList( trailer1, trailer2, trailer3, trailer4, 
            trailer5, trailer6, trailer7, trailer8, trailer9, trailer10, trailer11, trailer12);

        return trailerList;

    };

    @Override
    public Map<String, Trailer> getTrailersMap() {
        Map<String, Trailer> trailersMap = new HashMap<String, Trailer>();

        this.getAllTrailersObjects().forEach( trailer -> {
            trailersMap.put( trailer.getId(), trailer);
        } );

        return trailersMap;
    };

}
