package com.gym.gym.Services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gym.gym.Model.MembresiasModel;
import com.gym.gym.Model.PagosModel;
import com.gym.gym.Respository.PagosRepository;

@Service
public class PagosService {
    private final PagosRepository pagosRepository;
    private final MembresiasService membresiasService;

    public PagosService(PagosRepository pagosRepository, MembresiasService membresiasService) {
        this.pagosRepository = pagosRepository;
        this.membresiasService = membresiasService;
    }

    public PagosModel crearPago(PagosModel pago, Long membresiaId) {
        Optional<MembresiasModel> membresia = membresiasService.obtenerMembresiaPorMiembroId(membresiaId);
        MembresiasModel membresiareal = membresia.get();
        pago.setMembresia(membresiareal);
        pago.setFechaPago(LocalDate.now());
        membresiasService.extenderMembresia(membresiaId, 30); 
        PagosModel pagohecho = pagosRepository.save(pago);
        return pagohecho;
    }
}
