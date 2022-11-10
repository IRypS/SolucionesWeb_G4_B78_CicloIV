package com.soluciones.web.appGrupo4.model.validators;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "trailer")
public class V_Trailer {

    @Id
    @Column(name = "id_trailer")
    @NotEmpty(message = "El ID no debe estar vacío")
    private String id;

    @Column(name = "name_trailer")
    @NotEmpty(message = "El titulo no debe estar vacío")
    private String title;

    @Column(name = "views_trailer")
    private int views;

    @Column(name = "language_id")
    private String laguageId;

    @Column(name = "subtitle_id")
    private String subtitleId;

    @Column(name = "movie_id")
    private String movieId;



    public V_Trailer() {
        this.id = "";
        this.title = "";
        this.views = 0;
        this.laguageId = "0";
        this.subtitleId = "0";
        this.movieId = "0";
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getLaguageId() {
        return laguageId;
    }

    public void setLaguageId(String laguageId) {
        this.laguageId = laguageId;
    }

    public String getSubtitleId() {
        return subtitleId;
    }

    public void setSubtitleId(String subtitleId) {
        this.subtitleId = subtitleId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

}
