package com.soluciones.web.appGrupo4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soluciones.web.appGrupo4.model.entities.E_Trailer;


@Repository
public interface I_trailer_db extends JpaRepository<E_Trailer, String> {
    
    @Query(nativeQuery = true, value = "SELECT * FROM trailer WHERE id_trailer NOT LIKE (?1) ORDER BY rand() LIMIT 5")
    List<E_Trailer> getLimitedTrailers(String id);
    
    List<E_Trailer> findByTitleContainsIgnoreCase(String title);

    @Query(nativeQuery = true, 
        value = "SELECT trailer.* FROM trailer, movie_genre, movie, genre WHERE trailer.movie_id = movie.id_movie AND movie_genre.genre_id = genre.id_genre AND movie_genre.movie_id = movie.id_movie AND genre.name_genre = (?1)")
    List<E_Trailer> getTrailersByGenre(String genre);

    @Query(nativeQuery = true, 
        value = "SELECT trailer.* FROM trailer, language WHERE trailer.language_id = language.id_language AND language.name_language = (?1) ORDER BY trailer.name_trailer ASC;")
    List<E_Trailer> getTrailersByAudioLanguage(String lang);

    @Query(nativeQuery = true, 
        value = "SELECT trailer.* FROM trailer, language WHERE trailer.subtitle_id = language.id_language AND language.name_language = (?1) ORDER BY trailer.name_trailer ASC;")
    List<E_Trailer> getTrailersBySubtitleLanguage(String lang);

}
