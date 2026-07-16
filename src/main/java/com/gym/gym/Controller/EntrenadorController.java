package com.gym.gym.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.gym.DTO.Request.EntrenadorRequestDTO;
import com.gym.gym.DTO.Response.EntrenadorResponseDTO;
import com.gym.gym.Model.EntrenadoresModel;
import com.gym.gym.Model.Estado;
import com.gym.gym.Services.EntrenadorService;


@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorController {
    
    private final EntrenadorService entrenadorService;
    
    public EntrenadorController(EntrenadorService entrenadorService) {
        this.entrenadorService = entrenadorService;
    }

    @PostMapping
    public ResponseEntity<?> postCrearEntrenador(@RequestBody EntrenadorRequestDTO entrenador) {
        EntrenadorResponseDTO nuevoEntrenador = entrenadorService.crearEntrenador(entrenador);
        return ResponseEntity.ok(nuevoEntrenador);
    }

    @GetMapping
    public ResponseEntity<?> getObtenerTodosEntrenadores() {
        return ResponseEntity.ok(entrenadorService.obtenerTodosEntrenadores());
 
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getObtenerEntrenadorPorId(@PathVariable Long id) {
        EntrenadoresModel entrenador = entrenadorService.obtenerEntrenadorPorId(id);
        return ResponseEntity.ok(entrenador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntrenador(@PathVariable Long id) {
        entrenadorService.eliminarEntrenador(id);
        return ResponseEntity.ok("Entrenador eliminado correctamente");
    }

    @PatchMapping ("/{id}/estado")
    public ResponseEntity<?> postCambiarEstadoEntrenador(@PathVariable Long id, @RequestBody Estado nuevoEstado) {
        entrenadorService.cambiarEstadoEntrenador(id, nuevoEstado);
        return ResponseEntity.ok("Estado del entrenador cambiado correctamente");            
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putActualizarEntrenador(@PathVariable Long id, @RequestBody EntrenadoresModel entrenadorActualizado) {
        EntrenadoresModel entrenador = entrenadorService.actualizarEntrenador(id, entrenadorActualizado);
        return ResponseEntity.ok(entrenador);
    }

    @GetMapping("/{id}/estado")
    public ResponseEntity<?> getConsultarEstadoActivoEntrenador(@PathVariable Long id) {
        boolean esActivo = entrenadorService.consultarEstadoActivoEntrenador(id);
        return ResponseEntity.ok(esActivo);
    }
}
