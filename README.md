# Spring Boot Microservicios y Autenticación JWT 

Este proyecto consta de tres microservicios desarrollados en Spring Boot:

- **gateway-service**: Servicio de puerta de enlace que enruta las solicitudes a los microservicios.
- **users-service**: Servicio encargado de la gestión de usuarios.
- **products-service**: Servicio para la gestión de productos.

## Requisitos

Antes de ejecutar los microservicios, asegúrate de tener instalados los siguientes requisitos:

- **Java 17** o superior
- **Apache Maven 3.8+**

## Instalación y Ejecución

### 1. Construir los servicios
Ejecutar el siguiente comando en la raíz del proyecto:
```bash
mvn clean install
```
Esto generará los archivos JAR de cada microservicio en sus respectivas carpetas `target/`.

### 2. Ejecutar los microservicios
Cada microservicio debe ejecutarse en una terminal diferente.

#### Gateway Service:
```bash
cd gateway-service
mvn spring-boot:run
```

#### Users Service:
```bash
cd users-service
mvn spring-boot:run
```

#### Products Service:
```bash
cd products-service
mvn spring-boot:run
```

### 3. Verificar los servicios
Una vez en ejecución, los servicios estarán disponibles en:
- **Gateway Service**: `http://localhost:8080`
- **Users Service**: `http://localhost:8081`
- **Products Service**: `http://localhost:8082`
