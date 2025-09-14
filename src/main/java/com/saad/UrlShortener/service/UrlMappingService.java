package com.saad.UrlShortener.service;

import com.saad.UrlShortener.model.UrlMapping;
import com.saad.UrlShortener.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

@Service
public class UrlMappingService {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    public String generateShortenURL(String originalUrl) {
        return urlMappingRepository.findByOriginalUrl(originalUrl)
                .map(UrlMapping::getShortenUrl)
                .orElseGet(() -> {
                    String shortenUrl = generateShortenUrl(originalUrl);

                    UrlMapping urlMapping = UrlMapping.builder()
                            .originalUrl(originalUrl)
                            .shortenUrl(shortenUrl)
                            .build();

                    urlMappingRepository.save(urlMapping);
                    return shortenUrl;
                });
    }

    public String getOriginalUrl(String shortenUrl) {
        return urlMappingRepository.findByShortenUrl(shortenUrl)
                .map(UrlMapping::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("URL not found"));
    }

    private String generateShortenUrl(String originalUrl) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(originalUrl.getBytes(StandardCharsets.UTF_8));

            // Short code in lowercase
            String shortCode = Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(hash)
                    .substring(0, 10);

            // Extract domain from originalUrl
            URL url = new URL(originalUrl);
            String domain = url.getProtocol() + "://" + url.getHost();

            // Rebuild shortened URL
            return domain + "/" + shortCode;
        } catch (Exception e) {
            throw new RuntimeException("Error generating short URL");
        }
    }
}
