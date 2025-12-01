package uniandes.edu.co.proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Calificacion;
import uniandes.edu.co.proyecto.repositorio.CalificacionRepository;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @PostMapping("/new/save")
    public ResponseEntity<String> crearCalificacion(@RequestBody Calificacion calificacion) {
        try {
            calificacionRepository.save(calificacion);
            return new ResponseEntity<>("Calificación creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la calificación: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarCalificacion(@PathVariable("id") String id,
                                                         @RequestBody Calificacion calificacion) {
        try {
            calificacion.setId(id);
            calificacionRepository.save(calificacion);
            return new ResponseEntity<>("Calificación actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la calificación: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Calificacion>> obtenerTodasLasCalificaciones() {
        try {
            List<Calificacion> calificaciones = calificacionRepository.findAll();
            return ResponseEntity.ok(calificaciones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calificacion> obtenerCalificacionPorId(@PathVariable("id") String id) {
        try {
            Optional<Calificacion> calificacionOpt = calificacionRepository.findById(id);
            if (calificacionOpt.isPresent()) {
                return ResponseEntity.ok(calificacionOpt.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarCalificacion(@PathVariable("id") String id) {
        try {
            calificacionRepository.deleteById(id);
            return new ResponseEntity<>("Calificación eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la calificación: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}