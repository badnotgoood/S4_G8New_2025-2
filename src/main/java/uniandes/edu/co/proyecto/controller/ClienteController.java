package uniandes.edu.co.proyecto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.modelo.Cliente;
import uniandes.edu.co.proyecto.repositorio.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // RF1 -- REGISTRAR UN USUARIO DE SERVICIOS
    @PostMapping("/new/save")
    public ResponseEntity<String> crearCliente(@RequestBody Cliente cliente) {
        try {
        if (cliente.getId() == null || cliente.getId().isBlank()
                || cliente.getNombre() == null || cliente.getNombre().isBlank()
                || cliente.getCorreo() == null || cliente.getCorreo().isBlank()
                || cliente.getCelular() == null || cliente.getCelular().isBlank()
                || cliente.getCedula() == null || cliente.getCedula().isBlank()) {

            return new ResponseEntity<>("Faltan campos obligatorios del cliente", HttpStatus.BAD_REQUEST);
        }
        clienteRepository.save(cliente);
        return new ResponseEntity<>("Cliente creado exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el cliente: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{id}/edit/save")
    public ResponseEntity<String> actualizarCliente(@PathVariable("id") String id,
                                                    @RequestBody Cliente cliente) {
        try {
            cliente.setId(id);
            clienteRepository.save(cliente);
            return new ResponseEntity<>("Cliente actualizado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el cliente: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        try {
            List<Cliente> clientes = clienteRepository.findAll();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable("id") String id) {
        try {
            Optional<Cliente> clienteOpt = clienteRepository.findById(id);
            if (clienteOpt.isPresent()) {
                return ResponseEntity.ok(clienteOpt.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarCliente(@PathVariable("id") String id) {
        try {
            clienteRepository.deleteById(id);
            return new ResponseEntity<>("Cliente eliminado exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el cliente: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}