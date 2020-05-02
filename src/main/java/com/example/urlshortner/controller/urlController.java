package com.example.urlshortner.controller;



import org.springframework.util.StringUtils;
import com.example.urlshortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class urlController {
    @Autowired
    private UrlService urlService;

    @GetMapping(value = "/")
    public String shortUrl(String sourceUrl,
                        Map<String,String> map) {
        if(!StringUtils.isEmpty(sourceUrl) && !urlService.existsByShortUrl(sourceUrl)){
            map.put("shortUrl",urlService.save(sourceUrl));
        }
        if(urlService.existsByShortUrl(sourceUrl)){
            map.put("msg","无法生成，该链接为短链接");
        }
        return "index";
    }

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url")String url){
        if(urlService.existsByShortUrl("localhost:8080/"+url)) {
            return "redirect:"+urlService.redirect("localhost:8080/"+url);
        }
        else{
            return "index";
        }
    }
}
