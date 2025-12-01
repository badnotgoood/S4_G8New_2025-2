package uniandes.edu.co.proyecto.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Vehiculo;
import uniandes.edu.co.proyecto.modelo.TipoVehiculo;
import uniandes.edu.co.proyecto.modelo.NivelTransporte;
import uniandes.edu.co.proyecto.modelo.Disponibilidad;

public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {

    @Query("{}")
    List<Vehiculo> findAllVehiculos();

    @Query("{ _id: ?0 }")
    Vehiculo findVehiculoByPlaca(String placa);

    @Query("{ 'idConductor': ?0 }")
    List<Vehiculo> findVehiculosByIdConductor(String idConductor);

    @Query("{ 'tipoVehiculo': ?0, 'nivelTransporte': ?1 }")
    List<Vehiculo> findByTipoVehiculoAndNivelTransporte(TipoVehiculo tipoVehiculo, NivelTransporte nivelTransporte);

    @Query("{ $insert: { _id: ?0, marca: ?1, modelo: ?2, capacidad: ?3, tipoVehiculo: ?4, nivelTransporte: ?5, idConductor: ?6, disponibilidades: ?7 } }")
    void insertVehiculo(String placa, String marca, String modelo, int capacidad,
                        TipoVehiculo tipoVehiculo, NivelTransporte nivelTransporte,
                        String idConductor, List<Disponibilidad> disponibilidades);

    @Query("{ _id: ?0 }")
    @Update("{ $set: { marca: ?1, modelo: ?2, capacidad: ?3, tipoVehiculo: ?4, nivelTransporte: ?5, idConductor: ?6 } }")
    void updateVehiculo(String placa, String marca, String modelo, int capacidad,
                        TipoVehiculo tipoVehiculo, NivelTransporte nivelTransporte,
                        String idConductor);

    @Query("{ _id: ?0 }")
    @Update("{ $set: { disponibilidades: ?1 } }")
    void updateDisponibilidades(String placa, List<Disponibilidad> disponibilidades);

    @Query(value = "{ _id: ?0 }", delete = true)
    void deleteVehiculoByPlaca(String placa);
}