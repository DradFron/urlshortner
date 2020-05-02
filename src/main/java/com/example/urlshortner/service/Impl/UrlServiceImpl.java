package com.example.urlshortner.service.Impl;

import com.example.urlshortner.Repository.UrlRepository;
import com.example.urlshortner.model.Url;
import com.example.urlshortner.service.UrlService;
import com.example.urlshortner.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Override
    public String save(String longUrl) {
        if(!longUrl.startsWith("http://") && !longUrl.startsWith("https://")){
            longUrl = "https://"+longUrl;
        }
        Url url = new Url();
        url.setSourceUrl(longUrl);
        urlRepository.save(url);
        url.setShortUrl("localhost:8080/"+generateShortUrl(longUrl,"fron"+url.getId()));
        urlRepository.save(url);
        return url.getShortUrl();
    }

    @Override
    public String redirect(String shortUrl) {
        return urlRepository.findByShortUrl(shortUrl).getSourceUrl();
    }

    @Override
    public boolean existsByShortUrl(String shortUrl) {
        return urlRepository.existsByShortUrl(shortUrl);
    }

    public String generateShortUrl(String url,String key) {

        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"

        };

        String sMD5EncryptResult = MD5Util.MD5(key + url);
        String hex = sMD5EncryptResult;

        String sTempSubString = hex.substring(2 * 8, 2 * 8 + 8);


        long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
        String outChars = "";
        for (int j = 0; j < 6; j++) {
            long index = 0x0000003D & lHexLong;

            outChars += chars[(int) index];

            lHexLong = lHexLong >> 5;
        }

        return outChars;
    }

}
