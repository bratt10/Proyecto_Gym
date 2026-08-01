package com.gym.gym.ServiciosTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gym.gym.DTO.Request.MiembroRequestDTO;
import com.gym.gym.Exception.BusinessException;
import com.gym.gym.Exception.DuplicateResourceException;
import com.gym.gym.Model.EntrenadoresModel;
import com.gym.gym.Model.Estado;
import com.gym.gym.Respository.MiembrosRepository;
import com.gym.gym.Services.EntrenadorService;
import com.gym.gym.Services.MiembrosService;

@ExtendWith(MockitoExtension.class)
public class MiembrosServiceTest {
    @Mock
    private MiembrosRepository miembrosRepository;

    @Mock
    private EntrenadorService entrenador;

    @InjectMocks
    private MiembrosService miembrosService;

    @Test
    void crearmiembro_emailduplicadoexception(){
        MiembroRequestDTO dto = new MiembroRequestDTO();
        dto.setEmail("bratt10@gmail.com");
    
        when(miembrosRepository.existsByEmail("bratt10@gmail.com")).thenReturn(true);
    
        assertThrows(DuplicateResourceException.class, ()-> miembrosService.crearMiembro(dto, 1L));
    
    }
    @Test
    void crarmiembro_nombrevacioonull(){
        MiembroRequestDTO dto = new MiembroRequestDTO();
        dto.setEmail("bratt11@gmail.com");
        dto.setNombre("");

        when(miembrosRepository.existsByEmail("bratt11@gmail.com")).thenReturn(false);

        assertThrows(BusinessException.class, ()->miembrosService.crearMiembro(dto, 1L));
    }

    @Test
    void crearmiembro_edadmayorde18años(){
        MiembroRequestDTO dto = new MiembroRequestDTO();
        dto.setNombre("bratt");
        dto.setApellido("diaz");
        dto.setTelefono("3297497134");
        dto.setEmail("menor@email.com");
        dto.setFechaNacimiento(LocalDate.now().minusYears(15));
    
        when(miembrosRepository.existsByEmail("menor@email.com")).thenReturn(false);
        
        assertThrows(BusinessException.class, ()->miembrosService.crearMiembro(dto, 1L));
    }

    @Test
    void crearmiembro_telefonoNOcontengaletras_lanzaExcepcion(){
        MiembroRequestDTO dto = new MiembroRequestDTO();
        dto.setNombre("bratt");
        dto.setApellido("diaz");
        dto.setTelefono("3297d97i34");
        dto.setEmail("menor@email.com");
        dto.setFechaNacimiento(LocalDate.now().minusYears(20));

        when(miembrosRepository.existsByEmail("menor@email.com")).thenReturn(false);

        assertThrows(BusinessException.class, () -> miembrosService.crearMiembro(dto, 1L));
}

    @Test
    void crearmiembro_lafechadenacimientoNOpuedeserfutura(){
        MiembroRequestDTO dto = new MiembroRequestDTO();
        dto.setNombre("bratt");
        dto.setApellido("diaz");
        dto.setTelefono("3297d97i34");
        dto.setEmail("menor@email.com");
        dto.setFechaNacimiento(LocalDate.now().plusDays(2));

        when(miembrosRepository.existsByEmail("menor@email.com")).thenReturn(false);

        assertThrows(BusinessException.class, () -> miembrosService.crearMiembro(dto, 1L));

}

    @Test
    void crearmiembro_emailvacionarrojaException(){
        MiembroRequestDTO dto = new MiembroRequestDTO();
        dto.setNombre("Alfonso");
        dto.setApellido("Guzman");
        dto.setEmail("");
        dto.setTelefono("3208478192");
        dto.setFechaNacimiento(LocalDate.now().minusYears(20));

        when(miembrosRepository.existsByEmail("")).thenReturn(false);

        assertThrows(BusinessException.class, () -> miembrosService.crearMiembro(dto, 1L));
}
    
    @Test
    void crearmiembro_conentrenadorInactivo(){
        MiembroRequestDTO dto = new MiembroRequestDTO();
        EntrenadoresModel entrenadorinactivo = new EntrenadoresModel();
        dto.setNombre("Alfonso");
        dto.setApellido("Guzman");
        dto.setEmail("bratt10@gmail.com");
        dto.setTelefono("3208478192");
        dto.setFechaNacimiento(LocalDate.now().minusYears(20));
        entrenadorinactivo.setEstado(Estado.INACTIVO);
        
        when(miembrosRepository.existsByEmail("bratt10@gmail.com")).thenReturn(false); 
        when(entrenador.obtenerEntrenadorPorId(1L)).thenReturn(entrenadorinactivo);

        assertThrows(BusinessException.class,()-> miembrosService.crearMiembro(dto, 1L));
    }

}