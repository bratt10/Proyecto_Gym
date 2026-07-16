package com.gym.gym.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {
    private String correo;
    private String contraseña;
}
