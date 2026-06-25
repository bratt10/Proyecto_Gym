package com.gym.gym.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.gym.Model.MiembrosModel;

public interface MiembrosRepository extends JpaRepository<MiembrosModel, Long> {
    boolean existsByEmail(String email);
}
