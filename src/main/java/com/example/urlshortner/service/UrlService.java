package com.example.urlshortner.service;


public interface UrlService {
    public String save(String url);

    public String redirect(String shortUrl);

    public boolean existsByShortUrl(String shortUrl);

}
