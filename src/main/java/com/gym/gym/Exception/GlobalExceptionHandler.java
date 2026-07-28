package com.gym.gym.Exception;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> RespuestaEntradaDuplicada(DuplicateResourceException e, HttpServletRequest request){
        ErrorResponse error =  new ErrorResponse(409, e.getMessage(), LocalDateTime.now(), request.getRequestURI());
        return ResponseEntity.status(409).body(error);
        
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?>  datoNoEncontradoEnElSistema(ResourceNotFoundException e, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(404, e.getMessage(), LocalDateTime.now(), request.getRequestURI());
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> respuestaLaoperaciónnoestápermitida(BusinessException e, HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(400, e.getMessage(), LocalDateTime.now(), request.getRequestURI());
        return ResponseEntity.status(400).body(error);
    }
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
