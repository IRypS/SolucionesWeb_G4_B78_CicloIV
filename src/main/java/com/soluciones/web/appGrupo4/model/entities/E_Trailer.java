package com.soluciones.web.appGrupo4.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "trailer")
public class E_Trailer {

    @Id
    @Column(name = "id_trailer")
    @NotEmpty(message = "El ID no debe estar vacío")
    private String id;

    @Column(name = "name_trailer")
    @NotEmpty(message = "El titulo no debe estar vacío")
    private String title;

    @Column(name = "url_trailer")
    private String url;

    @Column(name = "url_cover_trailer")
    private String imageUrl;

    @Column(name = "views_trailer")
    private int views;


    
    @ManyToOne
    @JoinColumn(name = "language_id")
    private E_Language language;

    @ManyToOne
    @JoinColumn(name = "subtitle_id")
    private E_Language subtitle;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private E_Movie movie;



    // Constructor
    
    public E_Trailer() {
        this.id = "0";
        this.title = "";
        this.url = "";
        this.imageUrl = "";
        this.views = 0;
        this.movie = new E_Movie();
        this.language = new E_Language();
        this.subtitle = new E_Language();
    }



    // Getters & Setters

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public E_Language getLanguage() {
        return language;
    }

    public void setLanguage(E_Language language) {
        this.language = language;
    }

    public E_Language getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(E_Language subtitle) {
        this.subtitle = subtitle;
    }

    public E_Movie getMovie() {
        return movie;
    }

    public void setMovie(E_Movie movie) {
        this.movie = movie;
    }

}