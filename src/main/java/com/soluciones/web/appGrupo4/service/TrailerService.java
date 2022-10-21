package com.soluciones.web.appGrupo4.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.Trailer;

@Service
public class TrailerService implements ITrailerService{
    
    @Override
    public List<Trailer> getAllTrailers() {

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

        trailer1.setId("D09aGdEDK2U");
        trailer1.setTitle("TERMINATOR 7: End Of War (2022) Official Trailer Teaser - Arnold Schwarzenegger");

        trailer2.setId("jaAiz_-ZLvI");
        trailer2.setTitle("CREED 3 Tráiler Español (2023)");

        trailer3.setId("fbZyLF16pgE");
        trailer3.setTitle("ONE PIECE FILM RED Último Tráiler Español Latino (Nuevo, 2022)");

        trailer4.setId("XvqWZOPJN0Y");
        trailer4.setTitle("SIN NOVEDAD EN EL FRENTE Tráiler Español Latino Subtitulado (2022)");

        trailer5.setId("4QJOLNASevs");
        trailer5.setTitle("EL PEOR VECINO DEL MUNDO Tráiler Español (2022) Tom Hanks");

        trailer6.setId("yerGXcQEXUo");
        trailer6.setTitle("L'IMMENSITÁ Tráiler Español (2022) Penélope Cruz");

        trailer7.setId("UajwHWUXY7k");
        trailer7.setTitle("BLACK ADAM \"Hay Héroes y Hay Villanos\" Tráiler Español Latino Subtitulado (2022)");

        trailer8.setId("jNj2QUD_jCI");
        trailer8.setTitle("POKER FACE Tráiler (2022)");

        trailer9.setId("bjki2_w8Rtk");
        trailer9.setTitle("SPIRITED Tráiler (2022) Ryan Reynolds, Will Ferrell");

        trailer10.setId("lMVtVRffw9o");
        trailer10.setTitle("YO ME ENCARGO DE LA CERVEZA Tráiler Español (2022) Zac Efron");

        trailer11.setId("AYoLBi0tL_0");
        trailer11.setTitle("NOCHE SIN PAZ Tráiler Español Latino Subtitulado (2022)");

        trailer12.setId("4NrgOIDHNGg");
        trailer12.setTitle("UNA BODA EXPLOSIVA Tráiler Español (2022) Jennifer Lopez");

        List<Trailer> trailerList = Arrays.asList(trailer1, trailer2, trailer3, trailer4, trailer5, trailer6, trailer7, trailer8, trailer9, trailer10, trailer11, trailer12);

        return trailerList;
    };

