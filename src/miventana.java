import javax.swing.*;
public class miventana {
    public static void main(String[] args) {
        // Crear la ventana
        JFrame ventana = new JFrame("Mi Primera Ventana");

        // Configurar tamaño y acción al cerrar
        ventana.setSize(800, 700);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hacer la ventana visible
        ventana.setVisible(true);
    }
}
