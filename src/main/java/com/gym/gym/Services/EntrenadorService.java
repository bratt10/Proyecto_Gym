package com.gym.gym.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gym.gym.DTO.Request.EntrenadorRequestDTO;
import com.gym.gym.DTO.Response.EntrenadorResponseDTO;
import com.gym.gym.Exception.BusinessException;
import com.gym.gym.Exception.DuplicateResourceException;
import com.gym.gym.Exception.ResourceNotFoundException;
import com.gym.gym.Model.EntrenadoresModel;
import com.gym.gym.Model.Estado;
import com.gym.gym.Respository.EntrenadoresRepository;

@Service
public class EntrenadorService {
    private final EntrenadoresRepository entrenadorRepository;
    
    public EntrenadorService(EntrenadoresRepository entrenadorRepository) {
        this.entrenadorRepository = entrenadorRepository;
    }

    private EntrenadoresModel convertirDTOaEntidad(EntrenadorRequestDTO dto){
        EntrenadoresModel entrenadorEntidad = new EntrenadoresModel();
        entrenadorEntidad.setNombre(dto.getNombre());
        entrenadorEntidad.setApellido(dto.getApellido());
        entrenadorEntidad.setEspecialidad(dto.getEspecialidad());
        entrenadorEntidad.setTelefono(dto.getTelefono());
        return entrenadorEntidad;
    }

    private EntrenadorResponseDTO convertirEntidadaAResponseDTO(EntrenadoresModel entrenador){
        EntrenadorResponseDTO dto = new EntrenadorResponseDTO();
        dto.setNombre(entrenador.getNombre());
        dto.setApellido(entrenador.getApellido());
        dto.setEspecialidad(entrenador.getEspecialidad());
        dto.setTelefono(entrenador.getTelefono());
        return dto;
    }

    public EntrenadorResponseDTO crearEntrenador(EntrenadorRequestDTO dto) {
        EntrenadoresModel entrenador = convertirDTOaEntidad(dto);
        if ( entrenadorRepository.existsByNombreAndApellido(entrenador.getNombre(), entrenador.getApellido())) {
            throw new DuplicateResourceException("El entrenador ya está registrado");
        }
        
        if(entrenador.getNombre() == null || entrenador.getNombre().isEmpty()){
            throw new BusinessException("El nombre es obligatorio");
        }
        if(entrenador.getApellido() == null || entrenador.getApellido().isEmpty()){
            throw new BusinessException("El apellido es obligatorio");
        }
        if(entrenador.getTelefono() == null || entrenador.getTelefono().isEmpty()){
            throw new BusinessException("El teléfono es obligatorio");
        } else if(!entrenador.getTelefono().matches("\\d[0-9]+")){
            throw new BusinessException("El teléfono debe tener 10 dígitos");
        }
        if(entrenador.getEspecialidad() == null || entrenador.getEspecialidad().isEmpty()){
            throw new BusinessException("La especialidad es obligatoria");
        }
        entrenador.setEstado(Estado.ACTIVO);
        EntrenadoresModel entrenadorGuardado = entrenadorRepository.save(entrenador);
        return convertirEntidadaAResponseDTO(entrenadorGuardado);
    } 

    public List<EntrenadoresModel> obtenerTodosEntrenadores() {
        return entrenadorRepository.findAll();
    }

    public EntrenadoresModel obtenerEntrenadorPorId(Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException("El ID del entrenador no es válido");
        }
        return entrenadorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entrenador no encontrado"));
    }
    
    public void eliminarEntrenador(Long id) {
        if (!entrenadorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Entrenador no encontrado");
        }
        entrenadorRepository.deleteById(id);
    }

    public boolean cambiarEstadoEntrenador(Long id, Estado estado) {
        EntrenadoresModel entrenador = obtenerEntrenadorPorId(id);
        entrenador.setEstado(estado);
        entrenadorRepository.save(entrenador);
        return true;
    }

    public EntrenadoresModel actualizarEntrenador(Long id, EntrenadoresModel entrenador){
        EntrenadoresModel entrenadorExistente = obtenerEntrenadorPorId(id);
        if(entrenador.getNombre() != null && !entrenador.getNombre().isEmpty()){
            entrenadorExistente.setNombre(entrenador.getNombre());
        }
        if(entrenador.getApellido() != null && !entrenador.getApellido().isEmpty()){
            entrenadorExistente.setApellido(entrenador.getApellido());
        }
        if(entrenador.getTelefono() != null && !entrenador.getTelefono().isEmpty()){
            entrenadorExistente.setTelefono(entrenador.getTelefono());
        }
        if(entrenador.getEspecialidad() != null && !entrenador.getEspecialidad().isEmpty()){
            entrenadorExistente.setEspecialidad(entrenador.getEspecialidad());
        }
        return entrenadorRepository.save(entrenadorExistente);
    }
    
    public boolean consultarEstadoActivoEntrenador(Long id) {
        EntrenadoresModel entrenador = obtenerEntrenadorPorId(id);
        if (entrenador == null) {
            throw new BusinessException("Entrenador no encontrado");
        }
        return entrenador.getEstado() == Estado.ACTIVO;
    }

}

