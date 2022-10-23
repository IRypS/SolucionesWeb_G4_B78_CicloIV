package com.soluciones.web.appGrupo4.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soluciones.web.appGrupo4.model.Trailer;
import com.soluciones.web.appGrupo4.repository.ITrailersRepository;

@Service
public class TrailerService implements ITrailerService{

    @Autowired
    private ITrailersRepository trailerrepo;
    
    @Override
    public List<Trailer> getAllTrailers() {
        List<Trailer> trailerList = trailerrepo.getAllTrailersObjects();
        return trailerList;
    };

    @Override
    public Map<String, Trailer> getTrailersMap() {

        Map<String, Trailer> trailersMap = trailerrepo.getTrailersMap();

        return trailersMap;
    }


    @Override
    public List<Trailer> getRelatedTrailers(String id) {

        List<Trailer> trailerList = trailerrepo.getAllTrailersObjects();
        Collections.shuffle(trailerList);

        List<Trailer> relatedTrailersList = Arrays.asList( trailerList.get(0), trailerList.get(2), trailerList.get(3) );


        // Change Trailer in case of repetition
        relatedTrailersList.forEach( trailer -> {

            if (trailer.getId().equals(id)) {
                int trailerIndex = relatedTrailersList.indexOf(trailer);
                Trailer newTrailer = trailerList.get(4);
                relatedTrailersList.set(trailerIndex, newTrailer);
            }
        });

        return relatedTrailersList;
    };
}
