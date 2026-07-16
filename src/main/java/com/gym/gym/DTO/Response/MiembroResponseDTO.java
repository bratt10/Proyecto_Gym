package com.gym.gym.DTO.Response;

import com.gym.gym.Model.Estado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiembroResponseDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Estado estado;
    private String nombreEntrenador;
}
