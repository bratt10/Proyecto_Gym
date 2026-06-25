package com.gym.gym.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.gym.Model.PagosModel;

public interface PagosRepository extends JpaRepository<PagosModel, Long> {
    
}
