package tienda;

import javax.swing.*;
import java.awt.*;

/**
 * Interfaz gráfica principal para la tienda.
 */
public class MainApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().crearGUI());
    }

    private Tienda tienda = new Tienda();
    private Cliente cliente = new Cliente("Juan Pérez", "juan@mail.com");
    private JTextArea areaCompras;
    private double totalComprado = 0.0;

    public void crearGUI() {
        JFrame frame = new JFrame("Tienda OO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 450);

        JComboBox<Producto> comboProductos = new JComboBox<>();
        JTextField cantidadField = new JTextField(5);
        JButton comprarBtn = new JButton("Comprar");
        JButton totalBtn = new JButton("Ver Total");
        areaCompras = new JTextArea(10, 40);
        areaCompras.setEditable(false);

        tienda.agregarProducto(new Producto("Pan", 2500, 10));
        tienda.agregarProducto(new Producto("Leche", 3200, 15));
        tienda.agregarProducto(new Producto("Huevos", 6000, 12));
        tienda.agregarProducto(new Producto("Arroz", 4200, 20));
        tienda.agregarProducto(new Producto("Jabón", 3800, 8));

        for (Producto p : tienda.getProductos()) {
            comboProductos.addItem(p);
        }

        comprarBtn.addActionListener(e -> realizarCompra(comboProductos, cantidadField));
        totalBtn.addActionListener(e -> mostrarTotal());

        JPanel panel = new JPanel();
        panel.add(new JLabel("Producto:"));
        panel.add(comboProductos);
        panel.add(new JLabel("Cantidad:"));
        panel.add(cantidadField);
        panel.add(comprarBtn);
        panel.add(totalBtn);

        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(new JScrollPane(areaCompras), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void realizarCompra(JComboBox<Producto> comboProductos, JTextField cantidadField) {
        Producto producto = (Producto) comboProductos.getSelectedItem();
        int cantidad;

        try {
            cantidad = Integer.parseInt(cantidadField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida.");
            return;
        }

        if (producto != null && producto.vender(cantidad)) {
            double total = cantidad * producto.getPrecio();
            totalComprado += total;
            String resumen = "Compraste " + cantidad + " de " + producto.getNombre() + " por $" + total;
            cliente.agregarCompra(resumen);
            areaCompras.append(resumen + "\n");
        } else {
            JOptionPane.showMessageDialog(null, "No hay suficiente cantidad disponible.");
        }

        cantidadField.setText("");
    }

    private void mostrarTotal() {
        areaCompras.append("\nTotal comprado: $" + totalComprado + "\n");
    }
}