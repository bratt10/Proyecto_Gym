package com.gym.gym.ServiciosTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gym.gym.DTO.Request.MembresiaRequestDTO;
import com.gym.gym.Exception.DuplicateResourceException;
import com.gym.gym.Exception.ResourceNotFoundException;
import com.gym.gym.Model.MembresiasModel;
import com.gym.gym.Model.MembresiasModel.TipoMembresia;
import com.gym.gym.Respository.MembresiaRespository;
import com.gym.gym.Services.MembresiasService;

@ExtendWith(MockitoExtension.class)
public class MembresiaTest {
    @Mock
    private MembresiaRespository membresiarepo;

    @InjectMocks
    private MembresiasService memebresiaservice;

    @Test
    void crearMembresia_cuandoMiembroYaTieneMembresia_lanzaExcepcion() {

    MembresiaRequestDTO dto = new MembresiaRequestDTO();
    dto.setTipoMembresia(TipoMembresia.MENSUAL);
    dto.setFechaInicio(LocalDate.now());
    dto.setPrecio(60.0);

    MembresiasModel membresiaExistente = new MembresiasModel();

    when(membresiarepo.findByMiembroId(1L)).thenReturn(Optional.of(membresiaExistente));

    assertThrows(DuplicateResourceException.class,() -> memebresiaservice.crearMembresia(1L, dto));
}

    @Test
    void obtenerMembresia_noEncontrada_lanzaExcepcion() {
        when(membresiarepo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> memebresiaservice.obtenerMembresiaPorId(99L));
    }
}
