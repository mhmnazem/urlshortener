# URL Shortener Service

A modern, production-ready URL shortener built with **Kotlin** and **Spring Boot**, following **Hexagonal Architecture** and clean code principles.

## Features

-  Shorten any valid URL via REST API
-  Retrieve original URL via short code
-  Base62 + numeric ID for collision-free shortening
-  Hexagonal (Ports & Adapters) architecture for modularity
-  H2 for tests, PostgreSQL for production
-  Junit & Mockito for Testing
-  Global exception handling
-  Swagger (OpenAPI) for easy API exploration
-  Dockerized for consistent deployment

## Technologies

- Kotlin, Spring Boot
- PostgreSQL / H2
- Maven
- Swagger (springdoc-openapi)
- Docker
## Installation

### Clone the Repository
```bash
# Clone the repository:
git clone https://github.com/mhmnazem/urlshortener.git
```

## How to Run

### Run with Docker

```bash
docker-compose up --build
```

Access Swagger UI:  
`http://localhost:8080/swagger-ui.html`

### Run Locally with Maven

```bash
# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

To run with a specific profile (e.g. dev):

```bash
SPRING_PROFILES_ACTIVE=dev ./mvnw spring-boot:run
```

## Example API Usage

### Shorten a URL

**POST** `/api/v1/shorten`  
**Request:**
```json
{
  "url": "https://example.com/long-page"
}
```

**Response:**
```json
{
  "shortUrl": "http://localhost:8080/abc123"
}
```

**Curl Example:**

```bash
curl -X POST http://localhost:8080/api/v1/shorten \
     -H "Content-Type: application/json" \
     -d '{"url": "https://example.com/long-page"}'
```

### Retrieve Original URL

**GET** `/api/v1/abc123`  

**Curl Example:**
```bash
curl http://localhost:8080/api/v1/abc123
```

**Response:**
```json
{
  "originalUrl": "https://example.com/long-page"
}
```



