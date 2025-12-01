package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import uniandes.edu.co.proyecto.modelo.PuntoGeografico;

public interface PuntoGeograficoRepository extends MongoRepository<PuntoGeografico, String> {
   
}