package datos;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import domain.PersonaDTO;

/*
* DAO (Data Access Object) es un patrón de diseño que nos permite separar la lógica de negocio de la lógica de acceso a los datos.
* En este caso, la clase PersonaDAO se encarga de realizar operaciones CRUD (Create, Read, Update, Delete) sobre la tabla 'persona' 
* de la base de datos.
*/
public class PersonaDaoJDBC implements PersonaDao {
  // * Útil para realizar operaciones transaccionales que son  un conjunto de
  // operaciones que deben ejecutarse en su totalidad o no
  // * ejecutarse en absoluto, ese decir, si una de las operaciones falla, todas
  // las operaciones deben deshacerse.
  private Connection conexionTransaccional;

  private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
  private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)";
  private static final String SQL_UPDATE = "UPDATE persona SET nombre=?, apellido=?, email=?, telefono=? WHERE id_persona=?";
  private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona = ?";

  public PersonaDaoJDBC() {
  }

  public PersonaDaoJDBC(Connection conexionTransaccional) {
    this.conexionTransaccional = conexionTransaccional;
  }

  // ? Método que selecciona todas las personas de la tabla 'persona' y las
  // devuelve en una lista.
  public List<PersonaDTO> select() {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    PersonaDTO persona = null;
    List<PersonaDTO> personas = new ArrayList<>();

    try {
      conn = this.conexionTransaccional != null
      ? this.conexionTransaccional
      : Conexion.getConnection();
      
      // * Selección de todas las personas de la tabla 'persona'
      stmt = conn.prepareStatement(SQL_SELECT);
      rs = stmt.executeQuery();

      while (rs.next()) {
        int idPersona = rs.getInt("id_persona");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        String email = rs.getString("email");
        String telefono = rs.getString("telefono");

        persona = new PersonaDTO(idPersona, nombre, apellido, email, telefono);

        personas.add(persona);
      }
    } catch (SQLException e) {
      e.printStackTrace(System.out);
    } finally {
      try {
        Conexion.close(rs);
        Conexion.close(stmt);

        //* Si la conexión no es transaccional, se cierra la conexión
        //* Al cerrar una conxion se hace un commit implícito  
        if (this.conexionTransaccional == null) {
          Conexion.close(conn); 
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return personas;
  }

  // ? Método que inserta una persona
  public int insert(PersonaDTO persona) {
    Connection conn = null;
    PreparedStatement stmt = null;
    int registros = 0;

    try {
      conn = this.conexionTransaccional != null
          ? this.conexionTransaccional
          : Conexion.getConnection();

      // * Inserción de una persona en la tabla 'persona'
      stmt = conn.prepareStatement(SQL_INSERT);
      // * Se establecen los valores de los parámetros de la consulta para actualizar
      // una persona
      stmt.setString(1, persona.getNombre());
      stmt.setString(2, persona.getApellido());
      stmt.setString(3, persona.getEmail());
      stmt.setString(4, persona.getTelefono());

      registros = stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace(System.out);
    } finally {
      try {
        Conexion.close(stmt);
        if (this.conexionTransaccional == null) {
          Conexion.close(conn);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return registros;
  }

  // ? Método que actualiza una persona
  public int update(PersonaDTO persona) {
    Connection conn = null;
    PreparedStatement stmt = null;
    int registros = 0;

    try {
      conn = this.conexionTransaccional != null
          ? this.conexionTransaccional
          : Conexion.getConnection();
      
      // * Actualizacion de una persona en la tabla 'persona'
      stmt = conn.prepareStatement(SQL_UPDATE);
      // * Se establecen los valores de los parámetros de la consulta para insertar
      // una persona
      stmt.setString(1, persona.getNombre());
      stmt.setString(2, persona.getApellido());
      stmt.setString(3, persona.getEmail());
      stmt.setString(4, persona.getTelefono());
      stmt.setInt(5, persona.getIdPersona());

      registros = stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace(System.out);
    } finally {
      try {
        Conexion.close(stmt);
        if (this.conexionTransaccional == null) {
          Conexion.close(conn);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return registros;
  }

  // ? Métodos para eliminar una persona
  public int delete(PersonaDTO persona) {
    Connection conn = null;
    PreparedStatement stmt = null;
    int registros = 0;

    try {
      conn = this.conexionTransaccional != null
          ? this.conexionTransaccional
          : Conexion.getConnection();

      // * Eliminar de una persona en la tabla 'persona'
      stmt = conn.prepareStatement(SQL_DELETE);
      // * Se establecen los valores de los parámetros de la consulta para eliminar
      // una persona
      stmt.setInt(1, persona.getIdPersona());

      registros = stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace(System.out);
    } finally {
      try {
        Conexion.close(stmt);
        if (this.conexionTransaccional == null) {
          Conexion.close(conn);
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return registros;
  }
}
