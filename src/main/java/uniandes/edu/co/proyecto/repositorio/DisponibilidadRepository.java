package uniandes.edu.co.proyecto.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import uniandes.edu.co.proyecto.modelo.Disponibilidad;

public interface DisponibilidadRepository extends MongoRepository<Disponibilidad, String> {
    
}
