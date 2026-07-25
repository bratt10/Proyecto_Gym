package com.gym.gym.DTO.Request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gym.gym.Model.MembresiasModel.TipoMembresia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class MembresiaRequestDTO {
    private TipoMembresia tipoMembresia;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double precio;
}
