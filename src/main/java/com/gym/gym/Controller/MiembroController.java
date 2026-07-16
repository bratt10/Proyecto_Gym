package com.gym.gym.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.gym.DTO.Request.MiembroRequestDTO;
import com.gym.gym.DTO.Response.MiembroResponseDTO;
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
    public ResponseEntity<?> postCrearMiembro(@RequestBody MiembroRequestDTO miembroDTO, @PathVariable Long entrenadorId) {        
        MiembroResponseDTO nuevoMiembro = miembrosService.crearMiembro(miembroDTO, entrenadorId);
        return ResponseEntity.ok(nuevoMiembro);
 
    }
    
    @GetMapping
    public ResponseEntity<?> getobtenerMiembros() {
     
        return ResponseEntity.ok(miembrosService.obtenerMiembros());
  
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getObtenerMiembroPorID(@PathVariable Long id) {
        MiembrosModel miembro = miembrosService.obtenerPorId(id).get();
        return ResponseEntity.ok(miembro);
  
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putActualizarMiembro(@PathVariable Long id, @RequestBody MiembrosModel miembroActualizado) {
        MiembrosModel miembro = miembrosService.actualizarMiembro(id, miembroActualizado);
        return ResponseEntity.ok(miembro);
 
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<?> patchActualizarEstadoMiembro(@PathVariable Long id, @RequestBody Estado nuevoEstado) {
        boolean resultado = miembrosService.actualizarEstadoMiembro(id, nuevoEstado);
        return ResponseEntity.ok(resultado);
 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEliminarMiembro(@PathVariable Long id) {
        miembrosService.eliminarMiembro(id);
        return ResponseEntity.ok("Miembro eliminado correctamente");
   
    }
}