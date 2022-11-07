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

    @Column(name = "name_trailer")
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