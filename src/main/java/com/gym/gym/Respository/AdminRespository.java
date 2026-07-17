package com.gym.gym.Respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.gym.Model.AdminModel;

public interface AdminRespository extends JpaRepository<AdminModel, Long> {
    boolean existsByCorreo(String correo);
    Optional<AdminModel> findByCorreo(String correo);
}
