package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.border.LineBorder;

public class MainWindow extends JFrame {
    private final JTextField tfNombre;
    private JTextField tfApellido;
    private JTextField tfEdad;
    private JTextField tfTelefono;
    private JTextField tfDireccion;
    private JComboBox<String> cbGenero;
    private JButton btnRegistrar;

    public MainWindow() {
        setTitle("Registro de Pacientes");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));

        // Panel principal
        JPanel panelForm = new JPanel();
        panelForm.setLayout(new GridBagLayout());
        panelForm.setBackground(new Color(81, 209, 246));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 20)); // Ajustamos margen izquierdo
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Colores personalizados
        Color fieldColor = new Color(245, 245, 245);
        Color buttonColor = new Color(80, 150, 255);
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        // Componentes del formulario
        JLabel lblNombre = createLabel("Nombre:");
        tfNombre = new JTextField(20); // Aumentamos el tamaño del campo de texto
        tfNombre.setBackground(fieldColor);
        tfNombre.setBorder(new LineBorder(Color.GRAY, 10));

        JLabel lblApellido = createLabel("Apellido:");
        tfApellido = new JTextField(20);
        tfApellido.setBackground(fieldColor);
        tfApellido.setBorder(new LineBorder(Color.GRAY, 10));

        JLabel lblEdad = createLabel("Edad:");
        tfEdad = new JTextField(20);
        tfEdad.setBackground(fieldColor);
        tfEdad.setBorder(new LineBorder(Color.GRAY, 10));

        JLabel lblGenero = createLabel("Género:");
        cbGenero = new JComboBox<>(new String[]{"Masculino", "Femenino"});
        cbGenero.setBackground(fieldColor);
        cbGenero.setBorder(new LineBorder(Color.GRAY, 10));
        cbGenero.setPreferredSize(new Dimension(220, 50));;
        cbGenero.setPrototypeDisplayValue("Seleccionar Genero");

        JLabel lblTelefono = createLabel("Teléfono:");
        tfTelefono = new JTextField(20);
        tfTelefono.setBackground(fieldColor);
        tfTelefono.setBorder(new LineBorder(Color.GRAY, 10));

        JLabel lblDireccion = createLabel("Dirección:");
        tfDireccion = new JTextField(20);
        tfDireccion.setBackground(fieldColor);
        tfDireccion.setBorder(new LineBorder(Color.GRAY, 10));
        // Configurar alineación de etiquetas y campos
        gbc.anchor = GridBagConstraints.WEST; // Alinea a la izquierda
        gbc.fill = GridBagConstraints.HORIZONTAL; // Permite expansión horizontal
        gbc.weightx = 1; // Hace que los campos se expandan mejor

        gbc.gridx = 0; gbc.gridy = 0; panelForm.add(lblNombre, gbc);
        gbc.gridx = 1; panelForm.add(tfNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1; panelForm.add(lblApellido, gbc);
        gbc.gridx = 1; panelForm.add(tfApellido, gbc);

        gbc.gridx = 0; gbc.gridy = 2; panelForm.add(lblEdad, gbc);
        gbc.gridx = 1; panelForm.add(tfEdad, gbc);

        gbc.gridx = 0; gbc.gridy = 3; panelForm.add(lblGenero, gbc);
        gbc.gridx = 1; panelForm.add(cbGenero, gbc);

        gbc.gridx = 0; gbc.gridy = 4; panelForm.add(lblTelefono, gbc);
        gbc.gridx = 1; panelForm.add(tfTelefono, gbc);

        gbc.gridx = 0; gbc.gridy = 5; panelForm.add(lblDireccion, gbc);
        gbc.gridx = 1; panelForm.add(tfDireccion, gbc);

        // Botón de registro
        btnRegistrar = new JButton("Registrar Paciente");
        btnRegistrar.setBackground(buttonColor);
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setPreferredSize(new Dimension(200, 40));

        // Panel para el botón
        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelButton.setBackground(new Color(255, 255, 255));
        panelButton.add(btnRegistrar);

        // Agregar paneles a la ventana
        add(panelForm, BorderLayout.CENTER);
        add(panelButton, BorderLayout.SOUTH);

        // Acción del botón
        btnRegistrar.addActionListener((ActionEvent e) -> registrarPaciente());

        setVisible(true);
    }

    // Método para crear etiquetas con alineación correcta
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(60, 60, 60));
        label.setPreferredSize(new Dimension(100, 30)); // Asegura que todas tengan el mismo ancho
        return label;
    }

    // Método para crear campos de texto con borde uniforme
    private JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setBackground(new Color(245, 245, 245));
        field.setBorder(new LineBorder(Color.GRAY, 1));
        field.setPreferredSize(new Dimension(250, 30)); // Aseguramos que sean más largos
        return field;
    }

    private void registrarPaciente() {
        String url = "jdbc:mysql://localhost:3306/hospital";
        String usuario = "root";
        String contraseña = "Valentino4567";

        String sql = "INSERT INTO pacientes (nombre, apellido, edad, genero, telefono, direccion, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, NOW())";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, tfNombre.getText());
            pstmt.setString(2, tfApellido.getText());
            pstmt.setInt(3, Integer.parseInt(tfEdad.getText()));
            pstmt.setString(4, (String) cbGenero.getSelectedItem());
            pstmt.setString(5, tfTelefono.getText());
            pstmt.setString(6, tfDireccion.getText());

            int filasInsertadas = pstmt.executeUpdate();

            if (filasInsertadas > 0) {
                JOptionPane.showMessageDialog(this, "Paciente registrado exitosamente.");
                limpiarCampos();
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar el paciente: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void limpiarCampos() {
        tfNombre.setText("");
        tfApellido.setText("");
        tfEdad.setText("");
        tfTelefono.setText("");
        tfDireccion.setText("");
        cbGenero.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}



