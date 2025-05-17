package tienda;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un cliente con historial de compras.
 */
public class Cliente {
    private String nombre;
    private String correo;
    private List<String> historialDeCompras;

    public Cliente(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
        this.historialDeCompras = new ArrayList<>();
    }

    public void agregarCompra(String descripcion) {
        historialDeCompras.add(descripcion);
    }

    public List<String> getHistorialDeCompras() {
        return historialDeCompras;
    }
}