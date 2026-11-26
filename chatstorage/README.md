# chat-history-service

Spring Boot microservice to store chat sessions and messages (suitable for RAG chatbots).

## Quick start (Docker)
1. Copy `.env` to `.env` and edit values if needed.
2. Build & run:
   ```bash
   mvn clean package -DskipTests
   docker-compose up --build
   ```
3. App: http://localhost:8080
   Swagger: http://localhost:8080/swagger-ui.html
   pgAdmin: http://localhost:8081 (user: admin@local / admin)

## APIs
All endpoints require header `X-API-KEY: <your-key>`.
- POST /api/v1/sessions
- GET /api/v1/sessions?userId=...
- PATCH /api/v1/sessions/{id}/rename?userId=...&title=...
- PATCH /api/v1/sessions/{id}/favorite?userId=...&favorite=true
- DELETE /api/v1/sessions/{id}?userId=...
- POST /api/v1/sessions/{sessionId}/messages
- GET /api/v1/sessions/{sessionId}/messages?page=0&size=20
