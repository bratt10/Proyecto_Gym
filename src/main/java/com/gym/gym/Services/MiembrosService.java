package com.gym.gym.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

    public MiembrosModel crearMiembro(MiembrosModel miebre, Long entrenadorId){
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
        }
        EntrenadoresModel entrenador = entrenadorService.obtenerEntrandorPorId(entrenadorId);
        if (entrenador == null || entrenador.getEstado() != Estado.ACTIVO) {
            throw new IllegalArgumentException("El entrenador no está activo o no existe");
        }
        miebre.setEntrenador(entrenador);
        miebre.setEstado(Estado.ACTIVO);
        miebre.setFechaRegistro(LocalDate.now());
        return miembrosRepository.save(miebre);
    
    
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
    //public MiembrosModel obtenerPorid2(Long id) {
    //  return miembrosRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Miembro no encontrado"));
    public MiembrosModel actualizarMiembroMiembros(Long id, MiembrosModel miembroinformacioAactualizar){
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
