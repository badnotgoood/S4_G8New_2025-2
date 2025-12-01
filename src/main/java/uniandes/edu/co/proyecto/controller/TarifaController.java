package uniandes.edu.co.proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Tarifa;
import uniandes.edu.co.proyecto.repositorio.TarifaRepository;

@RestController
@RequestMapping("/tarifas")
public class TarifaController {

    @Autowired
    private TarifaRepository tarifaRepository;

    @PostMapping("/new/save")
    public ResponseEntity<String> crearTarifa(@RequestBody Tarifa tarifa) {
        try {
            tarifaRepository.save(tarifa);
            return new ResponseEntity<>("Tarifa creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la tarifa: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarTarifa(@PathVariable("id") String id,
                                                   @RequestBody Tarifa tarifa) {
        try {
            tarifa.setId(id);
            tarifaRepository.save(tarifa);
            return new ResponseEntity<>("Tarifa actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar la tarifa: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Tarifa>> obtenerTodasLasTarifas() {
        try {
            List<Tarifa> tarifas = tarifaRepository.findAll();
            return ResponseEntity.ok(tarifas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarifa> obtenerTarifaPorId(@PathVariable("id") String id) {
        try {
            Optional<Tarifa> tarifaOpt = tarifaRepository.findById(id);
            if (tarifaOpt.isPresent()) {
                return ResponseEntity.ok(tarifaOpt.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarTarifa(@PathVariable("id") String id) {
        try {
            tarifaRepository.deleteById(id);
            return new ResponseEntity<>("Tarifa eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar la tarifa: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}