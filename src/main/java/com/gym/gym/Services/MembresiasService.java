package com.gym.gym.Services;

import org.springframework.stereotype.Service;

import com.gym.gym.DTO.Request.MembresiaRequestDTO;
import com.gym.gym.DTO.Response.MembresiaRepsonseDTO;
import com.gym.gym.Model.Estado;
import com.gym.gym.Model.MembresiasModel;
import com.gym.gym.Model.MiembrosModel;
import com.gym.gym.Respository.MembresiaRespository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MembresiasService {
    private final MembresiaRespository membresiasRepository;
    private final MiembrosService miembrosService;

    public MembresiasService(MembresiaRespository membresiasRepository, MiembrosService miembrosService) {
        this.membresiasRepository = membresiasRepository;
        this.miembrosService = miembrosService;
    }

    private MembresiasModel convertirDTOaEntidad(MembresiaRequestDTO membresia, MiembrosModel miembro) {
        MembresiasModel MembresiaDTO = new MembresiasModel();
        MembresiaDTO.setTipoMembresia(membresia.getTipoMembresia());
        MembresiaDTO.setFechaInicio(membresia.getFechaInicio());
        MembresiaDTO.setFechaFin(membresia.getFechaFin());
        MembresiaDTO.setPrecio(membresia.getPrecio());
        MembresiaDTO.setMiembro(miembro);
        return MembresiaDTO;
    }

    private MembresiaRepsonseDTO convertirEntidadaAResponseDTO(MembresiasModel membresia) {
        MembresiaRepsonseDTO dto = new MembresiaRepsonseDTO();
        dto.setTipoMembresia(membresia.getTipoMembresia().toString());
        dto.setFechaInicio(membresia.getFechaInicio());
        dto.setFechaFin(membresia.getFechaFin());
        dto.setEstado(membresia.getEstado());
        dto.setPrecio(membresia.getPrecio()); 
        dto.setNombreMiembro(membresia.getMiembro().getNombre()+ " "+membresia.getMiembro().getApellido());
        return dto;
    }
    

    public MembresiaRepsonseDTO crearMembresia(Long miembroId, MembresiaRequestDTO membresia) {
        Optional<MembresiasModel> membresiaExistente = membresiasRepository.findByMiembroId(miembroId);
        if (membresiaExistente.isPresent()) {
            throw new IllegalArgumentException("El miembro ya tiene una membresía registrada");
        }

        Optional<MiembrosModel> miembroOptional = miembrosService.obtenerPorId(miembroId);
        MiembrosModel miembro = miembroOptional.get();
        MembresiasModel membresiaEntidad = convertirDTOaEntidad(membresia, miembro);
        membresiaEntidad.setEstado(Estado.ACTIVO);
        MembresiasModel membresiaGuardada = membresiasRepository.save(membresiaEntidad); 
        return convertirEntidadaAResponseDTO(membresiaGuardada);
        
    }
    
    public Optional<MembresiasModel> obtenerMembresiaDelMiembro(Long miembroId) {
    Optional<MembresiasModel> membresia = membresiasRepository.findByMiembroId(miembroId);
    if (membresia.isEmpty()) {
        throw new IllegalArgumentException("Membresía no encontrada para el miembro con ID: " + miembroId);
    }
    if (membresia.get().getFechaFin().isBefore(LocalDate.now())) {
        membresia.get().setEstado(Estado.INACTIVO);
        membresiasRepository.save(membresia.get());
    }
    return membresia;
    }

    public List<MembresiasModel> obtenerMembresias() {
        List<MembresiasModel> membresias = membresiasRepository.findAll();
        return membresias;
    }

    public MembresiasModel actualizarMembresia(Long miembroID, MembresiasModel membresiaActualizada) {
        Optional<MembresiasModel> membresiaOptional = membresiasRepository.findByMiembroId(miembroID);
        if (membresiaOptional.isEmpty()) {
            throw new IllegalArgumentException("Membresía no encontrada");
        }
        MembresiasModel membresiaExistente = membresiaOptional.get();
        membresiaExistente.setTipoMembresia(membresiaActualizada.getTipoMembresia());
        membresiaExistente.setFechaInicio(membresiaActualizada.getFechaInicio());
        membresiaExistente.setFechaFin(membresiaActualizada.getFechaFin());
        membresiaExistente.setEstado(membresiaActualizada.getEstado());
        membresiaExistente.setPrecio(membresiaActualizada.getPrecio());
        MembresiasModel membresiactual = membresiasRepository.save(membresiaExistente);
        return membresiactual;
    }

    public void eliminarMembresia(Long miembroId) {
        Optional<MembresiasModel> membresiaOptional = membresiasRepository.findByMiembroId(miembroId);
        if (membresiaOptional.isEmpty()) {
            throw new IllegalArgumentException("Membresía no encontrada");
        }
        MembresiasModel membresia = membresiaOptional.get();
        membresiasRepository.delete(membresia);
    }

    public boolean actualizarEstadoMembresia(Long miembroId, Estado nuevoEstado) {
        Optional<MembresiasModel> membresiaOptional = membresiasRepository.findByMiembroId(miembroId);
        if (membresiaOptional.isEmpty()) {
            throw new IllegalArgumentException("Membresía no encontrada");
        }
        MembresiasModel membresia = membresiaOptional.get();
        membresia.setEstado(nuevoEstado);
        membresiasRepository.save(membresia);
        return true;
    }
    public MembresiasModel extenderMembresia(Long membresiaId, int dias) {
    MembresiasModel membresia = obtenerMembresiaPorId(membresiaId); 
    membresia.setFechaFin(membresia.getFechaFin().plusDays(dias));
    return membresiasRepository.save(membresia);
    }

    public MembresiasModel obtenerMembresiaPorId(Long id) {
    return membresiasRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Membresía no encontrada con ID: " + id));
    }
}