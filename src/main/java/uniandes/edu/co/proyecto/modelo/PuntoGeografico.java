package uniandes.edu.co.proyecto.modelo;

import lombok.ToString;

@ToString
public class PuntoGeografico {

    private String direccion;
    private double latitud;
    private double longitud;
    private String idCiudad;

    public PuntoGeografico() { }

    public PuntoGeografico(String direccion, double latitud, double longitud, String idCiudad) {
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.idCiudad = idCiudad;
    }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public double getLatitud() { return latitud; }
    public void setLatitud(double latitud) { this.latitud = latitud; }

    public double getLongitud() { return longitud; }
    public void setLongitud(double longitud) { this.longitud = longitud; }

    public String getIdCiudad() { return idCiudad; }
    public void setIdCiudad(String idCiudad) { this.idCiudad = idCiudad; }
}