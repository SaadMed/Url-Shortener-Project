package com.saad.UrlShortener.repository;

import com.saad.UrlShortener.model.UrlShortener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlShortenerRepository extends JpaRepository<UrlShortener, Integer> {
    Optional<UrlShortener> findByOriginalUrl(String originalUrl);
    Optional<UrlShortener> findByShortenUrl(String shortenUrl);
}
