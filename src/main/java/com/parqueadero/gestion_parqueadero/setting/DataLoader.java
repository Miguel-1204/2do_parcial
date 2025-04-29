package com.parqueadero.gestion_parqueadero.setting;

import com.parqueadero.gestion_parqueadero.entity.TipoVehiculo;
import com.parqueadero.gestion_parqueadero.entity.UserEntity;
import com.parqueadero.gestion_parqueadero.entity.Vehiculo;
import com.parqueadero.gestion_parqueadero.repository.TipoVehiculoRepository;
import com.parqueadero.gestion_parqueadero.repository.UserRepository;
import com.parqueadero.gestion_parqueadero.repository.VehiculoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalTime;
import java.util.Arrays;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(
            UserRepository userRepository,
            TipoVehiculoRepository tipoVehiculoRepository,
            VehiculoRepository vehiculoRepository,
            PasswordEncoder passwordEncoder) {
        
        return args -> {
            // Solo cargar datos si las tablas están vacías
            if (userRepository.count() == 0 && tipoVehiculoRepository.count() == 0) {
                // Cargar usuarios iniciales
            	UserEntity admin = new UserEntity();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("1234"));
                admin.setRole("ADMINISTRADOR");
                admin.setEnabled(true);

                UserEntity acomodador = new UserEntity();
                acomodador.setUsername("acomodador");
                acomodador.setPassword(passwordEncoder.encode("1234"));
                acomodador.setRole("ACOMODADOR");
                acomodador.setEnabled(true);

                UserEntity cliente = new UserEntity();
                cliente.setUsername("cliente");
                cliente.setPassword(passwordEncoder.encode("1234"));
                cliente.setRole("CLIENTE");
                cliente.setEnabled(true);

                userRepository.saveAll(Arrays.asList(admin, acomodador, cliente));

                // Cargar tipos de vehículo
                TipoVehiculo automovil = new TipoVehiculo();
                automovil.setNombre("Automóvil");
                automovil.setDescripcion("Vehículo de hasta 5 pasajeros");

                TipoVehiculo motocicleta = new TipoVehiculo();
                motocicleta.setNombre("Motocicleta");
                motocicleta.setDescripcion("Vehículo de 2 ruedas");

                TipoVehiculo camioneta = new TipoVehiculo();
                camioneta.setNombre("Camioneta");
                camioneta.setDescripcion("Vehículo de carga liviana");

                tipoVehiculoRepository.saveAll(Arrays.asList(automovil, motocicleta, camioneta));

                // Cargar algunos vehículos de ejemplo
                Vehiculo vehiculo1 = new Vehiculo();
                vehiculo1.setPlaca("ABC123");
                vehiculo1.setHoraEntrada(LocalTime.now().minusHours(2));
                vehiculo1.setUbicacion("A-12");
                vehiculo1.setTipoVehiculo(automovil);

                Vehiculo vehiculo2 = new Vehiculo();
                vehiculo2.setPlaca("XYZ789");
                vehiculo2.setHoraEntrada(LocalTime.now().minusMinutes(30));
                vehiculo2.setUbicacion("M-05");
                vehiculo2.setTipoVehiculo(motocicleta);

                vehiculoRepository.saveAll(Arrays.asList(vehiculo1, vehiculo2));

                System.out.println("Datos iniciales cargados exitosamente");
            }
        };
    }
}