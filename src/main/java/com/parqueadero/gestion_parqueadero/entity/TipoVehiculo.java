package com.parqueadero.gestion_parqueadero.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipos_vehiculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String nombre;

    @Column(length = 100)
    private String descripcion;
}