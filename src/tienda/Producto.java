package tienda;

/**
 * Representa un producto con nombre, precio, y cantidad disponible.
 */
public class Producto implements Vendible {
    private String nombre;
    private double precio;
    private int cantidadDisponible;

    public Producto(String nombre, double precio, int cantidadDisponible) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    @Override
    public boolean vender(int cantidad) {
        if (cantidad <= cantidadDisponible && cantidad > 0) {
            cantidadDisponible -= cantidad;
            return true;
        }
        return false;
    }

    public void reabastecer(int cantidad) {
        if (cantidad > 0) {
            cantidadDisponible += cantidad;
        }
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio + " (" + cantidadDisponible + " disponibles)";
    }
}