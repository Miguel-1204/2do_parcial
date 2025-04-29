package com.parqueadero.gestion_parqueadero.repository;

import com.parqueadero.gestion_parqueadero.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    // Buscar vehículo por placa exacta
    Vehiculo findByPlaca(String placa);
    
    // Buscar vehículos que aún están en el parqueadero (sin hora de salida)
    List<Vehiculo> findByHoraSalidaIsNull();
    
    // Buscar vehículos que ya salieron del parqueadero
    List<Vehiculo> findByHoraSalidaIsNotNull();
    
    // Buscar vehículos por tipo
    List<Vehiculo> findByTipoVehiculoId(Long tipoId);
    
    // Buscar vehículos por ubicación exacta
    List<Vehiculo> findByUbicacion(String ubicacion);
    
    // Consulta personalizada para vehículos que entraron en un rango de horas
    @Query("SELECT v FROM Vehiculo v WHERE v.horaEntrada BETWEEN :inicio AND :fin")
    List<Vehiculo> findVehiculosPorRangoDeEntrada(LocalTime inicio, LocalTime fin);
    
    // Verificar si una placa ya existe
    boolean existsByPlaca(String placa);
    
    // Verificar si una ubicación está ocupada por un vehículo que aún no ha salido
    boolean existsByUbicacionAndHoraSalidaIsNull(String ubicacion);
}