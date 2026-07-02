package com.gym.gym.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.gym.Model.Estado;
import com.gym.gym.Model.MiembrosModel;
import com.gym.gym.Services.MiembrosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/miembros")
public class MiembroController {
    private final MiembrosService miembrosService;

    public MiembroController(MiembrosService miembrosService) {
        this.miembrosService = miembrosService;
    }

    @PostMapping ("/{entrenadorId}")
    public ResponseEntity<?> postCrearMiembro(@RequestBody MiembrosModel miembro, @PathVariable Long entrenadorId) {        
        try {
            MiembrosModel nuevoMiembro = miembrosService.crearMiembro(miembro, entrenadorId);
            return ResponseEntity.ok(nuevoMiembro);
        }catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getobtenerMiembros() {
        try {
            return ResponseEntity.ok(miembrosService.obtenerMiembros());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getObtenerMiembroPorID(@PathVariable Long id) {
        try {
            MiembrosModel miembro = miembrosService.obtenerPorId(id).get();
            return ResponseEntity.ok(miembro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putActualizarMiembro(@PathVariable Long id, @RequestBody MiembrosModel miembroActualizado) {
        try {
            MiembrosModel miembro = miembrosService.actualizarMiembro(id, miembroActualizado);
            return ResponseEntity.ok(miembro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> patchActualizarEstadoMiembro(@PathVariable Long id, @RequestBody Estado nuevoEstado) {
        try {
            boolean resultado = miembrosService.actualizarEstadoMiembro(id, nuevoEstado);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEliminarMiembro(@PathVariable Long id) {
        try {
            miembrosService.eliminarMiembro(id);
            return ResponseEntity.ok("Miembro eliminado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}