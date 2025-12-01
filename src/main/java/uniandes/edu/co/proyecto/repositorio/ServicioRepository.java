package uniandes.edu.co.proyecto.repositorio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Servicio;
import uniandes.edu.co.proyecto.modelo.TipoServicio;
import uniandes.edu.co.proyecto.modelo.NivelTransporte;
import uniandes.edu.co.proyecto.modelo.Ruta;

public interface ServicioRepository extends MongoRepository<Servicio, String> {

    @Query(value = "{}", fields = "{ 'ruta': 0 }")
    List<Servicio> findAllServicios();

    @Query("{ _id: ?0 }")
    Servicio findServicioById(String id);

    @Query("{ 'idCliente': ?0 }")
    List<Servicio> findServiciosByIdCliente(String idCliente);

    @Query("{ 'idConductor': ?0 }")
    List<Servicio> findServiciosByIdConductor(String idConductor);

    @Query("{ $insert: { _id: ?0, idCliente: ?1, idConductor: ?2, placaVehiculo: ?3, tipoServicio: ?4, nivelTransporte: ?5, ruta: ?6, fechaSolicitud: ?7, horaInicio: ?8, horaFin: ?9, costoTotal: ?10, distanciaKm: ?11, estado: ?12 } }")
    void insertServicio(String id, String idCliente, String idConductor, String placaVehiculo,
                        TipoServicio tipoServicio, NivelTransporte nivelTransporte,
                        Ruta ruta, LocalDateTime fechaSolicitud,
                        LocalDateTime horaInicio, LocalDateTime horaFin,
                        double costoTotal, double distanciaKm,
                        String estado);

    @Query("{ _id: ?0 }")
    @Update("{ $set: { horaInicio: ?1, horaFin: ?2, costoTotal: ?3, estado: ?4 } }")
    void updateHorariosYCosto(String id,
                              LocalDateTime horaInicio,
                              LocalDateTime horaFin,
                              double costoTotal,
                              String estado);

    @Query(value = "{ _id: ?0 }", delete = true)
    void deleteServicioById(String id);
}