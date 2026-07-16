package com.gym.gym.DTO.Request;


import com.gym.gym.Model.PagosModel.MetodoPago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor  
public class PagoRequestDTO {
    private double monto;
    private MetodoPago metodoPago;
    
}
