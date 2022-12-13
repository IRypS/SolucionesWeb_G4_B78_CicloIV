package com.soluciones.web.appGrupo4.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "movie")
public class E_Movie {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id_movie")
    private String idMovie;

    @Column(name = "cover_movie")
    private String coverUrl;

    @Column(name = "name_movie")
    @NotEmpty(message = "Ingrese un título")
    private String name;

    @Column(name = "duration_movie")
    private int duration;

    @Column(name = "synopsis_movie", columnDefinition="LONGTEXT")
    private String synopsis;

    @Column(name = "realease_movie")
    private String releaseDate;

    @Column(name = "rate_movie")
    private double rate;

    @ManyToMany
    @JoinTable(name = "movie_genre",
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "genre_id") })
    private List<E_Genre> genreList;

    @ManyToMany
    @JoinTable(name = "movie_director",
            joinColumns = { @JoinColumn(name = "movie_id") },
            inverseJoinColumns = { @JoinColumn(name = "person_id") })
    private List<E_Person> directorsList;



    // constructor

    public E_Movie() {
        this.coverUrl = "";
        this.name = "";
        this.duration = 0;
        this.synopsis = "";
        this.releaseDate = "";
        this.rate = 0.0;
        this.genreList = new ArrayList<E_Genre>();
        this.directorsList = new ArrayList<E_Person>();
    }



    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public List<E_Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<E_Genre> genreList) {
        this.genreList = genreList;
    }

    public List<E_Person> getDirectorsList() {
        return directorsList;
    }

    public void setDirectorsList(List<E_Person> directorsList) {
        this.directorsList = directorsList;
    }

}
