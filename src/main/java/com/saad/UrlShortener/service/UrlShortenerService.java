package com.saad.UrlShortener.service;

import com.saad.UrlShortener.model.UrlShortener;
import com.saad.UrlShortener.repository.UrlShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

@Service
public class UrlShortenerService {

    @Autowired
    private UrlShortenerRepository urlShortenerRepository;

    public String generateShortenURL(String originalUrl) {
        return urlShortenerRepository.findByOriginalUrl(originalUrl)
                .map(UrlShortener::getShortenUrl)
                .orElseGet(() -> {
                    String shortenUrl = generateShortenUrl(originalUrl);

                    UrlShortener urlShortener = UrlShortener.builder()
                            .originalUrl(originalUrl)
                            .shortenUrl(shortenUrl)
                            .build();

                    urlShortenerRepository.save(urlShortener);
                    return shortenUrl;
                });
    }

    public String getOriginalUrl(String shortenUrl) {
        return urlShortenerRepository.findByShortenUrl(shortenUrl)
                .map(UrlShortener::getOriginalUrl)
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
                    .substring(0, 10)
                    .toLowerCase();

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
