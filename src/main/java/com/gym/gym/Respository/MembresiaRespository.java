package com.gym.gym.Respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.gym.Model.MembresiasModel;

public interface MembresiaRespository extends JpaRepository<MembresiasModel, Long> {
    Optional<MembresiasModel> findByMiembroId(Long miembroId);
}
