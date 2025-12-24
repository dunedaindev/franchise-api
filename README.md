# Franchise API – Prueba Técnica Backend

API REST desarrollada en **Spring Boot** para la administración de **franquicias**, **sucursales** y **productos**, cumpliendo los criterios solicitados en la prueba técnica.

Una franquicia contiene sucursales y cada sucursal contiene productos con stock.

---

## 🛠️ Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Lombok
- Docker & Docker Compose
- Swagger / OpenAPI

---

## 📦 Funcionalidades implementadas

### Franquicias
- Crear franquicia
- Actualizar nombre de franquicia (BONUS)

### Sucursales
- Agregar sucursal a una franquicia
- Actualizar nombre de sucursal (BONUS)

### Productos
- Agregar producto a una sucursal
- Eliminar producto
- Actualizar stock de producto
- Actualizar nombre de producto (BONUS)

### Consulta especial
- Obtener el **producto con mayor stock por sucursal** para una franquicia específica  
  (retorna el producto y la sucursal a la que pertenece)

---

## 🗄️ Persistencia de datos

La aplicación utiliza **MySQL** como sistema de persistencia.

Cuando se ejecuta mediante **Docker Compose**, la base de datos se almacena en un **volumen Docker**, lo que garantiza que la información **no se pierde al reiniciar los contenedores**.

---

## ▶️ Ejecución en entorno local (MySQL en la máquina)

1. Crear la base de datos:
   ```sql
   CREATE DATABASE accenture;
2. Configurar credenciales en:
   src/main/resources/application.properties
3. Ejecutar la aplicación:
   ```bash
   mvn clean spring-boot:run
### La API quedará disponible en:
http://localhost:8080

---

## 🐳 Ejecución con Docker (recomendado)
1. Construir el proyecto:
   ```bash
   mvn clean package -DskipTests
2. Levantar los contenedores:
   ```bash
   docker-compose up --build

Servicios disponibles:

### API: http://localhost:8080

### MySQL: localhost:3307

## 📖 Documentación de la API (Swagger)

1. Una vez levantada la aplicación, la documentación interactiva está disponible en:

http://localhost:8080/swagger-ui/index.html


Desde Swagger se pueden probar todos los endpoints.
----

## 📂 Estructura del proyecto
```bash 
   accenture.prueba.tecnica.franchise
   └── src
   └── main
   └── java
   └── accenture
   └── prueba
   └── tecnica
   └── franchise
   ├── controller
   ├── service
   ├── repository
   ├── entity
   ├── dto
   │ ├── request
   │ └── response
   ├── exception
   └── config
   ```
---
## 👤 Autor
 Julián Vargas Salamanca