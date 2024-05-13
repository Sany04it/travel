package com.sany.travel.Persistence.Entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String country;
    private String language;
    private String pictureUrl;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Date lastUpdated;

    public City(UUID id, String name, String country, String language, String pictureUrl, String description, Date lastUpdated) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.language = language;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.lastUpdated = lastUpdated;
    }

    public City() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
