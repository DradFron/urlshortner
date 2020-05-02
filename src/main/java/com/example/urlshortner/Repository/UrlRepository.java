package com.example.urlshortner.Repository;

import com.example.urlshortner.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url,Long> {

    boolean existsByShortUrl(String shortUrl);

    Url findByShortUrl(String shortUrl);

}

