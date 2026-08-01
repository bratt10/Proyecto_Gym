package com.gym.gym.ServiciosTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gym.gym.DTO.Request.AdminRequestDTO;
import com.gym.gym.DTO.Request.LoginRequestDTO;
import com.gym.gym.DTO.Response.LoginResponse;
import com.gym.gym.Exception.BusinessException;
import com.gym.gym.Exception.DuplicateResourceException;
import com.gym.gym.Respository.AdminRespository;
import com.gym.gym.Services.AdminService;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
    
    @Mock
    AdminRespository adminrepo;

    @InjectMocks
    private AdminService adminService;

    @Test
    void crearadmin_correoduplicado(){
        AdminRequestDTO dto = new AdminRequestDTO();
        dto.setNombre("Bratt");
        dto.setCorreo("Gymbest@gmal.com");

        when(adminrepo.existsByCorreo("Gymbest@gmal.com")).thenReturn(true);

        assertThrows(DuplicateResourceException.class, () -> adminService.crearAdmin(dto));
    }

    @Test
    void crearadmin_contraseñaVACIANULL(){
        AdminRequestDTO dto = new AdminRequestDTO();
        dto.setNombre("Bratt");
        dto.setCorreo("Gymbest@gmal.com");
        dto.setApellido("Diaz");
        dto.setContraseña("");
        when(adminrepo.existsByCorreo("Gymbest@gmal.com")).thenReturn(false);

        assertThrows(BusinessException.class, () -> adminService.crearAdmin(dto));
    }
    @Test
    void crearadmin_correoycontraseñacorreoincorrecto(){
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setCorreo("Gymbest@gmal.com");
        dto.setContraseña("12345");

        when(adminrepo.findByCorreo("Gymbest@gmal.com")).thenReturn(Optional.empty());

        LoginResponse respuesta = adminService.loginadmin(dto);

        assertFalse(respuesta.getSuccess());
        assertEquals("Correo no encontrado", respuesta.getMessage());
        assertNull(respuesta.getToken());    
    }
}
