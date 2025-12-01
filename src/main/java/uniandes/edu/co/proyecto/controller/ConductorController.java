package uniandes.edu.co.proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Conductor;
import uniandes.edu.co.proyecto.repositorio.ConductorRepository;

@RestController
@RequestMapping("/conductores")
public class ConductorController {

    @Autowired
    private ConductorRepository conductorRepository;

    // RF2 -- REGISTRAR UN USUARIO CONDUCTOR
    @PostMapping("/new/save")
    public ResponseEntity<String> crearConductor(@RequestBody Conductor conductor) {
        try {
        if (conductor.getId() == null || conductor.getId().isBlank()
                || conductor.getNombre() == null || conductor.getNombre().isBlank()
                || conductor.getCorreo() == null || conductor.getCorreo().isBlank()
                || conductor.getCelular() == null || conductor.getCelular().isBlank()) {
            return new ResponseEntity<>("Faltan campos obligatorios del cliente", HttpStatus.BAD_REQUEST);
        }
        conductorRepository.save(conductor);
            return new ResponseEntity<>("Conductor creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el conductor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarConductor(@PathVariable("id") String id,
                                                      @RequestBody Conductor conductor) {
        try {
            conductor.setId(id);
            conductorRepository.save(conductor);
            return new ResponseEntity<>("Conductor actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el conductor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Conductor>> obtenerTodosLosConductores() {
        try {
            List<Conductor> conductores = conductorRepository.findAll();
            return ResponseEntity.ok(conductores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conductor> obtenerConductorPorId(@PathVariable("id") String id) {
        try {
            Optional<Conductor> conductorOpt = conductorRepository.findById(id);
            if (conductorOpt.isPresent()) {
                return ResponseEntity.ok(conductorOpt.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarConductor(@PathVariable("id") String id) {
        try {
            conductorRepository.deleteById(id);
            return new ResponseEntity<>("Conductor eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el conductor: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}