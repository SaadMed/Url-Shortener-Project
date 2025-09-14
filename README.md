# Url-Shortener-Project

# URL Shortener (Spring Boot)

A simple **URL Shortener** built with **Java 17** and **Spring Boot 3**.  
This service generates short URLs (max 10 characters) for given long URLs and resolves them back to the original.  
It also persists data using an embedded database (H2) so that shortened URLs survive restarts.  

---

## 🚀 Features

- From a full URL, generate a shortened URL - A shortened URL should not be longer than 10 characters, not including the service URL (domain).
- Resolve a short URL back to the original long URL.
- Same input URL → always same short URL.
- Persistent storage using **H2 (file mode)**.
- Shortened URL reuses the **domain of the original URL**.

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **H2 Database**
- **Gradle**

---

## 📂 Project Structure

UrlShortenerApplication.java
│
├── controller
│ └── UrlMappingController.java
│
├── service
│ └── UrlMappingService.java
│
├── repository
│ └── UrlMappingRepository.java
│
├── model
│ └── UrlMapping.java

