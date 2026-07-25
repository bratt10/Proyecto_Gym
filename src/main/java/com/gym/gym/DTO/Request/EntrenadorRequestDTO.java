package com.gym.gym.DTO.Request;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class EntrenadorRequestDTO {
    private String nombre;
    private String apellido;
    private String especialidad;
    private String telefono;
}
