package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.ToString;

@Document(collection = "calificaciones")
@ToString
public class Calificacion {

    @Id
    private String id;

    private String idServicio;
    private String idCliente;
    private String idConductor;

    private int valor;
    private String comentario;
    private LocalDateTime fecha;

    public Calificacion() { }

    public Calificacion(String id, String idServicio, String idCliente, String idConductor,
                        int valor, String comentario, LocalDateTime fecha) {
        this.id = id;
        this.idServicio = idServicio;
        this.idCliente = idCliente;
        this.idConductor = idConductor;
        this.valor = valor;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdServicio() { return idServicio; }
    public void setIdServicio(String idServicio) { this.idServicio = idServicio; }

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public String getIdConductor() { return idConductor; }
    public void setIdConductor(String idConductor) { this.idConductor = idConductor; }

    public int getValor() { return valor; }
    public void setValor(int valor) { this.valor = valor; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}