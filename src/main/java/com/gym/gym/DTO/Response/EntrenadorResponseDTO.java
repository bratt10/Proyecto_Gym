package com.gym.gym.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrenadorResponseDTO {
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
}
