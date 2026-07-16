<pre>
Pruebas desde postman consumiento a las APIRest y su respectiva respuesta. luego de agregar mas validacion y DTO

Crear un entrenador (No depende de nada)
Protocolo POST: http://localhost:8080/api/entrenadores
Request:
{
    "nombre": "Yaidis",
    "apellido": "Guzman",
    "especialidad": "cardio",
    "telefono": "3001234567"
}
Response:
{
    "nombre": "Yaidis",
    "apellido": "Guzman",
    "especialidad": "cardio",
    "telefono": "3001234567"
}

Ya no devuelve todos los datos del objeto 


Crear un miembro (Depende del entrenador)
Protocolo POST: http://localhost:8080/api/miembros/2
Request:
{
    "nombre": "Alfonso",
    "apellido": "lara",
    "email": "alfonso10@gmail.com",
    "telefono": "3041748178",
    "fechaNacimiento": "2000-05-20"
}
Response:
{
    "nombre": "Alfonso",
    "apellido": "lara",
    "email": "alfonso10@gmail.com",
    "telefono": "3041748178",
    "estado": "ACTIVO",
    "nombreEntrenador": "Yaidis"
}

Ya no devuelve todos los datos del objeto se restringe el ID 

Crear membresía (depende del miembro)
Protocolo: http://localhost:8080/api/membresias/4
Request:
{
    "tipoMembresia": "MENSUAL",
    "fechaInicio": "2026-07-15",
    "fechaFin": "2026-08-15",
    "precio": 60000.00
}
Response:
{
    "tipoMembresia": "MENSUAL",
    "fechaInicio": "2026-07-15",
    "fechaFin": "2026-08-15",
    "estado": "ACTIVO",
    "precio": 60000.0,
    "nombreMiembro": "Ricardo Diaz"
}

Se agrega el campo de nombre para saber a quien se asocia esta membresia

Registrar un pago
POST : http://localhost:8080/api/pagos/3
Request:
{
    "monto": 60000.00,
    "metodoPago": "EFECTIVO"
}

Response: 

{
    "nombreMiembro": "Ricardo",
    "monto": 60000.0,
    "metodoPago": "EFECTIVO",
    "mensaje": "Pago registrado exitosamente"
}

Hasta este punto solo se han agregado los DTO para los metodos POST 

</pre>
