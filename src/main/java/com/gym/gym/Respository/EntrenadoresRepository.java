package com.gym.gym.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.gym.Model.EntrenadoresModel;

public interface EntrenadoresRepository extends JpaRepository<EntrenadoresModel, Long> {
    boolean existsByNombreAndApellido(String nombre, String apellido);
}
