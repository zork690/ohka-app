# 🛠️ Ohka App - API REST con Spring Boot y Docker

API REST para la gestión de productos, categorías y usuarios, desarrollada con Spring Boot, autenticación JWT y PostgreSQL. Contiene endpoints protegidos por roles y está lista para desplegarse con Docker.

---

## 📦 Tecnologías utilizadas

- Java 17 + Spring Boot  
- Spring Security + JWT  
- PostgreSQL  
- Docker + Docker Compose  
- Gradle  
- Postman (para pruebas)

---

## 🚀 Cómo levantar el proyecto

### Requisitos previos

- Docker y Docker Compose instalados  
- Java 17 (solo si compilas localmente)  
- Postman (opcional)

### Instrucciones

```bash
# 1. Compilar el proyecto (si no tienes el JAR)
./gradlew build

# 2. Levantar los contenedores
docker-compose up --build

# 3. Acceder a la API
http://localhost:8080
