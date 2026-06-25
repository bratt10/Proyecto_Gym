package com.gym.gym.Model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor 
@Table(name="Membresias")
public class MembresiasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "miembro_id", nullable = false)
    @JsonIgnore
    private MiembrosModel miembro;

    public enum TipoMembresia {
        MENSUAL, TRIMESTRAL, ANUAL
    }
    
    @Column(name = "tipo_membresia", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMembresia tipoMembresia = TipoMembresia.MENSUAL;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private Estado estado = Estado.ACTIVO;

    @Column(name = "precio", nullable = false)
    private Double precio;
    

}
