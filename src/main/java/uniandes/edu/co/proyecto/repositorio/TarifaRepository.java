package uniandes.edu.co.proyecto.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Tarifa;
import uniandes.edu.co.proyecto.modelo.TipoServicio;
import uniandes.edu.co.proyecto.modelo.NivelTransporte;

public interface TarifaRepository extends MongoRepository<Tarifa, String> {

    @Query("{}")
    List<Tarifa> findAllTarifas();

    @Query("{ _id: ?0 }")
    Tarifa findTarifaById(String id);

    @Query("{ 'tipoServicio': ?0, 'nivelTransporte': ?1 }")
    Tarifa findByTipoServicioYNivel(TipoServicio tipoServicio, NivelTransporte nivelTransporte);

    @Query("{ $insert: { _id: ?0, tipoServicio: ?1, nivelTransporte: ?2, costoBase: ?3, costoPorKm: ?4 } }")
    void insertTarifa(String id, TipoServicio tipoServicio, NivelTransporte nivelTransporte,
                      double costoBase, double costoPorKm);

    @Query("{ _id: ?0 }")
    @Update("{ $set: { tipoServicio: ?1, nivelTransporte: ?2, costoBase: ?3, costoPorKm: ?4 } }")
    void updateTarifa(String id, TipoServicio tipoServicio, NivelTransporte nivelTransporte,
                      double costoBase, double costoPorKm);

    @Query(value = "{ _id: ?0 }", delete = true)
    void deleteTarifaById(String id);
}