package uniandes.edu.co.proyecto.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Cliente;
import uniandes.edu.co.proyecto.modelo.Disponibilidad;
import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.modelo.Tarifa;
import uniandes.edu.co.proyecto.modelo.Vehiculo;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;
import uniandes.edu.co.proyecto.repositorio.ServicioRepository;
import uniandes.edu.co.proyecto.repositorio.TarifaRepository;
import uniandes.edu.co.proyecto.repositorio.VehiculoRepository;

@RestController
@RequestMapping("/servicios")
public class ServiciosController {

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private TarifaRepository tarifaRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/new/save")
    public ResponseEntity<String> crearServicio(@RequestBody Servicio servicio) {
        try {
            if (servicio.getId() == null || servicio.getId().isEmpty()) {
                servicio.setId(UUID.randomUUID().toString());
            }
            if (servicio.getEstado() == null || servicio.getEstado().isEmpty()) {
                servicio.setEstado("EN_CURSO");
            }
        
            servicioRepository.save(servicio);
            return new ResponseEntity<>("Servicio creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el servicio: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarHorariosYCostoYEstado(
            @PathVariable("id") String id,
            @RequestBody Servicio servicioDatos) {
        try {
            servicioRepository.updateHorariosYCosto(
                    id,
                    servicioDatos.getHoraInicio(),
                    servicioDatos.getHoraFin(),
                    servicioDatos.getCostoTotal(),
                    servicioDatos.getEstado()
            );
            return new ResponseEntity<>("Servicio actualizado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el servicio: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Servicio>> obtenerTodosLosServicios() {
        try {
            List<Servicio> servicios = servicioRepository.findAllServicios();
            return ResponseEntity.ok(servicios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtenerServicioPorId(@PathVariable("id") String id) {
        try {
            Servicio servicio = servicioRepository.findServicioById(id);
            if (servicio != null) {
                return ResponseEntity.ok(servicio);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarServicio(@PathVariable("id") String id) {
        try {
            servicioRepository.deleteServicioById(id);
            return new ResponseEntity<>("Servicio eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el servicio: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // RF6 -- SOLICITAR UN SERVICIO POR PARTE DE UN USUARIO DE SERVICIOS
    @PostMapping("/solicitar/new/save")
    public ResponseEntity<String> solicitarServicio(@RequestBody Servicio solicitud) {
        try {
            Cliente cliente = clienteRepository.findClienteById(solicitud.getIdCliente());
            if (cliente == null) {
                return new ResponseEntity<>("No existe cliente con ese id", HttpStatus.NOT_FOUND);
            }
        
            List<Vehiculo> vehiculos = vehiculoRepository.findAllVehiculos();
            Vehiculo vehiculoElegido = null;

            for (Vehiculo v : vehiculos) {
                if (vehiculoElegido == null && v.getNivelTransporte() == solicitud.getNivelTransporte()) {
                List<Disponibilidad> disp = v.getDisponibilidades();
                if (disp != null) {
                    for (Disponibilidad d : disp) {
                        if (vehiculoElegido == null && d.getTipoServicio() == solicitud.getTipoServicio()) {
                            vehiculoElegido = v;
                        }
                    }
                }
            }
            }

            if (vehiculoElegido == null) {
                return new ResponseEntity<>("No se encontró un conductor disponible para ese tipo y nivel de servicio", HttpStatus.BAD_REQUEST);
            }

            Tarifa tarifa = tarifaRepository.findByTipoServicioYNivel(solicitud.getTipoServicio(), solicitud.getNivelTransporte());
            if (tarifa == null) {
                return new ResponseEntity<>("No se encontró tarifa para ese tipo y nivel de servicio", HttpStatus.BAD_REQUEST);
            }

            double costo = tarifa.getCostoBase() + solicitud.getDistanciaKm() * tarifa.getCostoPorKm();

            Servicio servicio = new Servicio();
            servicio.setId(UUID.randomUUID().toString());
            servicio.setIdCliente(solicitud.getIdCliente());
            servicio.setIdConductor(vehiculoElegido.getIdConductor());
            servicio.setPlacaVehiculo(vehiculoElegido.getPlaca());
            servicio.setTipoServicio(solicitud.getTipoServicio());
            servicio.setNivelTransporte(solicitud.getNivelTransporte());
            servicio.setRuta(solicitud.getRuta());
            servicio.setFechaSolicitud(java.time.LocalDateTime.now());
            servicio.setHoraInicio(null);
            servicio.setHoraFin(null);
            servicio.setDistanciaKm(solicitud.getDistanciaKm());
            servicio.setCostoTotal(costo);
            servicio.setEstado("EN_CURSO");

            servicioRepository.save(servicio);

            return new ResponseEntity<>("Servicio solicitado correctamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al solicitar el servicio: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // RF7 -- REGISTRAR UN VIAJE PARA UN USUARIO DE SERVICIOS Y UN USUARIO CONDUCTOR
    @PostMapping("/{id}/finalizar/save")
    public ResponseEntity<String> finalizarServicio(@PathVariable("id") String id,
                                               @RequestBody Servicio datosFinales) {
    try {
        Servicio servicio = servicioRepository.findServicioById(id);
        if (servicio == null) {
            return new ResponseEntity<>("No existe servicio con ese id", HttpStatus.NOT_FOUND);
        }

        servicio.setHoraInicio(datosFinales.getHoraInicio());
        servicio.setHoraFin(datosFinales.getHoraFin());

        if (datosFinales.getDistanciaKm() > 0) {
            servicio.setDistanciaKm(datosFinales.getDistanciaKm());
        }
        if (datosFinales.getCostoTotal() > 0) {
            servicio.setCostoTotal(datosFinales.getCostoTotal());
        }

        servicio.setEstado("FINALIZADO");

        servicioRepository.save(servicio);

        return new ResponseEntity<>("Servicio finalizado correctamente", HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>("Error al finalizar el servicio: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


}