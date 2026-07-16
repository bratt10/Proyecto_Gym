package com.gym.gym.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gym.gym.DTO.Request.MiembroRequestDTO;
import com.gym.gym.DTO.Response.MiembroResponseDTO;
import com.gym.gym.Model.EntrenadoresModel;
import com.gym.gym.Model.Estado;
import com.gym.gym.Model.MiembrosModel;
import com.gym.gym.Respository.MiembrosRepository;

@Service
public class MiembrosService {
    private final MiembrosRepository miembrosRepository;
    private final EntrenadorService entrenadorService;
    
    public MiembrosService(MiembrosRepository miembrosRepository, EntrenadorService entrenadorService) {
        this.miembrosRepository = miembrosRepository;
        this.entrenadorService = entrenadorService;
    }
    //Convertir DTO a entidad
    private MiembrosModel convertirDTOaEntidad(MiembroRequestDTO dto, EntrenadoresModel entrenador){
        MiembrosModel miembro = new MiembrosModel();
        miembro.setNombre(dto.getNombre());
        miembro.setApellido(dto.getApellido());
        miembro.setEmail(dto.getEmail());
        miembro.setTelefono(dto.getTelefono());
        miembro.setFechaNacimiento(dto.getFechaNacimiento());
        miembro.setFechaRegistro(LocalDate.now());
        miembro.setEstado(Estado.ACTIVO);
        miembro.setEntrenador(entrenador);
        return miembro;
    }
    //Respuesta DTO 
    private MiembroResponseDTO convertirEntidadaAResponseDTO(MiembrosModel miembro){
        MiembroResponseDTO dto = new MiembroResponseDTO();
        dto.setNombre(miembro.getNombre());
        dto.setApellido(miembro.getApellido());
        dto.setEmail(miembro.getEmail());
        dto.setTelefono(miembro.getTelefono());
        dto.setNombreEntrenador(miembro.getEntrenador()!= null ? miembro.getEntrenador().getNombre(): "sin entrenador");
        dto.setEstado(miembro.getEstado());
        return dto;
    }

    public MiembroResponseDTO crearMiembro(MiembroRequestDTO miebre, Long entrenadorId){
       if(miembrosRepository.existsByEmail(miebre.getEmail())){
            throw new IllegalArgumentException("El email ya está registrado");
        }
        if(miebre.getNombre() == null || miebre.getNombre().isEmpty()){
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if(miebre.getApellido() == null || miebre.getApellido().isEmpty()){
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        if(miebre.getTelefono() == null || miebre.getTelefono().isEmpty()){ 
            throw new IllegalArgumentException("El teléfono es obligatorio");
        } else if(!miebre.getTelefono().matches("[0-9]+")){
            throw new IllegalArgumentException("El teléfono debe tener 10 dígitos");
        }
        if(miebre.getEmail() == null || miebre.getEmail().isEmpty()){
            throw new IllegalArgumentException("El email es obligatorio");
        } 
        if(miebre.getFechaNacimiento() == null){
            throw new IllegalArgumentException("La fecha de nacimiento es obligatoria");
        } else if(miebre.getFechaNacimiento().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura");
        }else if(miebre.getFechaNacimiento().isAfter(LocalDate.now().minusYears(18))){
                throw new IllegalArgumentException("El miembro debe ser mayor de edad");
        }
        EntrenadoresModel entrenador = entrenadorService.obtenerEntrenadorPorId(entrenadorId);
        if (entrenador == null || entrenador.getEstado() != Estado.ACTIVO) {
            throw new IllegalArgumentException("El entrenador no está activo o no existe");
        }
        if (entrenador.getMiembros().size() >= 10) {
            throw new IllegalArgumentException("El entrenador ya tiene 10 miembros asignados");
            
        }
        MiembrosModel miebre1 = convertirDTOaEntidad(miebre, entrenador);
        miebre1.setEstado(Estado.ACTIVO);
        MiembrosModel miembroGuardado = miembrosRepository.save(miebre1);
        return convertirEntidadaAResponseDTO(miembroGuardado);    
    }
    
    public List<MiembrosModel> obtenerMiembros() {
        List<MiembrosModel> miembros = miembrosRepository.findAll();
        if (miembros.isEmpty()) {
            throw new IllegalArgumentException("No hay miembros registrados");
        }
        return miembros;
    }

    public Optional<MiembrosModel> obtenerPorId(Long id) {
        Optional<MiembrosModel> miembro = miembrosRepository.findById(id);
        if (miembro.isEmpty()) {
            throw new IllegalArgumentException("Miembro no encontrado");
        }
        return miembro;
    }
   
    public MiembrosModel actualizarMiembro(Long id, MiembrosModel miembroinformacioAactualizar){
        Optional<MiembrosModel> miembroID = miembrosRepository.findById(id);
        if (miembroID.isEmpty()) {
            throw new IllegalArgumentException("No existe este miembro");
        }
        MiembrosModel miembroReal = miembroID.get();

        if (miembroinformacioAactualizar.getNombre()!= null) {
            miembroReal.setNombre(miembroinformacioAactualizar.getNombre());            
        }
        if (miembroinformacioAactualizar.getApellido() != null) {
            miembroReal.setApellido(miembroinformacioAactualizar.getApellido());
        }
        if (miembroinformacioAactualizar.getTelefono() != null) {
            miembroReal.setTelefono(miembroinformacioAactualizar.getTelefono());
        }
        if (miembroinformacioAactualizar.getEmail() != null) {
            miembroReal.setEmail(miembroinformacioAactualizar.getEmail());
        }
        if (miembroinformacioAactualizar.getFechaNacimiento() != null) {
            miembroReal.setFechaNacimiento(miembroinformacioAactualizar.getFechaNacimiento());
        }
        return miembrosRepository.save(miembroReal);
        }
        
        public boolean actualizarEstadoMiembro(Long id, Estado nuevoEstado) {
            MiembrosModel miembroReal = miembrosRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Miembro no encontrado"));
            miembroReal.setEstado(nuevoEstado);
            miembrosRepository.save(miembroReal);
            return true;
        }

        public void eliminarMiembro(Long id) {
            miembrosRepository.deleteById(id);
        }
}
