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
        try {
            EntrenadorResponseDTO nuevoEntrenador = entrenadorService.crearEntrenador(entrenador);
            return ResponseEntity.ok(nuevoEntrenador);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @GetMapping
    public ResponseEntity<?> getObtenerTodosEntrenadores() {
        try {
            return ResponseEntity.ok(entrenadorService.obtenerTodosEntrenadores());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getObtenerEntrenadorPorId(@PathVariable Long id) {
        try {
            EntrenadoresModel entrenador = entrenadorService.obtenerEntrenadorPorId(id);
            return ResponseEntity.ok(entrenador);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntrenador(@PathVariable Long id) {
        try {
            entrenadorService.eliminarEntrenador(id);
            return ResponseEntity.ok("Entrenador eliminado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @PatchMapping ("/{id}/estado")
    public ResponseEntity<?> postCambiarEstadoEntrenador(@PathVariable Long id, @RequestBody Estado nuevoEstado) {
        try {
            return ResponseEntity.ok("Estado del entrenador cambiado correctamente");            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putActualizarEntrenador(@PathVariable Long id, @RequestBody EntrenadoresModel entrenadorActualizado) {
            try {
                EntrenadoresModel entrenador = entrenadorService.actualizarEntrenador(id, entrenadorActualizado);
                return ResponseEntity.ok(entrenador);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Error interno del servidor");
            }
        }

    @GetMapping("/{id}/estado")
    public ResponseEntity<?> getConsultarEstadoActivoEntrenador(@PathVariable Long id) {
        try {
            boolean esActivo = entrenadorService.consultarEstadoActivoEntrenador(id);
            return ResponseEntity.ok(esActivo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
