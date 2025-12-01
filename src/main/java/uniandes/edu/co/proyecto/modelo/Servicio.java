package uniandes.edu.co.proyecto.modelo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.ToString;

@Document(collection = "servicios")
@ToString
public class Servicio {

    @Id
    private String id;

    private String idCliente;
    private String idConductor;
    private String placaVehiculo;

    private TipoServicio tipoServicio;
    private NivelTransporte nivelTransporte;

    private Ruta ruta;

    private LocalDateTime fechaSolicitud;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;

    private double costoTotal;
    private double distanciaKm;

    private String estado; 

    public Servicio() { }

    public Servicio(String id, String idCliente, String idConductor, String placaVehiculo,
                    TipoServicio tipoServicio, NivelTransporte nivelTransporte,
                    Ruta ruta,
                    LocalDateTime fechaSolicitud, LocalDateTime horaInicio,
                    LocalDateTime horaFin, double costoTotal, double distanciaKm,
                    String estado) {
        this.id = id;
        this.idCliente = idCliente;
        this.idConductor = idConductor;
        this.placaVehiculo = placaVehiculo;
        this.tipoServicio = tipoServicio;
        this.nivelTransporte = nivelTransporte;
        this.ruta = ruta;
        this.fechaSolicitud = fechaSolicitud;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.costoTotal = costoTotal;
        this.distanciaKm = distanciaKm;
        this.estado = estado;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getIdCliente() { return idCliente; }
    public void setIdCliente(String idCliente) { this.idCliente = idCliente; }

    public String getIdConductor() { return idConductor; }
    public void setIdConductor(String idConductor) { this.idConductor = idConductor; }

    public String getPlacaVehiculo() { return placaVehiculo; }
    public void setPlacaVehiculo(String placaVehiculo) { this.placaVehiculo = placaVehiculo; }

    public TipoServicio getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(TipoServicio tipoServicio) { this.tipoServicio = tipoServicio; }

    public NivelTransporte getNivelTransporte() { return nivelTransporte; }
    public void setNivelTransporte(NivelTransporte nivelTransporte) { this.nivelTransporte = nivelTransporte; }

    public Ruta getRuta() { return ruta; }
    public void setRuta(Ruta ruta) { this.ruta = ruta; }

    public LocalDateTime getFechaSolicitud() { return fechaSolicitud; }
    public void setFechaSolicitud(LocalDateTime fechaSolicitud) { this.fechaSolicitud = fechaSolicitud; }

    public LocalDateTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalDateTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalDateTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalDateTime horaFin) { this.horaFin = horaFin; }

    public double getCostoTotal() { return costoTotal; }
    public void setCostoTotal(double costoTotal) { this.costoTotal = costoTotal; }

    public double getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(double distanciaKm) { this.distanciaKm = distanciaKm; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}