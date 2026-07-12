<pre>
Pruebas desde postman consumiento a las APIRest y su respectiva respuesta. Esto antes de agregar mas validacion y DTO

Crear un entrenador (No depende de nada)
Protocolo POST: http://localhost:8080/api/entrenadores
Request:
{
    "nombre": "Carlos",
    "apellido": "García",
    "especialidad": "Pesas",
    "telefono": "3001234567"
}
Response:
{
    "id": 1,
    "nombre": "Carlos",
    "apellido": "García",
    "especialidad": "Pesas",
    "telefono": "3001234567",
    "estado": "ACTIVO"
}


Crear un miembro (Depende del entrenador)
Protocolo POST: http://localhost:8080/api/miembros/1
Request:
{
    "nombre": "Bratt",
    "apellido": "Diaz",
    "email": "Bratt10@gmail.com",
    "telefono": "3207684953",
    "fechaNacimiento": "2006-07-10"
}
Response:
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


Crear membresía (depende del miembro)
Protocolo: http://localhost:8080/api/membresias/2
Request:
{
    "tipoMembresia": "MENSUAL",
    "fechaInicio": "2026-07-09",
    "fechaFin": "2026-07-10",
    "precio": 60000.00
}
Response:
{
    "id": 1,
    "tipoMembresia": "MENSUAL",
    "fechaInicio": "2026-07-09",
    "fechaFin": "2026-07-10",
    "estado": "ACTIVO",
    "precio": 60000.0
}

Protocolos GET para obtener datos 

Listar todos los entrenadores
GET : http://localhost:8080/api/entrenadores
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

Obtener entrenador por id
GET : http://localhost:8080/api/entrenadores/1
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

Listar todos los miembros
GET : http://localhost:8080/api/miembros
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

Obtener miembro por id
GET : http://localhost:8080/api/miembros/2
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

Ver membresía de un miembro
GET : http://localhost:8080/api/membresias/2
{
    "id": 1,
    "tipoMembresia": "MENSUAL",
    "fechaInicio": "2026-07-09",
    "fechaFin": "2026-07-10",
    "estado": "ACTIVO",
    "precio": 60000.0
}

Registrar un pago
POST : http://localhost:8080/api/pagos/1
{
    "monto": 60000.00,
    "metodoPago": "EFECTIVO"
}

Actualizar un miembro
PUT : http://localhost:8080/api/miembros/1
{
    "telefono": "3119999999"
}
Response:
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

Cambiar estado de un miembro
PATCH : http://localhost:8080/api/miembros/1/estado
"INACTIVO"
Response:
TRUE
</pre>
