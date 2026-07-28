package com.gym.gym.Exception;

public class BusinessException extends RuntimeException {
    public BusinessException(String mensaje){
        super(mensaje);
    }
}
