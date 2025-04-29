package com.parqueadero.gestion_parqueadero.repository;

import com.parqueadero.gestion_parqueadero.entity.TipoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface TipoVehiculoRepository extends JpaRepository<TipoVehiculo, Long> {
    // Método para buscar por nombre exacto
    TipoVehiculo findByNombre(String nombre);
    
    // Método para verificar si existe un tipo con cierto nombre
    boolean existsByNombre(String nombre);
    
    // Método para buscar tipos que contengan cierta palabra en la descripción
    List<TipoVehiculo> findByDescripcionContaining(String palabraClave);
}