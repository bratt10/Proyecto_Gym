package com.gym.gym.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponseDTO {
    private String nombre;
    private String apellido;
    private String nombredegym;
    private String correo;
}
