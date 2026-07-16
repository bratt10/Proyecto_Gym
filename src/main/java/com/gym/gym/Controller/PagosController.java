package com.gym.gym.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.gym.DTO.Request.PagoRequestDTO;
import com.gym.gym.DTO.Response.PagosResponseDTO;
import com.gym.gym.Services.PagosService;

@RestController
@RequestMapping("/api/pagos")
public class PagosController {
    private final PagosService pagosService;

    public PagosController(PagosService pagosService) {
        this.pagosService = pagosService;
    }

    @PostMapping("/{miembroId}")
    public ResponseEntity<?> postRegistrarPago(@RequestBody PagoRequestDTO pago, @PathVariable Long miembroId) {
        PagosResponseDTO nuevoPago = pagosService.crearPago(pago, miembroId);
        return ResponseEntity.ok(nuevoPago);
    }

    @GetMapping("/{miembroId}")
    public ResponseEntity<?> getObtenerPagosPorMembresiaId(@PathVariable Long miembroId) {
        return ResponseEntity.ok(pagosService.obtenerPagosPorMembresiaId(miembroId));
    
    }

    @GetMapping
    public ResponseEntity<?> getObtenerTodosLosPagos() {
        return ResponseEntity.ok(pagosService.obtenerTodosLosPagos());
   
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> postEliminarPago(@PathVariable Long id) {
            pagosService.eliminarPago(id);
            return ResponseEntity.ok("Pago eliminado correctamente");

    }
}
