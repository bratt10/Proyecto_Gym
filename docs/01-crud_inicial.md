# Etapa 1 - CRUD Inicial

En esta primera versión del proyecto se desarrolló el CRUD básico del sistema para gestionar entrenadores, miembros, membresías y pagos mediante una API REST.

En esta etapa el objetivo principal fue implementar la lógica de negocio y comprobar el funcionamiento de los endpoints antes de incorporar mejoras como DTO, validaciones, autenticación y manejo global de excepciones.

---

# Características de esta versión

- CRUD básico de entrenadores.
- CRUD básico de miembros.
- CRUD básico de membresías.
- Registro de pagos.
- Persistencia de datos con MySQL.
- Pruebas realizadas desde Postman.
- Respuestas utilizando directamente las entidades del modelo.
- Sin DTO.
- Sin Spring Security.
- Sin autenticación JWT.
- Sin validaciones personalizadas.

---

# Flujo de creación

Para registrar correctamente la información se seguía el siguiente orden:

1. Crear un entrenador.
2. Crear un miembro asociado al entrenador.
3. Crear una membresía para el miembro.
4. Registrar un pago.

---

# Crear entrenador

**Método**

```http
POST /api/entrenadores
```

### Request

```json
{
    "nombre": "Carlos",
    "apellido": "García",
    "especialidad": "Pesas",
    "telefono": "3001234567"
}
```

### Response

```json
{
    "id": 1,
    "nombre": "Carlos",
    "apellido": "García",
    "especialidad": "Pesas",
    "telefono": "3001234567",
    "estado": "ACTIVO"
}
```

---

# Crear miembro

> Requiere que exista un entrenador.

**Método**

```http
POST /api/miembros/1
```

### Request

```json
{
    "nombre": "Bratt",
    "apellido": "Diaz",
    "email": "Bratt10@gmail.com",
    "telefono": "3207684953",
    "fechaNacimiento": "2006-07-10"
}
```

### Response

```json
{
    "id": 2,
    "nombre": "Bratt",
    "apellido": "Diaz",
    "email": "Bratt10@gmail.com",
    "telefono": "3207684953",
    "fechaNacimiento": "2006-07-10",
    "fechaRegistro": "2026-07-09",
    "estado": "ACTIVO"
}
```

---

# Crear membresía

> Requiere que exista un miembro.

**Método**

```http
POST /api/membresias/2
```

### Request

```json
{
    "tipoMembresia": "MENSUAL",
    "fechaInicio": "2026-07-09",
    "fechaFin": "2026-07-10",
    "precio": 60000.00
}
```

### Response

```json
{
    "id": 1,
    "tipoMembresia": "MENSUAL",
    "fechaInicio": "2026-07-09",
    "fechaFin": "2026-07-10",
    "estado": "ACTIVO",
    "precio": 60000.0
}
```

---

# Consultas GET

## Listar entrenadores

```http
GET /api/entrenadores
```

### Response

```json
[
    {
        "id": 1,
        "nombre": "Carlos",
        "apellido": "García",
        "especialidad": "Pesas",
        "telefono": "3001234567",
        "estado": "ACTIVO"
    }
]
```

---

## Obtener entrenador por ID

```http
GET /api/entrenadores/1
```

### Response

```json
{
    "id": 1,
    "nombre": "Carlos",
    "apellido": "García",
    "especialidad": "Pesas",
    "telefono": "3001234567",
    "estado": "ACTIVO"
}
```

---

## Listar miembros

```http
GET /api/miembros
```

### Response

```json
[
    {
        "id": 1,
        "nombre": "Juan",
        "apellido": "Pérez",
        "email": "juan@email.com",
        "telefono": "3109876543",
        "fechaNacimiento": "1995-05-20",
        "fechaRegistro": "2026-07-09",
        "estado": "ACTIVO"
    },
    {
        "id": 2,
        "nombre": "Bratt",
        "apellido": "Diaz",
        "email": "Bratt10@gmail.com",
        "telefono": "3207684953",
        "fechaNacimiento": "2006-07-10",
        "fechaRegistro": "2026-07-09",
        "estado": "ACTIVO"
    }
]
```

---

## Obtener miembro por ID

```http
GET /api/miembros/2
```

### Response

```json
{
    "id": 2,
    "nombre": "Bratt",
    "apellido": "Diaz",
    "email": "Bratt10@gmail.com",
    "telefono": "3207684953",
    "fechaNacimiento": "2006-07-10",
    "fechaRegistro": "2026-07-09",
    "estado": "ACTIVO"
}
```

---

## Consultar membresía

```http
GET /api/membresias/2
```

### Response

```json
{
    "id": 1,
    "tipoMembresia": "MENSUAL",
    "fechaInicio": "2026-07-09",
    "fechaFin": "2026-07-10",
    "estado": "ACTIVO",
    "precio": 60000.0
}
```

---

# Registrar un pago

```http
POST /api/pagos/1
```

### Request

```json
{
    "monto": 60000.00,
    "metodoPago": "EFECTIVO"
}
```

---

# Actualizar un miembro

```http
PUT /api/miembros/1
```

### Request

```json
{
    "telefono": "3119999999"
}
```

### Response

```json
{
    "id": 1,
    "nombre": "Juan",
    "apellido": "Pérez",
    "email": "juan@email.com",
    "telefono": "3119999999",
    "fechaNacimiento": "1995-05-20",
    "fechaRegistro": "2026-07-09",
    "estado": "ACTIVO"
}
```

---

# Cambiar estado de un miembro

```http
PATCH /api/miembros/1/estado
```

### Request

```json
"INACTIVO"
```

### Response

```json
true
```

---

# Conclusiones

Esta primera versión permitió comprobar el funcionamiento del CRUD y las relaciones entre las entidades del sistema. Sin embargo, las respuestas exponían directamente las entidades de la base de datos, aún no existían validaciones de negocio, manejo centralizado de excepciones ni mecanismos de autenticación.

Estas mejoras fueron implementadas en la siguiente etapa del proyecto.