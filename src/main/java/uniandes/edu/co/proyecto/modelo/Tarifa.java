package uniandes.edu.co.proyecto.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.ToString;

@Document(collection = "tarifas")
@ToString
public class Tarifa {

    @Id
    private String id;
    private TipoServicio tipoServicio;
    private NivelTransporte nivelTransporte;

    private double costoBase;
    private double costoPorKm;

    public Tarifa() { }

    public Tarifa(String id, TipoServicio tipoServicio, NivelTransporte nivelTransporte,
                  double costoBase, double costoPorKm) {
        this.id = id;
        this.tipoServicio = tipoServicio;
        this.nivelTransporte = nivelTransporte;
        this.costoBase = costoBase;
        this.costoPorKm = costoPorKm;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public TipoServicio getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(TipoServicio tipoServicio) { this.tipoServicio = tipoServicio; }

    public NivelTransporte getNivelTransporte() { return nivelTransporte; }
    public void setNivelTransporte(NivelTransporte nivelTransporte) { this.nivelTransporte = nivelTransporte; }

    public double getCostoBase() { return costoBase; }
    public void setCostoBase(double costoBase) { this.costoBase = costoBase; }

    public double getCostoPorKm() { return costoPorKm; }
    public void setCostoPorKm(double costoPorKm) { this.costoPorKm = costoPorKm; }
}