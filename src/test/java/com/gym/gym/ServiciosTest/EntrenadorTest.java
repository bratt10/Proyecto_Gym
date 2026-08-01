package com.gym.gym.ServiciosTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gym.gym.DTO.Request.EntrenadorRequestDTO;
import com.gym.gym.Exception.BusinessException;
import com.gym.gym.Exception.DuplicateResourceException;
import com.gym.gym.Exception.ResourceNotFoundException;
import com.gym.gym.Respository.EntrenadoresRepository;
import com.gym.gym.Services.EntrenadorService;

@ExtendWith(MockitoExtension.class)
public class EntrenadorTest {

    @Mock
    private EntrenadoresRepository entrenador;

    @InjectMocks
    private EntrenadorService entrenadorservice;

    @Test
    void crearentrenador_entrandorduplicado(){
        EntrenadorRequestDTO dto = new EntrenadorRequestDTO();
        dto.setNombre("Frank");
        dto.setApellido("Romero");
        dto.setEspecialidad("PESAS");
        dto.setTelefono("3209481928");

        when(entrenador.existsByNombreAndApellido("Frank", "Romero")).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> entrenadorservice.crearEntrenador(dto));
    }

    @Test
    void crearentrenador_entrandornombrevacioNULL(){
        EntrenadorRequestDTO dto = new EntrenadorRequestDTO();
        dto.setNombre("");
        dto.setApellido("Romero");
        dto.setEspecialidad("PESAS");
        dto.setTelefono("3209481928");

        when(entrenador.existsByNombreAndApellido("", "Romero")).thenReturn(false);

        assertThrows(BusinessException.class, () -> entrenadorservice.crearEntrenador(dto));
    
    }

    @Test
    void crearentrenador_entrandorletraseneltelefono(){
        EntrenadorRequestDTO dto = new EntrenadorRequestDTO();
        dto.setNombre("frank");
        dto.setApellido("Romero");
        dto.setEspecialidad("PESAS");
        dto.setTelefono("3IO948g928");

        when(entrenador.existsByNombreAndApellido("frank", "Romero")).thenReturn(false);

        assertThrows(BusinessException.class, () -> entrenadorservice.crearEntrenador(dto));
    
    }

    @Test
    void obtenerEntrenador_noEncontrado_lanzaExcepcion(){
        when(entrenador.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> 
            entrenadorservice.obtenerEntrenadorPorId(99L));
    }

    
  
    
}
