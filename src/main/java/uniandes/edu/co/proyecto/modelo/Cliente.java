package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.ToString;

@Document(collection = "clientes")
@ToString
public class Cliente {

    @Id
    private String id;
    private String nombre;
    private String correo;
    private String celular;
    private String cedula;

    private String numeroTarjeta;
    private String nombreTitularTarjeta;
    private String fechaVencimientoTarjeta;
    private String cvcTarjeta;

    public Cliente() { }

    public Cliente(String id, String nombre, String correo, String celular,
                   String cedula, String numeroTarjeta, String nombreTitularTarjeta,
                   String fechaVencimientoTarjeta, String cvcTarjeta) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.celular = celular;
        this.cedula = cedula;
        this.numeroTarjeta = numeroTarjeta;
        this.nombreTitularTarjeta = nombreTitularTarjeta;
        this.fechaVencimientoTarjeta = fechaVencimientoTarjeta;
        this.cvcTarjeta = cvcTarjeta;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNumeroTarjeta() { return numeroTarjeta; }
    public void setNumeroTarjeta(String numeroTarjeta) { this.numeroTarjeta = numeroTarjeta; }

    public String getNombreTitularTarjeta() { return nombreTitularTarjeta; }
    public void setNombreTitularTarjeta(String nombreTitularTarjeta) { this.nombreTitularTarjeta = nombreTitularTarjeta; }

    public String getFechaVencimientoTarjeta() { return fechaVencimientoTarjeta; }
    public void setFechaVencimientoTarjeta(String fechaVencimientoTarjeta) { this.fechaVencimientoTarjeta = fechaVencimientoTarjeta; }

    public String getCvcTarjeta() { return cvcTarjeta; }
    public void setCvcTarjeta(String cvcTarjeta) { this.cvcTarjeta = cvcTarjeta; }
}