package uniandes.edu.co.proyecto.repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import uniandes.edu.co.proyecto.modelo.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

    @Query("{}")
    List<Cliente> findAllClientes();

    @Query("{ _id: ?0 }")
    Cliente findClienteById(String id);

    @Query("{ 'correo': ?0 }")
    Cliente findClienteByCorreo(String correo);

    @Query("{ $insert: { _id: ?0, nombre: ?1, correo: ?2, celular: ?3, cedula: ?4, numeroTarjeta: ?5, nombreTitularTarjeta: ?6, fechaVencimientoTarjeta: ?7, cvcTarjeta: ?8 } }")
    void insertCliente(String id, String nombre, String correo, String celular,
                       String cedula, String numeroTarjeta, String nombreTitularTarjeta,
                       String fechaVencimientoTarjeta, String cvcTarjeta);

    @Query("{ _id: ?0 }")
    @Update("{ $set: { nombre: ?1, correo: ?2, celular: ?3, cedula: ?4, numeroTarjeta: ?5, nombreTitularTarjeta: ?6, fechaVencimientoTarjeta: ?7, cvcTarjeta: ?8 } }")
    void updateCliente(String id, String nombre, String correo, String celular,
                       String cedula, String numeroTarjeta, String nombreTitularTarjeta,
                       String fechaVencimientoTarjeta, String cvcTarjeta);

    @Query(value = "{ _id: ?0 }", delete = true)
    void deleteClienteById(String id);
}