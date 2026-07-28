# GymManager API

GymManager API es un backend REST desarrollado con Java y Spring Boot para la administración de gimnasios. El proyecto permite gestionar entrenadores, miembros, membresías y pagos, además de incorporar autenticación para administradores mediante JWT.

Este proyecto fue desarrollado por etapas con el objetivo de fortalecer conocimientos en desarrollo backend, arquitectura por capas, seguridad, persistencia de datos y buenas prácticas para el desarrollo de APIs REST.

---

# Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA (Hibernate)
- JWT
- BCrypt
- MySQL
- Maven
- Lombok
- Postman
- Git y GitHub

---

# Funcionalidades

Actualmente la API permite:

- Gestión de entrenadores.
- Gestión de miembros.
- Gestión de membresías.
- Registro de pagos.
- Administración de usuarios.
- Autenticación mediante JWT.
- Validaciones de negocio.
- Manejo global de excepciones.
- Arquitectura por capas.
- Uso de DTO para Request y Response.

---

# Modelo de datos

```
Administrador

Entrenador (1)
      │
      │
      ▼
Miembro (N)
      │
      │
      ▼
Membresía (1)
      │
      │
      ▼
Pago (N)
```

---

# Arquitectura del proyecto

```
src
└── main
    └── java
        └── com.gym.gym
            ├── Config
            ├── Controller
            ├── DTO
            │   ├── Request
            │   └── Response
            ├── Exception
            ├── Model
            ├── Repository
            ├── Security
            ├── Service
            └── Util
```

El proyecto sigue una arquitectura por capas para separar la lógica de negocio, el acceso a datos, la seguridad y la comunicación mediante DTO.

---

# Cómo ejecutar el proyecto

## Requisitos

- Java 21
- Maven
- MySQL 8

## Configuración

Crear el archivo:

```
src/main/resources/application.properties
```

Configurar la conexión a la base de datos.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gymmanager?createDatabaseIfNotExist=true
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=tu_clave_secreta
```

Ejecutar el proyecto.

```bash
mvn spring-boot:run
```

---

# Evolución del proyecto

El desarrollo de esta API se realizó por etapas. Cada una incorpora nuevas funcionalidades y mejoras respecto a la anterior.

| Etapa | Descripción |
|--------|-------------|
| [01 - CRUD Inicial](docs/01-crud-inicial.md) | Implementación del CRUD básico de entrenadores, miembros, membresías y pagos. |
| [02 - DTO, Login y BCrypt](docs/02-dto-login-bcrypt.md) | Implementación de DTO, mejoras en las respuestas de la API, registro y autenticación del administrador mediante BCrypt. |
| 03 - Spring Security y JWT | Protección de endpoints, generación y validación de tokens JWT y mejoras en el manejo de excepciones. *(Próximamente)* |
| 04 - Paginación y filtros | Implementación de paginación, ordenamiento y filtros para mejorar las consultas. *(Próximamente)* |

---

# Lo que aprendí

Durante el desarrollo de este proyecto reforcé conocimientos sobre:

- Desarrollo de APIs REST.
- Arquitectura MVC.
- Spring Boot.
- Spring Security.
- DTO (Request y Response).
- JPA e Hibernate.
- Relaciones entre entidades.
- BCrypt.
- JWT.
- Manejo global de excepciones.
- Persistencia de datos con MySQL.
- Consumo y pruebas de APIs utilizando Postman.

---

# Mejoras futuras

- Implementar Spring Security con JWT.
- Incorporar paginación y filtros.
- Agregar pruebas unitarias con JUnit y Mockito.
- Contenerizar la aplicación con Docker.
- Desarrollar un frontend para consumir la API.
- Desplegar la aplicación en la nube.

---

# Autor

**George Bratt Díaz Castañeda**

Tecnólogo en Análisis y Desarrollo de Software.

GitHub: https://github.com/bratt10