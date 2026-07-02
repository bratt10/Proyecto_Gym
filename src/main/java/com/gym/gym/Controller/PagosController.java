package com.gym.gym.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.gym.Model.PagosModel;
import com.gym.gym.Services.PagosService;

@RestController
@RequestMapping("/api/pagos")
public class PagosController {
    private final PagosService pagosService;

    public PagosController(PagosService pagosService) {
        this.pagosService = pagosService;
    }

    @PostMapping("/{miembroId}")
    public ResponseEntity<?> postRegistrarPago(@RequestBody PagosModel pago, @PathVariable Long miembroId) {
        try {
            PagosModel nuevoPago = pagosService.crearPago(pago, miembroId);
            return ResponseEntity.ok(nuevoPago);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @GetMapping("/{miembroId}")
    public ResponseEntity<?> getObtenerPagosPorMembresiaId(@PathVariable Long miembroId) {
        try {
            return ResponseEntity.ok(pagosService.obtenerPagosPorMembresiaId(miembroId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @GetMapping
    public ResponseEntity<?> getObtenerTodosLosPagos() {
        try {
            return ResponseEntity.ok(pagosService.obtenerTodosLosPagos());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> postEliminarPago(@PathVariable Long id) {
        try {
            pagosService.eliminarPago(id);
            return ResponseEntity.ok("Pago eliminado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error interno del servidor");
        }
    }
}
