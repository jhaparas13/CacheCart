# 🚀 CacheCart

A Spring Boot backend project focused on **improving API performance using Redis caching** and **running a multi-service setup using Docker**.

---

## 📌 Overview

CacheCart provides REST APIs for managing products and orders.
To optimize performance, Redis is integrated to cache frequently accessed data and reduce database load.

The entire system runs using Docker Compose (App + MySQL + Redis).

---

## ⚙️ Tech Stack

* Java 17
* Spring Boot
* Spring Data JPA
* MySQL
* Redis
* Docker & Docker Compose
* Maven
* JUnit & Mockito

---

## ✨ Features

* CRUD APIs for Products & Orders
* Redis caching for read-heavy endpoints
* Cache eviction & update handling
* Unit testing with JUnit & Mockito
* Fully containerized setup

---

## 🧠 Key Concepts

* Spring Cache (`@Cacheable`, `@CachePut`, `@CacheEvict`)
* Redis integration & serialization handling
* Docker networking (service-to-service communication)
* Layered backend architecture

---

## 🐳 Run Locally (Docker)

### 1. Clone repo

```bash
git clone https://github.com/jhaparas13/CacheCart.git
cd CacheCart
```

### 2. Start services

```bash
docker compose up --build
```

### 3. API Base URL

```
http://localhost:8080/api/products
```

---

## 📡 API Endpoints

**Products**

* `POST /api/products`
* `GET /api/products`
* `GET /api/products/{id}`
* `PUT /api/products/{id}`
* `DELETE /api/products/{id}`

---

## ⚔️ Challenges

* Redis serialization failures (`Cannot serialize` error)
* Cache not hitting due to misconfiguration
* Debugging Redis connection issues inside Docker
* Handling stale cache after updates

---

## 📚 Learnings

* How caching actually works (not just annotations)
* Redis + Spring Boot integration in real scenarios
* Docker setup and Containerization
* Debugging distributed systems (App + DB + Cache)
* Importance of proper configuration in Docker environments
* Writing cleaner and testable backend code

---

## 👨‍💻 Author

Paras Jha

---
