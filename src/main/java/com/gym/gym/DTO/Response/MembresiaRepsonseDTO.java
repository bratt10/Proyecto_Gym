package com.gym.gym.DTO.Response;

import java.time.LocalDate;

import com.gym.gym.Model.Estado;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembresiaRepsonseDTO {
    private String tipoMembresia;
    private LocalDate  fechaInicio;
    private LocalDate  fechaFin;
    private Estado estado;
    private Double precio;
    private String nombreMiembro;
}
