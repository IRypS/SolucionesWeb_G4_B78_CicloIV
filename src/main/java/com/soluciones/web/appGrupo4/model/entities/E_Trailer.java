package com.soluciones.web.appGrupo4.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "trailer")
public class E_Trailer implements Serializable{

    @Id
    @Column(name = "id_trailer")
    private String id;

    @Column(name = "pelicula_id")
    private String movieId;

    @Column(name = "nombre_trailer")
    private String title;

    @Column(name = "url_trailer")
    private String url;

    @Column(name = "url_cover_trailer")
    private String imageUrl;

    // @Column(name = "subtitle_id")
    // private String subtitleId;

    @Column(name = "vistas")
    private int views;


    
    @ManyToOne
    @JoinColumn(name = "language_id")
    private E_Language language;

    
    
    // Constructor

    public E_Trailer() {
        this.url = "https://www.youtube.com/watch?v=" + this.id;
        this.imageUrl = "https://img.youtube.com/vi/" + this.id + "/hqdefault.jpg";
    }


    // Getters & Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
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

    // public String getSubtitleId() {
    //     return subtitleId;
    // }

    // public void setSubtitleId(String subtitleId) {
    //     this.subtitleId = subtitleId;
    // }

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
   

}