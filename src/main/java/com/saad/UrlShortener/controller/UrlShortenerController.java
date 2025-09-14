package com.saad.UrlShortener.controller;


import com.saad.UrlShortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/generateShortenUrl")
    public String generateShortenUrl(@RequestBody String originalUrl) {
        return urlShortenerService.generateShortenURL(originalUrl);
    }

    @PostMapping("/provideOrginalUrl")
    public String getOriginalUrl(@RequestBody String shortenUrl) {
        return urlShortenerService.getOriginalUrl(shortenUrl);
    }
}
