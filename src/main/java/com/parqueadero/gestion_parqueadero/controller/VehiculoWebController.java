package com.parqueadero.gestion_parqueadero.controller;

import java.time.LocalTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.parqueadero.gestion_parqueadero.entity.Vehiculo;
import com.parqueadero.gestion_parqueadero.repository.TipoVehiculoRepository;
import com.parqueadero.gestion_parqueadero.repository.VehiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class VehiculoWebController {

    private final VehiculoRepository vehiculoRepository;
    private final TipoVehiculoRepository tipoVehiculoRepository;

    @GetMapping("/vehiculos")
    public String listarVehiculos(Model model) {
        model.addAttribute("vehiculos", vehiculoRepository.findByHoraSalidaIsNull());
        return "vehiculos/lista";
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoVehiculo(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        model.addAttribute("tiposVehiculo", tipoVehiculoRepository.findAll());
        return "vehiculos/formulario";
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/guardar")
    public String guardarVehiculo(@ModelAttribute Vehiculo vehiculo) {
        if(vehiculoRepository.existsByPlaca(vehiculo.getPlaca())) {
            return "redirect:/vehiculos/nuevo?error=Placa ya existe";
        }
        vehiculo.setHoraEntrada(LocalTime.now());
        vehiculoRepository.save(vehiculo);
        return "redirect:/vehiculos";
    }

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ACOMODADOR')")
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarVehiculo(@PathVariable Long id, Model model) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("tiposVehiculo", tipoVehiculoRepository.findAll());
        return "vehiculos/formulario";
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @PostMapping("/editar/{id}")
    public String actualizarVehiculo(@PathVariable Long id, @ModelAttribute Vehiculo vehiculo) {
        vehiculo.setId(id);
        vehiculoRepository.save(vehiculo);
        return "redirect:/vehiculos";
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/registrar-salida/{id}")
    public String registrarSalida(@PathVariable Long id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        vehiculo.setHoraSalida(LocalTime.now());
        vehiculoRepository.save(vehiculo);
        return "redirect:/vehiculos";
    }

    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @GetMapping("/eliminar/{id}")
    public String eliminarVehiculo(@PathVariable Long id) {
        vehiculoRepository.deleteById(id);
        return "redirect:/vehiculos";
    }
}