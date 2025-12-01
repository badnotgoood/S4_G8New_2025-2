package uniandes.edu.co.proyecto.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.ToString;

@Document(collection = "vehiculos")
@ToString
public class Vehiculo {

    @Id
    private String placa;
    private String marca;
    private String modelo;
    private int capacidad;

    private TipoVehiculo tipoVehiculo;
    private NivelTransporte nivelTransporte;

    private String idConductor;

    private List<Disponibilidad> disponibilidades;

    public Vehiculo() { }

    public Vehiculo(String placa, String marca, String modelo, int capacidad,
                    TipoVehiculo tipoVehiculo, NivelTransporte nivelTransporte,
                    String idConductor, List<Disponibilidad> disponibilidades) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.tipoVehiculo = tipoVehiculo;
        this.nivelTransporte = nivelTransporte;
        this.idConductor = idConductor;
        this.disponibilidades = disponibilidades;
    }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public TipoVehiculo getTipoVehiculo() { return tipoVehiculo; }
    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) { this.tipoVehiculo = tipoVehiculo; }

    public NivelTransporte getNivelTransporte() { return nivelTransporte; }
    public void setNivelTransporte(NivelTransporte nivelTransporte) { this.nivelTransporte = nivelTransporte; }

    public String getIdConductor() { return idConductor; }
    public void setIdConductor(String idConductor) { this.idConductor = idConductor; }

    public List<Disponibilidad> getDisponibilidades() { return disponibilidades; }
    public void setDisponibilidades(List<Disponibilidad> disponibilidades) { this.disponibilidades = disponibilidades; }
}