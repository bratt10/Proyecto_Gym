package com.gym.gym.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.gym.DTO.Request.MembresiaRequestDTO;
import com.gym.gym.DTO.Response.MembresiaRepsonseDTO;
import com.gym.gym.Model.MembresiasModel;
import com.gym.gym.Services.MembresiasService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/membresias")
public class MembresiaController {
    private final MembresiasService membresiasService;
    
    public MembresiaController(MembresiasService membresiasService) {
        this.membresiasService = membresiasService;
    }

    @PostMapping("/{miembroID}")
    public ResponseEntity<?> postcrearMembresia(@RequestBody MembresiaRequestDTO membresia, @PathVariable Long miembroID){

        MembresiaRepsonseDTO membresiacreada = membresiasService.crearMembresia(miembroID, membresia);
        return ResponseEntity.ok(membresiacreada);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMembresiaDelMiembro(@PathVariable Long id) {
        MembresiasModel membresia = membresiasService.obtenerMembresiaDelMiembro(id).get();
        return ResponseEntity.ok(membresia);
    }

    @GetMapping
       public ResponseEntity<?> getmostrarTodos() {
        return ResponseEntity.ok(membresiasService.obtenerMembresias());
 
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> putactualizarmembresia(@RequestBody MembresiasModel membresia, @PathVariable Long id) {
     
        MembresiasModel membresiaactualizada = membresiasService.actualizarMembresia(id, membresia);
        return ResponseEntity.ok(membresiaactualizada);
  

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletemembresia(@PathVariable Long id){
        membresiasService.eliminarMembresia(id);
        return ResponseEntity.ok("Miembro eliminado exitosamente");
  

    }
        
    
    
}
