package com.soluciones.web.appGrupo4.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trailer")
public class Trailer {

    @Id
    private String id;
    
    private String movie_id;
    private String title;
    private String url;
    private String imageUrl;
    private String languageId;
    private String subtitleId;
    private int views;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getMovie_id() {
        return movie_id;
    }
    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        this.url = "https://www.youtube.com/watch?v=" + id;;
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImageUrl() {
        this.imageUrl = "https://img.youtube.com/vi/" + id + "/hqdefault.jpg";
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getLanguageId() {
        return languageId;
    }
    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }
    public String getSubtitleId() {
        return subtitleId;
    }
    public void setSubtitleId(String subtitleId) {
        this.subtitleId = subtitleId;
    }
    public int getViews() {
        return views;
    }
    public void setViews(int views) {
        this.views = views;
    }

    
}
