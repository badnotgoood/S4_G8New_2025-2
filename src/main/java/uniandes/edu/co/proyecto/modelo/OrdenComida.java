package uniandes.edu.co.proyecto.modelo;

public class OrdenComida {

    private String id;
    private String servicioId;
    private String restaurante;
    private String pedido;

    public OrdenComida() { }

    public OrdenComida(String id, String servicioId, String restaurante, String pedido) {
        this.id = id;
        this.servicioId = servicioId;
        this.restaurante = restaurante;
        this.pedido = pedido;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getServicioId() { return servicioId; }
    public void setServicioId(String servicioId) { this.servicioId = servicioId; }

    public String getRestaurante() { return restaurante; }
    public void setRestaurante(String restaurante) { this.restaurante = restaurante; }

    public String getPedido() { return pedido; }
    public void setPedido(String pedido) { this.pedido = pedido; }
}