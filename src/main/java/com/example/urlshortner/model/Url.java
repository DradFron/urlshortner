package com.example.urlshortner.model;


import javax.persistence.*;

@Entity
public class Url {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    String sourceUrl;

    @Column(unique = true)
    String shortUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}
