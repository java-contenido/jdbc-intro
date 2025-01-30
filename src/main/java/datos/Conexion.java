package datos;

import java.io.InputStream;
import java.util.Properties;

import test.App;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
  private static final String JDBC_URL = "db.url";
  private static final String JDBC_USER = "db.user";
  private static final String JDBC_PASSWORD = "db.password";

  //? Método para cargar propiedades de archivo 'db.properties' 
  public static Properties loadProperties(String file) {
    // Leer configuración de db.properties
    Properties prop = new Properties();

    try (InputStream is = App.class.getClassLoader().getResourceAsStream(file)) {
      prop.load(is);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return prop;
  }

  //? Método para obtener conexión 
  public static Connection getConnection() throws SQLException {
    Properties props = loadProperties("db.properties");

    // Obtener detalles de conexión
    String url = props.getProperty(Conexion.JDBC_URL);
    String user = props.getProperty(Conexion.JDBC_USER);
    String password = props.getProperty(Conexion.JDBC_PASSWORD);

    // Establecer conexión
    Connection connection = DriverManager.getConnection(url, user, password);
    // System.out.println("¡Conexión exitosa!");

    return connection;
  }

  //? Método para cerrar conexión de ResultSet
  public static void close(ResultSet rs) throws SQLException {
    rs.close();
  }
   
  //? Método para cerrar conexión de PreparedStatement
  public static void close(PreparedStatement stmt) throws SQLException {
    stmt.close();
  }

  //? Método para cerrar conexión de Connection
  public static void close(Connection conn) throws SQLException {
    conn.close();
  }

}
