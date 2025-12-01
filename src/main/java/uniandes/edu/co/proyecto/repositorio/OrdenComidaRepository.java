package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import uniandes.edu.co.proyecto.modelo.OrdenComida;

public interface OrdenComidaRepository extends MongoRepository<OrdenComida, String> {
    
}