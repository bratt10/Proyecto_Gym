package com.gym.gym.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gym.gym.Config.SeguridadContrasenaEncriptada;
import com.gym.gym.DTO.Request.AdminRequestDTO;
import com.gym.gym.DTO.Request.LoginRequestDTO;
import com.gym.gym.DTO.Response.AdminResponseDTO;
import com.gym.gym.DTO.Response.LoginResponse;
import com.gym.gym.Model.AdminModel;
import com.gym.gym.Respository.AdminRespository;

@Service
public class AdminService {
    
    private final AdminRespository adminRespository;
    private final SeguridadContrasenaEncriptada seguridadContraseñaEncriptada;
    public AdminService(AdminRespository adminRespository, SeguridadContrasenaEncriptada seguridadContraseñaEncriptada) {
        this.adminRespository = adminRespository;
        this.seguridadContraseñaEncriptada = seguridadContraseñaEncriptada;
    }
    

    private AdminModel convertirDTOaEntidad(AdminRequestDTO dto){
        AdminModel adminEntidad = new AdminModel();
        adminEntidad.setNombre(dto.getNombre());
        adminEntidad.setApellido(dto.getApellido());
        adminEntidad.setNombreDeGym(dto.getNombredegym());
        adminEntidad.setCorreo(dto.getCorreo());
        adminEntidad.setContraseña(dto.getContraseña());
        return adminEntidad;
    }

    private AdminResponseDTO convertirEntidadAReponseDTO(AdminModel admin){
        AdminResponseDTO dto = new AdminResponseDTO();
        dto.setNombre(admin.getNombre());
        dto.setApellido(admin.getApellido());
        dto.setNombredegym(admin.getNombreDeGym());
        dto.setCorreo(admin.getCorreo());
        return dto;
        
    }

    private LoginResponse crearLoginResponse(boolean success, String mensaje) {
        LoginResponse response = new LoginResponse();
        response.setSuccess(success);
        response.setMessage(mensaje);
        return response;
    }


    public AdminResponseDTO crearAdmin(AdminRequestDTO admin){
       if (admin.getNombre()==null || admin.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if(admin.getCorreo() == null || admin.getCorreo().isEmpty()){
            throw new IllegalArgumentException("El correo electrónico es obligatorio");
        }
        if(adminRespository.existsByCorreo(admin.getCorreo())){
            throw new IllegalArgumentException("El correo electrónico ya está registrado");
        }
        if(admin.getNombredegym() == null || admin.getNombredegym().isEmpty()){
            throw new IllegalArgumentException("El nombre del gimnasio es obligatorio");
        }
        if(admin.getContraseña() == null || admin.getContraseña().isEmpty()){
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        String contraseñaEncriptada = seguridadContraseñaEncriptada.passwordEncoder().encode(admin.getContraseña());
        admin.setContraseña(contraseñaEncriptada);

        AdminModel adminDTO = convertirDTOaEntidad(admin);
        AdminModel adminguardado = adminRespository.save(adminDTO);
        return convertirEntidadAReponseDTO(adminguardado);
    }

    public LoginResponse  loginadmin (LoginRequestDTO dto){
        if (dto.getCorreo() == null || dto.getCorreo().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede ser null");
        }
        if (dto.getContraseña() == null || dto.getContraseña().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede ser null");
        }

        Optional<AdminModel> admincorreo = adminRespository.findByCorreo(dto.getCorreo());
        if (admincorreo.isEmpty()) {
            return crearLoginResponse(false, "Correo no encontrado");
        }

        AdminModel adminexistente = admincorreo.get();

        boolean validan = seguridadContraseñaEncriptada.passwordEncoder().matches(dto.getContraseña(), adminexistente.getContraseña());

        if (!validan) {
            return crearLoginResponse(validan, "Correo o contraseña INCORRECTA");
        }
        return crearLoginResponse(validan, "Ingreso exitoso. Bienvenido usuario "+ adminexistente.getNombre()+" "+adminexistente.getApellido());
    }
   
    
 
}
