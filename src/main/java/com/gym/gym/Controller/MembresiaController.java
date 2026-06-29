package com.gym.gym.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.gym.Model.MembresiasModel;
import com.gym.gym.Services.MembresiasService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/membresias")
public class MembresiaController {
    private final MembresiasService membresiasService;
    
    public MembresiaController(MembresiasService membresiasService) {
        this.membresiasService = membresiasService;
    }

    @PostMapping
    public ResponseEntity<?> postcrearMembresia(@RequestBody MembresiasModel membresia, @PathVariable Long miembroID){
        try {
           MembresiasModel membresiacreada = membresiasService.crearMembresia(miembroID, membresia);
            return ResponseEntity.ok(membresiacreada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception i){
            return ResponseEntity.status(500).body("Error en el servidor, intente mas tarde");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMembresiaDelMiembro(@PathVariable Long id) {
       try {
            MembresiasModel membresia = membresiasService.obtenerMembresiaDelMiembro(id).get();
            return ResponseEntity.ok(membresia);
       }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(Exception e){
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getmostrarTodos() {
        try {
            return ResponseEntity.ok(membresiasService.obtenerMembresias());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e){
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
    
    
}
