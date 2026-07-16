package com.gym.gym.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequestDTO {
    private String nombre;
    private String apellido;
    private String nombredegym;
    private String correo;
    private String contraseña;
}
