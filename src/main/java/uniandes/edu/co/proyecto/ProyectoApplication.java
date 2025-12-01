package uniandes.edu.co.proyecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uniandes.edu.co.proyecto.modelo.Vehiculo;
import uniandes.edu.co.proyecto.repositorio.VehiculoRepository;

import org.springframework.boot.CommandLineRunner;

import java.util.List;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProyectoApplication.class, args);
    }

    public void run(String... arg) {
        
        List<Vehiculo> vehiculos = vehiculoRepository.findAll();

        for (Vehiculo v : vehiculos) {
            System.out.print(v);
        }
    }
}
