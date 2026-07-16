package com.gym.gym.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControladorGlobalDeException {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?>   RespuestamanejarIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> RespuestamanejarException(Exception e){
        return ResponseEntity.status(500).body("Error interno del servidor");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> manejarJsonMalFormado(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body("El JSON enviado tiene un formato inválido");
    }
}
