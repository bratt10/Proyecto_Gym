package com.gym.gym.Services;

import java.time.LocalDate;
import java.util.List;
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
        MembresiasModel membresia = membresiasService.obtenerMembresiaPorId(membresiaId);
        pago.setMembresia(membresia);
        pago.setFechaPago(LocalDate.now());
        membresiasService.extenderMembresia(membresiaId, 30); 
        PagosModel pagohecho = pagosRepository.save(pago);
        return pagohecho;
    }

    public List<PagosModel> obtenerPagosPorMembresiaId(Long membresiaId) {
    membresiasService.obtenerMembresiaPorId(membresiaId);
    return pagosRepository.findByMembresiaId(membresiaId);
    }

    public List<PagosModel> obtenerTodosLosPagos() {
       List<PagosModel> pagos = pagosRepository.findAll();
        if(pagos.isEmpty()) { 
            throw new IllegalArgumentException("No hay pagos registrados");
        } 
        return pagos;
    }
    
    public void eliminarPago(Long id){
        if(!pagosRepository.existsById(id)){
            throw new IllegalArgumentException("El pago no existe");
        }
        pagosRepository.deleteById(id);
    }
}


