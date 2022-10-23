package com.soluciones.web.appGrupo4.model;

import java.util.List;

public class Movie {
    
    private String id;
    private String coverUrl;
    private String name;
    private List<String> directorId;
    private List<String> screenwriterId;
    private List<String> categories;
    private List<String> characters;
    private String duration;
    private String synopsis;
    private String releaseDate;
    private String rating;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
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
    public List<String> getDirectorId() {
        return directorId;
    }
    public void setDirectorId(List<String> directorId) {
        this.directorId = directorId;
    }
    public List<String> getScreenwriterId() {
        return screenwriterId;
    }
    public void setScreenwriterId(List<String> screenwriterId) {
        this.screenwriterId = screenwriterId;
    }
    public List<String> getCategories() {
        return categories;
    }
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
    public List<String> getCharacters() {
        return characters;
    }
    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
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
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }



    
    
    
}
