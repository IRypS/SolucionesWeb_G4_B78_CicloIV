package com.soluciones.web.appGrupo4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soluciones.web.appGrupo4.model.entities.E_Movie;


@Repository
public interface I_movie_db extends JpaRepository<E_Movie, String>{

    @Query( nativeQuery = true,
        value = "SELECT movie.id_movie FROM genre, movie, movie_genre WHERE genre.id_genre = movie_genre.genre_id AND movie.id_movie = movie_genre.movie_id AND genre.id_genre = (?1)" )
    List<String> findIdMoviesByGenreId(String id);
    
    @Query( nativeQuery = true,
        value = "SELECT COUNT(movie.id_movie) FROM genre, movie, movie_genre WHERE genre.id_genre = movie_genre.genre_id AND movie.id_movie = movie_genre.movie_id AND genre.id_genre = (?1)" )
    int findMoviesCountByGenreId(String id);
    
}
