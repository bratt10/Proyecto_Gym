package com.gym.gym.ServiciosTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gym.gym.DTO.Request.PagoRequestDTO;
import com.gym.gym.Exception.BusinessException;
import com.gym.gym.Exception.ResourceNotFoundException;
import com.gym.gym.Model.Estado;
import com.gym.gym.Model.MembresiasModel;
import com.gym.gym.Model.PagosModel.MetodoPago;
import com.gym.gym.Respository.PagosRepository;
import com.gym.gym.Services.MembresiasService;
import com.gym.gym.Services.PagosService;

@ExtendWith(MockitoExtension.class)
public class PagosServiceTest {
    @Mock
    private PagosRepository pagosRepository;
    @Mock
    private MembresiasService membresiasService;
    @InjectMocks
    private PagosService pagosService;

    @Test
    void crearpago_membresiaINACTIVA_lanzaExcepcion() {
        PagoRequestDTO dto = new PagoRequestDTO();
        dto.setMetodoPago(MetodoPago.EFECTIVO);
        dto.setMonto(60.000);
        MembresiasModel membresia = new MembresiasModel();
        membresia.setEstado(Estado.INACTIVO);

        when(membresiasService.obtenerMembresiaPorId(1L)).thenReturn(membresia);
        
        assertThrows(BusinessException.class, () -> pagosService.crearPago(dto, 1L));    
    }
    @Test
    void eliminarpago_pagoNoExiste_lanzaExcepcion() {
        when(pagosRepository.existsById(99L)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> pagosService.eliminarPago(99L));
    }
    
}
