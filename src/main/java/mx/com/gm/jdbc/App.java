package mx.com.gm.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;
import java.io.InputStream;


public class App {
  public static void main(String[] args) {
    Connection connection = null;
    
    try {
      // Leer configuración de db.properties
      Properties props = new Properties();
      InputStream input = App.class.getClassLoader().getResourceAsStream("db.properties");
      props.load(input);

      // Obtener detalles de conexión
      String url = props.getProperty("db.url");
      String user = props.getProperty("db.user");
      String password = props.getProperty("db.password");

      // Establecer conexión
      connection = DriverManager.getConnection(url, user, password);
      System.out.println("¡Conexión exitosa!");

      // Ejemplo de consulta
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM persona");

      while (rs.next()) {
        // Cambia según tus columnas
        System.out.println("Fila: " + rs.getString(1));

        int id = rs.getInt("id_persona");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        System.out.println("ID: " + id + ", Nombre: " + nombre + ", Apellido: " + apellido);
      }
    } catch (SQLException e) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    } catch (IOException e) {
      Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, e);
    } finally {
      try {
        if (connection != null) {
          connection.close();
          System.out.println("Conexión cerrada.");
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
