package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.border.LineBorder;

public class LoginWindow extends JFrame {
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JButton btnLogin;

    public LoginWindow() {
        setTitle("Login");
        setSize(600, 550);  // Tamaño actualizado
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20));

        // Panel principal
        JPanel panelLogin = new JPanel();
        panelLogin.setLayout(new GridBagLayout());
        panelLogin.setBackground(new Color(81, 209, 246));  // Azul claro
        panelLogin.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Colores personalizados
        Color fieldColor = new Color(245, 245, 245);  // Gris claro para los campos
        Color buttonColor = new Color(80, 150, 255);  // Azul para el botón
        Font boldFont = new Font("Arial", Font.BOLD, 14);

        // Componentes
        JLabel lblUsername = new JLabel("Usuario:");
        lblUsername.setFont(boldFont);
        lblUsername.setForeground(new Color(60, 60, 60));

        tfUsername = new JTextField(20);
        tfUsername.setBackground(fieldColor);
        tfUsername.setBorder(new LineBorder(Color.GRAY, 1));

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(boldFont);
        lblPassword.setForeground(new Color(60, 60, 60));

        pfPassword = new JPasswordField(20);
        pfPassword.setBackground(fieldColor);
        pfPassword.setBorder(new LineBorder(Color.GRAY, 1));

        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBackground(buttonColor);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setPreferredSize(new Dimension(200, 40));

        // Agregar componentes al panel
        gbc.gridx = 0; gbc.gridy = 0; panelLogin.add(lblUsername, gbc);
        gbc.gridx = 1; panelLogin.add(tfUsername, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panelLogin.add(lblPassword, gbc);
        gbc.gridx = 1; panelLogin.add(pfPassword, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        panelLogin.add(btnLogin, gbc);

        // Agregar el panel al centro de la ventana
        add(panelLogin, BorderLayout.CENTER);

        // Acción del botón
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarLogin();
            }
        });

        setVisible(true);
    }

    private void verificarLogin() {
        String username = tfUsername.getText();
        String password = new String(pfPassword.getPassword());

        String url = "jdbc:mysql://localhost:3306/hospital";
        String usuario = "root";
        String contraseña = "Valentino4567";

        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "¡Inicio de sesión exitoso!");
                dispose(); // Cierra la ventana de login
                new MainWindow(); // Abre la ventana principal
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginWindow::new);
    }
}



