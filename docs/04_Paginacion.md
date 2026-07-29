# Etapa 4 - Implementación de paginación

Después de implementar la seguridad mediante JWT, el siguiente paso fue optimizar los endpoints que devuelven listas de registros. Para ello se implementó paginación utilizando las herramientas que ofrece Spring Data JPA.

Con esta mejora ya no es necesario consultar todos los registros de la base de datos en una sola petición, sino únicamente la cantidad solicitada por el cliente.

---

# Objetivos de esta etapa

- Optimizar las consultas a la base de datos.
- Evitar enviar grandes cantidades de información en una sola respuesta.
- Permitir al cliente decidir la página, la cantidad de registros y el campo de ordenamiento.
- Mejorar el rendimiento de la API.

---

# ¿Por qué implementar paginación?

En la versión inicial, los endpoints que listaban información devolvían todos los registros almacenados.

Por ejemplo:

```http
GET /api/miembros
```

Si la base de datos contiene cientos o miles de registros, la respuesta puede hacerse muy pesada, aumentando el tiempo de consulta y el consumo de memoria tanto del servidor como del cliente.

Con la paginación únicamente se devuelven los registros necesarios para cada consulta.

---

# Endpoints paginados

Actualmente se implementó paginación en los siguientes endpoints:

| Método | Endpoint |
|---------|----------|
| GET | `/api/miembros/paginado` |
| GET | `/api/pagos/paginado` |

---

# Parámetros disponibles

| Parámetro | Descripción |
|-----------|-------------|
| `page` | Número de página (empieza desde 0). |
| `size` | Cantidad de registros por página. |
| `sort` | Campo utilizado para ordenar los resultados. |

Ejemplo:

```http
GET /api/pagos/paginado?page=0&size=3&sort=fechaPago
Authorization: Bearer <token>
```

---

# Prueba realizada

Se realizó una consulta solicitando la primera página con un máximo de tres registros ordenados por la fecha del pago.

## Endpoint

```http
GET /api/pagos/paginado?page=0&size=3&sort=fechaPago
```

## Response

```json
{
    "content": [
        {
            "id": 1,
            "monto": 60000.0,
            "metodoPago": "EFECTIVO",
            "fechaPago": "2026-07-09"
        },
        {
            "id": 2,
            "monto": 60000.0,
            "metodoPago": "EFECTIVO",
            "fechaPago": "2026-07-15"
        }
    ],
    "empty": false,
    "first": true,
    "last": true,
    "number": 0,
    "numberOfElements": 2,
    "pageable": {
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 3,
        "paged": true,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "unpaged": false
    },
    "size": 3,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "totalElements": 2,
    "totalPages": 1
}
```

---

# Información que devuelve Page<T>

| Campo | Descripción |
|--------|-------------|
| `content` | Lista de registros de la página solicitada. |
| `number` | Página actual. |
| `size` | Cantidad máxima de registros por página. |
| `numberOfElements` | Cantidad de registros devueltos. |
| `totalElements` | Total de registros existentes en la base de datos. |
| `totalPages` | Número total de páginas disponibles. |
| `first` | Indica si corresponde a la primera página. |
| `last` | Indica si corresponde a la última página. |
| `empty` | Indica si la página contiene registros. |

---

# Implementación

Para esta funcionalidad se utilizaron las clases proporcionadas por Spring Data JPA:

- `Pageable`
- `Page`
- `PageRequest`
- `Sort`

La consulta se realiza directamente desde el repositorio utilizando la paginación nativa de Spring, por lo que el framework genera automáticamente las cláusulas `LIMIT` y `OFFSET` en la consulta SQL.

---

# Lo aprendido

Durante esta etapa reforcé conocimientos sobre:

- Paginación en APIs REST.
- `Page<T>`.
- `Pageable`.
- `PageRequest`.
- `Sort`.
- `@RequestParam`.
- Ordenamiento de resultados.
- Optimización de consultas.
- LIMIT y OFFSET generados automáticamente por Spring Data JPA.

---

# Resultado

Con esta mejora la API ya no devuelve todos los registros en una sola consulta. Ahora el cliente puede solicitar únicamente la información que necesita, reduciendo el tiempo de respuesta y mejorando el rendimiento de la aplicación cuando el volumen de datos aumenta.