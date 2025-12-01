package uniandes.edu.co.proyecto.modelo;

import lombok.ToString;

@ToString
public class Disponibilidad {

    private Dia dia;
    private String horaInicio;
    private String horaFin;
    private TipoServicio tipoServicio;

    public Disponibilidad() { }

    public Disponibilidad(Dia dia, String horaInicio, String horaFin, TipoServicio tipoServicio) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.tipoServicio = tipoServicio;
    }

    public Dia getDia() { return dia; }
    public void setDia(Dia dia) { this.dia = dia; }

    public String getHoraInicio() { return horaInicio; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }

    public String getHoraFin() { return horaFin; }
    public void setHoraFin(String horaFin) { this.horaFin = horaFin; }

    public TipoServicio getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(TipoServicio tipoServicio) { this.tipoServicio = tipoServicio; }
}