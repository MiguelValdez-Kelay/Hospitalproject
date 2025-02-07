import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConexion {

    public static void main(String[] args) {
        // Configuración de la conexión
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root"; // tu usuario de MySQL
        String password = "Valentino4567"; // tu contraseña de MySQL

        // Intentamos establecer la conexión a MySQL
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión exitosa a MySQL!");

            // Creamos la base de datos 'hospital' si no existe
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("CREATE DATABASE IF NOT EXISTS hospital");
                System.out.println("Base de datos 'hospital' creada (si no existía).");

                // Ahora seleccionamos la base de datos 'hospital'
                connection.setCatalog("hospital");

                // Realizar otras operaciones con la base de datos 'hospital'
                // Puedes añadir más código aquí para trabajar con la base de datos

            }

            // Confirmación de la conexión a la base de datos 'hospital'
            System.out.println("Conexión exitosa a la base de datos 'hospital'!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
