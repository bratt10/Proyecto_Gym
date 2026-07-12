package com.gym.gym.DTO.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrenadorRequestDTO {
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
}
