# Etapa 2 - Implementación de DTO y mejoras en la API

Después de finalizar el CRUD inicial, el siguiente paso fue mejorar la forma en que la API se comunicaba con el cliente. Para ello se implementaron DTO (Data Transfer Object), permitiendo controlar la información que se envía y se recibe en cada petición.

Además, se comenzó a preparar la aplicación para futuras mejoras relacionadas con seguridad, validaciones y autenticación.

---

# Objetivos de esta etapa

- Implementar DTO para los métodos POST.
- Evitar exponer directamente las entidades de la base de datos.
- Mejorar las respuestas de la API.
- Agregar información útil al cliente sin exponer datos internos.
- Implementar el registro y autenticación del administrador.

---

# Cambios realizados

## Entrenadores

En la primera versión la API devolvía toda la entidad.

### Antes

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

### Ahora

```http
POST /api/entrenadores
```

#### Request

```json
{
    "nombre": "Yaidis",
    "apellido": "Guzman",
    "especialidad": "cardio",
    "telefono": "3001234567"
}
```

#### Response

```json
{
    "nombre": "Yaidis",
    "apellido": "Guzman",
    "especialidad": "cardio",
    "telefono": "3001234567"
}
```

### Cambios

- Se implementó DTO Response.
- Ya no se devuelve el ID.
- Solo se responde con la información necesaria.

---

# Miembros

También se implementó un DTO para la creación de miembros.

```http
POST /api/miembros/2
```

### Request

```json
{
    "nombre": "Alfonso",
    "apellido": "Lara",
    "email": "alfonso10@gmail.com",
    "telefono": "3041748178",
    "fechaNacimiento": "2000-05-20"
}
```

### Response

```json
{
    "nombre": "Alfonso",
    "apellido": "Lara",
    "email": "alfonso10@gmail.com",
    "telefono": "3041748178",
    "estado": "ACTIVO",
    "nombreEntrenador": "Yaidis"
}
```

### Cambios

- Se oculta el ID del miembro.
- Se evita devolver toda la entidad.
- Se agrega el nombre del entrenador asociado para facilitar la lectura de la respuesta.

---

# Membresías

La respuesta fue modificada para mostrar información más útil.

```http
POST /api/membresias/4
```

### Request

```json
{
    "tipoMembresia": "MENSUAL",
    "fechaInicio": "2026-07-15",
    "fechaFin": "2026-08-15",
    "precio": 60000.00
}
```

### Response

```json
{
    "tipoMembresia": "MENSUAL",
    "fechaInicio": "2026-07-15",
    "fechaFin": "2026-08-15",
    "estado": "ACTIVO",
    "precio": 60000.0,
    "nombreMiembro": "Ricardo Diaz"
}
```

### Cambios

- Se eliminó el identificador interno.
- Se agregó el nombre del miembro propietario de la membresía.

---

# Pagos

También se mejoró la respuesta del registro de pagos.

```http
POST /api/pagos/3
```

### Request

```json
{
    "monto": 60000.00,
    "metodoPago": "EFECTIVO"
}
```

### Response

```json
{
    "nombreMiembro": "Ricardo",
    "monto": 60000.0,
    "metodoPago": "EFECTIVO",
    "mensaje": "Pago registrado exitosamente"
}
```

### Cambios

- Se devuelve el nombre del miembro.
- Se agrega un mensaje descriptivo indicando que el pago fue registrado correctamente.

---

# Implementación del administrador

En esta etapa también se desarrolló el módulo encargado de administrar el sistema.

Se agregó:

- Registro de administradores.
- Inicio de sesión.
- Encriptación de contraseñas mediante BCrypt.
- DTO para el registro.
- DTO para el inicio de sesión.

---

# Registrar administrador

```http
POST /api/admin
```

### Request

```json
{
    "nombre": "Bratt",
    "apellido": "Diaz",
    "nombredegym": "GymBratt",
    "correo": "admin@gym.com",
    "contraseña": "12345"
}
```

### Response

```json
{
    "nombre": "Bratt",
    "apellido": "Diaz",
    "nombredegym": "GymBratt",
    "correo": "admin@gym.com"
}
```

En la respuesta ya no se devuelve la contraseña almacenada en la base de datos.

---

# Inicio de sesión

```http
POST /api/admin/login
```

### Request

```json
{
    "correo": "admin@gym.com",
    "contraseña": "1234"
}
```

### Respuesta incorrecta

```json
{
    "success": false,
    "message": "Correo o contraseña INCORRECTA"
}
```

### Respuesta correcta

```json
{
    "success": true,
    "message": "Ingreso exitoso. Bienvenido usuario Bratt Diaz"
}
```

---

# Lo aprendido en esta etapa

Durante esta fase del proyecto reforcé conocimientos sobre:

- DTO Request y Response.
- Separación entre entidades y respuestas de la API.
- Diseño de respuestas más limpias.
- Encriptación de contraseñas con BCrypt.
- Desarrollo de un módulo de autenticación.
- Organización del proyecto para facilitar futuras implementaciones.

---

# Próximo paso

Con la implementación de DTO y el módulo de autenticación listo, la siguiente etapa del proyecto consiste en incorporar Spring Security y JWT para proteger los endpoints y controlar el acceso a la API.