package uniandes.edu.co.proyecto.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Ciudad;

public interface CiudadRepository extends MongoRepository<Ciudad, String> {

    @Query("{}")
    List<Ciudad> findAllCiudades();

    @Query("{ _id: ?0 }")
    Ciudad findCiudadById(String id);

    @Query("{ 'nombre': ?0 }")
    Ciudad findCiudadByNombre(String nombre);

    @Query("{ $insert: { _id: ?0, nombre: ?1 } }")
    void insertCiudad(String id, String nombre);

    @Query("{ _id: ?0 }")
    @Update("{ $set: { nombre: ?1 } }")
    void updateCiudad(String id, String nombre);

    @Query(value = "{ _id: ?0 }", delete = true)
    void deleteCiudadById(String id);
}