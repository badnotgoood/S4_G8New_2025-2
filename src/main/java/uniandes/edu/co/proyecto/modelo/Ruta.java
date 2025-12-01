package uniandes.edu.co.proyecto.modelo;

import java.util.List;
import lombok.ToString;

@ToString
public class Ruta {

    private PuntoGeografico origen;
    private PuntoGeografico destino;
    private List<PuntoGeografico> paradas ;
    private double distanciaKm;

    public Ruta() { }

    public Ruta(PuntoGeografico origen, PuntoGeografico destino,
                List<PuntoGeografico> paradas , double distanciaKm) {
        this.origen = origen;
        this.destino = destino;
        this.paradas  = paradas ;
        this.distanciaKm = distanciaKm;
    }

    public PuntoGeografico getOrigen() { return origen; }
    public void setOrigen(PuntoGeografico origen) { this.origen = origen; }

    public PuntoGeografico getDestino() { return destino; }
    public void setDestino(PuntoGeografico destino) { this.destino = destino; }

    public List<PuntoGeografico> getParadas () { return paradas ; }
    public void setParadas (List<PuntoGeografico> paradas ) { this.paradas  = paradas ; }

    public double getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(double distanciaKm) { this.distanciaKm = distanciaKm; }
}