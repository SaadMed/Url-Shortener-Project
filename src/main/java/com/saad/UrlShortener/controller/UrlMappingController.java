package com.saad.UrlShortener.controller;


import com.saad.UrlShortener.service.UrlMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/urls")
public class UrlMappingController {

    @Autowired
    private UrlMappingService urlMappingService;

    @PostMapping("/generateShortenUrl")
    public String generateShortenUrl(@RequestBody String originalUrl) {
        return urlMappingService.generateShortenURL(originalUrl);
    }

    @PostMapping("/provideOrginalUrl")
    public String getOriginalUrl(@RequestBody String shortenUrl) {
        return urlMappingService.getOriginalUrl(shortenUrl);
    }
}
