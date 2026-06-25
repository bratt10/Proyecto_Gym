package com.gym.gym.Services;

import org.springframework.stereotype.Service;

import com.gym.gym.Model.PagosModel;
import com.gym.gym.Respository.PagosRepository;

@Service
public class PagosService {
    public PagosRepository pagosRepository;

    public PagosService(PagosRepository pagosRepository) {
        this.pagosRepository = pagosRepository;
    }

    public PagosModel crearPago(PagosModel pago) {
        return pagosRepository.save(pago);
    }
}