    @Override
    public Map<String, Trailer> getTrailersMap() {

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

        trailer1.setId("D09aGdEDK2U");
        trailer1.setTitle("TERMINATOR 7: End Of War (2022) Official Trailer Teaser - Arnold Schwarzenegger");

        trailer2.setId("jaAiz_-ZLvI");
        trailer2.setTitle("CREED 3 Tráiler Español (2023)");

        trailer3.setId("fbZyLF16pgE");
        trailer3.setTitle("ONE PIECE FILM RED Último Tráiler Español Latino (Nuevo, 2022)");

        trailer4.setId("XvqWZOPJN0Y");
        trailer4.setTitle("SIN NOVEDAD EN EL FRENTE Tráiler Español Latino Subtitulado (2022)");

        trailer5.setId("4QJOLNASevs");
        trailer5.setTitle("EL PEOR VECINO DEL MUNDO Tráiler Español (2022) Tom Hanks");

        trailer6.setId("yerGXcQEXUo");
        trailer6.setTitle("L'IMMENSITÁ Tráiler Español (2022) Penélope Cruz");

        trailer7.setId("UajwHWUXY7k");
        trailer7.setTitle("BLACK ADAM \"Hay Héroes y Hay Villanos\" Tráiler Español Latino Subtitulado (2022)");

        trailer8.setId("jNj2QUD_jCI");
        trailer8.setTitle("POKER FACE Tráiler (2022)");

        trailer9.setId("bjki2_w8Rtk");
        trailer9.setTitle("SPIRITED Tráiler (2022) Ryan Reynolds, Will Ferrell");

        trailer10.setId("lMVtVRffw9o");
        trailer10.setTitle("YO ME ENCARGO DE LA CERVEZA Tráiler Español (2022) Zac Efron");

        trailer11.setId("AYoLBi0tL_0");
        trailer11.setTitle("NOCHE SIN PAZ Tráiler Español Latino Subtitulado (2022)");

        trailer12.setId("4NrgOIDHNGg");
        trailer12.setTitle("UNA BODA EXPLOSIVA Tráiler Español (2022) Jennifer Lopez");


        Map<String, Trailer> trailersMap = new HashMap<String, Trailer>();

        trailersMap.put("D09aGdEDK2U", trailer1);
        trailersMap.put("jaAiz_-ZLvI", trailer2);
        trailersMap.put("fbZyLF16pgE", trailer3);
        trailersMap.put("XvqWZOPJN0Y", trailer4);
        trailersMap.put("4QJOLNASevs", trailer5);
        trailersMap.put("yerGXcQEXUo", trailer6);
        trailersMap.put("UajwHWUXY7k", trailer7);
        trailersMap.put("jNj2QUD_jCI", trailer8);
        trailersMap.put("bjki2_w8Rtk", trailer9);
        trailersMap.put("lMVtVRffw9o", trailer10);
        trailersMap.put("AYoLBi0tL_0", trailer11);
        trailersMap.put("4NrgOIDHNGg", trailer12);

        return trailersMap;
    }


    @Override
    public List<Trailer> getRelatedTrailers() {

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

        trailer1.setId("D09aGdEDK2U");
        trailer1.setTitle("TERMINATOR 7: End Of War (2022) Official Trailer Teaser - Arnold Schwarzenegger");

        trailer2.setId("jaAiz_-ZLvI");
        trailer2.setTitle("CREED 3 Tráiler Español (2023)");

        trailer3.setId("fbZyLF16pgE");
        trailer3.setTitle("ONE PIECE FILM RED Último Tráiler Español Latino (Nuevo, 2022)");

        trailer4.setId("XvqWZOPJN0Y");
        trailer4.setTitle("SIN NOVEDAD EN EL FRENTE Tráiler Español Latino Subtitulado (2022)");

        trailer5.setId("4QJOLNASevs");
        trailer5.setTitle("EL PEOR VECINO DEL MUNDO Tráiler Español (2022) Tom Hanks");

        trailer6.setId("yerGXcQEXUo");
        trailer6.setTitle("L'IMMENSITÁ Tráiler Español (2022) Penélope Cruz");

        trailer7.setId("UajwHWUXY7k");
        trailer7.setTitle("BLACK ADAM \"Hay Héroes y Hay Villanos\" Tráiler Español Latino Subtitulado (2022)");

        trailer8.setId("jNj2QUD_jCI");
        trailer8.setTitle("POKER FACE Tráiler (2022)");

        trailer9.setId("bjki2_w8Rtk");
        trailer9.setTitle("SPIRITED Tráiler (2022) Ryan Reynolds, Will Ferrell");

        trailer10.setId("lMVtVRffw9o");
        trailer10.setTitle("YO ME ENCARGO DE LA CERVEZA Tráiler Español (2022) Zac Efron");

        trailer11.setId("AYoLBi0tL_0");
        trailer11.setTitle("NOCHE SIN PAZ Tráiler Español Latino Subtitulado (2022)");

        trailer12.setId("4NrgOIDHNGg");
        trailer12.setTitle("UNA BODA EXPLOSIVA Tráiler Español (2022) Jennifer Lopez");

        List<Trailer> trailerList = Arrays.asList(trailer1, trailer2, trailer3, trailer4, trailer5, trailer6, trailer7, trailer8, trailer9, trailer10, trailer11, trailer12);
        Collections.shuffle(trailerList);
        List<Trailer> relatedTrailersList = Arrays.asList( trailerList.get(0), trailerList.get(2), trailerList.get(3) );

        return relatedTrailersList;
    };
}
