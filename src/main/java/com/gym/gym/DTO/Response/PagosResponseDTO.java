package com.gym.gym.DTO.Response;

import com.gym.gym.Model.PagosModel.MetodoPago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagosResponseDTO {
    private String nombreMiembro;
    private Double monto;
    private MetodoPago metodoPago;
    private String mensaje;
}