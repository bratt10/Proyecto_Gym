package com.gym.gym.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gym.gym.Model.PagosModel;

public interface PagosRepository extends JpaRepository<PagosModel, Long> {

    List<PagosModel> findByMembresiaId(Long membresiaId);

}
