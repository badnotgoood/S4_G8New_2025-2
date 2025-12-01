package uniandes.edu.co.proyecto.repositorio;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Calificacion;

public interface CalificacionRepository extends MongoRepository<Calificacion, String> {

    @Query("{}")
    List<Calificacion> findAllCalificaciones();

    @Query("{ _id: ?0 }")
    Calificacion findCalificacionById(String id);

    @Query("{ 'idServicio': ?0 }")
    List<Calificacion> findCalificacionesByIdServicio(String idServicio);

    @Query("{ 'idConductor': ?0 }")
    List<Calificacion> findCalificacionesByIdConductor(String idConductor);

    @Query("{ $insert: { _id: ?0, idServicio: ?1, idCliente: ?2, idConductor: ?3, valor: ?4, comentario: ?5, fecha: ?6 } }")
    void insertCalificacion(String id, String idServicio, String idCliente, String idConductor,
                            int valor, String comentario, LocalDateTime fecha);

    @Query("{ _id: ?0 }")
    @Update("{ $set: { valor: ?1, comentario: ?2, fecha: ?3 } }")
    void updateCalificacion(String id, int valor, String comentario, LocalDateTime fecha);

    @Query(value = "{ _id: ?0 }", delete = true)
    void deleteCalificacionById(String id);
}