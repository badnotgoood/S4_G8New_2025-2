package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.ToString;

@Document(collection = "conductores")
@ToString
public class Conductor {

    @Id
    private String id;
    private String nombre;
    private String correo;
    private String celular;

    public Conductor() { }

    public Conductor(String id, String nombre, String correo, String celular) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.celular = celular;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }
}