package uniandes.edu.co.proyecto.controller;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Conductor;
import uniandes.edu.co.proyecto.modelo.Disponibilidad;
import uniandes.edu.co.proyecto.modelo.Vehiculo;
import uniandes.edu.co.proyecto.repositorio.ConductorRepository;
import uniandes.edu.co.proyecto.repositorio.VehiculoRepository;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ConductorRepository conductorRepository;

    // RF3 -- REGISTRAR UN VEHÍCULO PARA UN USUARIO CONDUCTOR
    @PostMapping("/new/save")
    public ResponseEntity<String> registrarVehiculo(@RequestBody Vehiculo vehiculo) {
        try {
            if (vehiculo.getPlaca() == null || vehiculo.getPlaca().isEmpty() ||
                vehiculo.getMarca() == null || vehiculo.getMarca().isEmpty() ||
                vehiculo.getModelo() == null || vehiculo.getModelo().isEmpty() ||
                vehiculo.getCapacidad() <= 0 ||
                vehiculo.getTipoVehiculo() == null ||
                vehiculo.getNivelTransporte() == null ||
                vehiculo.getIdConductor() == null || vehiculo.getIdConductor().isEmpty()) {

                return new ResponseEntity<>("Faltan campos obligatorios del vehiculo",
                        HttpStatus.BAD_REQUEST);
            }

            Conductor conductor = conductorRepository.findConductorById(vehiculo.getIdConductor());
            if (conductor == null) {
                return new ResponseEntity<>("No existe conductor con ese id",
                        HttpStatus.NOT_FOUND);
            }

            vehiculoRepository.save(vehiculo);
            return new ResponseEntity<>("Vehículo registrado para el conductor",
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar el vehículo: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Vehiculo>> obtenerTodosLosVehiculos() {
        try {
            List<Vehiculo> vehiculos = vehiculoRepository.findAll();
            return ResponseEntity.ok(vehiculos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{placa}")
    public ResponseEntity<Vehiculo> obtenerVehiculoPorPlaca(@PathVariable("placa") String placa) {
        try {
            Optional<Vehiculo> vehiculoOpt = vehiculoRepository.findById(placa);
            if (vehiculoOpt.isPresent()) {
                return ResponseEntity.ok(vehiculoOpt.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @DeleteMapping("/{placa}/delete")
    public ResponseEntity<String> eliminarVehiculo(@PathVariable("placa") String placa) {
        try {
            vehiculoRepository.deleteById(placa);
            return new ResponseEntity<>("Vehículo eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el vehículo: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // RF4 -- REGISTRAR LA DISPONIBILIDAD DE UN USUARIO CONDUCTOR Y SU VEHÍCULO PARA UN SERVICIO
    @PostMapping("/{placa}/disponibilidades/new/save")
    public ResponseEntity<String> registrarDisponibilidadVehiculo(
            @PathVariable("placa") String placa,
            @RequestBody Disponibilidad nuevaDisponibilidad) {
        try {
            if (nuevaDisponibilidad == null
                    || nuevaDisponibilidad.getDia() == null
                    || nuevaDisponibilidad.getHoraInicio() == null || nuevaDisponibilidad.getHoraInicio().isEmpty()
                    || nuevaDisponibilidad.getHoraFin() == null || nuevaDisponibilidad.getHoraFin().isEmpty()
                    || nuevaDisponibilidad.getTipoServicio() == null) {
                    
                return new ResponseEntity<>("Faltan campos obligatorios de la disponibilidad",
                        HttpStatus.BAD_REQUEST);
            }
        
            Vehiculo vehiculo = vehiculoRepository.findVehiculoByPlaca(placa);
            if (vehiculo == null) {
                return new ResponseEntity<>("No existe vehiculo con esa placa", HttpStatus.NOT_FOUND);
            }
        
            List<Disponibilidad> actuales = vehiculo.getDisponibilidades();
            if (actuales == null) {
                actuales = new java.util.ArrayList<>();
            }
        
            actuales.add(nuevaDisponibilidad);
        
            if (seSuperponen(actuales)) {
                return new ResponseEntity<>(
                        "La nueva disponibilidad se superpone con otra existente para el mismo dia y tipo de servicio",
                        HttpStatus.BAD_REQUEST);
            }
        
            vehiculo.setDisponibilidades(actuales);
            vehiculoRepository.save(vehiculo);
        
            return new ResponseEntity<>("Disponibilidad registrada correctamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar la disponibilidad: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // RF5 -- MODIFICAR LA DISPONIBILIDAD DE UN VEHÍCULO PARA SERVICIOS
    @PostMapping("/{placa}/disponibilidades/edit/save")
    public ResponseEntity<String> actualizarDisponibilidadVehiculo(@PathVariable("placa") String placa,
                                                                   @RequestBody Disponibilidad disponibilidadActualizada) {
        try {
            Vehiculo vehiculo = vehiculoRepository.findVehiculoByPlaca(placa);
            if (vehiculo == null) {
                return new ResponseEntity<>("No existe vehiculo con esa placa", HttpStatus.NOT_FOUND);
            }
        
            List<Disponibilidad> actuales = vehiculo.getDisponibilidades();
            if (actuales == null) {
                actuales = new java.util.ArrayList<>();
            }
        
            java.util.List<Disponibilidad> nuevas = new java.util.ArrayList<>();
            for (Disponibilidad d : actuales) {
                boolean mismoDia = d.getDia() == disponibilidadActualizada.getDia();
                boolean mismoTipo = d.getTipoServicio() == disponibilidadActualizada.getTipoServicio();
                if (!(mismoDia && mismoTipo)) {
                    nuevas.add(d);
                }
            }
        
            nuevas.add(disponibilidadActualizada);
        
            if (seSuperponen(nuevas)) {
                return new ResponseEntity<>("La disponibilidad modificada se superpone con otra existente", HttpStatus.BAD_REQUEST);
            }
        
            vehiculo.setDisponibilidades(nuevas);
            vehiculoRepository.save(vehiculo);
        
            return new ResponseEntity<>("Disponibilidad modificada correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al modificar la disponibilidad: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean seSuperponen(List<Disponibilidad> disponibilidades) {
        for (int i = 0; i < disponibilidades.size(); i++) {
            Disponibilidad d1 = disponibilidades.get(i);
            LocalTime inicio1 = LocalTime.parse(d1.getHoraInicio());
            LocalTime fin1 = LocalTime.parse(d1.getHoraFin());
            for (int j = i+1; j < disponibilidades.size(); j++) {
                Disponibilidad d2 = disponibilidades.get(j);
                if (d1.getDia() == d2.getDia()) {
                    LocalTime inicio2 = LocalTime.parse(d2.getHoraInicio());
                    LocalTime fin2 = LocalTime.parse(d2.getHoraFin());
                    boolean supuertas = inicio1.isBefore(fin2) && inicio2.isBefore(fin1);
                    if (supuertas) return true;
                }
            }
        }
        return false;
    }
}