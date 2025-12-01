package uniandes.edu.co.proyecto.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Conductor;

public interface ConductorRepository extends MongoRepository<Conductor, String> {

    @Query("{}")
    List<Conductor> findAllConductores();

    @Query("{ _id: ?0 }")
    Conductor findConductorById(String id);

    @Query("{ 'correo': ?0 }")
    Conductor findConductorByCorreo(String correo);

    @Query("{ $insert: { _id: ?0, nombre: ?1, correo: ?2, celular: ?3 } }")
    void insertConductor(String id, String nombre, String correo, String celular);

    @Query("{ _id: ?0 }")
    @Update("{ $set: { nombre: ?1, correo: ?2, celular: ?3 } }")
    void updateConductor(String id, String nombre, String correo, String celular);

    @Query(value = "{ _id: ?0 }", delete = true)
    void deleteConductorById(String id);
}