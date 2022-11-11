package com.soluciones.web.appGrupo4.model.validators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "movie")
public class V_Movie {
    
    @Id
    @Column(name = "id_movie")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String idMovie;

    @Column(name = "cover_movie")
    @NotEmpty(message = "Ingrese la url de la imagen")
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



    public V_Movie() {
        this.idMovie = "";
        this.coverUrl = "";
        this.name = "";
        this.duration = 0;
        this.synopsis = "";
        this.releaseDate = "";
        this.rate = 0;
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

}
