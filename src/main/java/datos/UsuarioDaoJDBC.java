package datos;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import domain.UsuarioDTO;

public class UsuarioDaoJDBC {
  private static final String SQL_SELECT = "SELECT id_usuario, usuario, password FROM usuario";
  private static final String SQL_INSERT = "INSERT INTO usuario(usuario, password) VALUES(?, ?)";
  private static final String SQL_UPDATE = "UPDATE usuario SET usuario=?, password=? WHERE id_usuario=?";
  private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";

  public List<UsuarioDTO> seleccionar() {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    UsuarioDTO usuario = null;
    List<UsuarioDTO> usuarios = new ArrayList<>();

    try {
      conn = Conexion.getConnection();
      stmt = conn.prepareStatement(SQL_SELECT);
      rs = stmt.executeQuery();

      while (rs.next()) {
        int idUsuario = rs.getInt("id_usuario");
        String usuarioName = rs.getString("usuario");
        String password = rs.getString("password");

        usuario = new UsuarioDTO(idUsuario, usuarioName, password);

        usuarios.add(usuario);
      }
    } catch (SQLException e) {
      e.printStackTrace(System.out);
    } finally {
      try {
        Conexion.close(rs);
        Conexion.close(stmt);
        Conexion.close(conn);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return usuarios;
  }

  public int insertar(UsuarioDTO usuario) {
    Connection conn = null;
    PreparedStatement stmt = null;
    int registros = 0;

    try {
      conn = Conexion.getConnection();
      stmt = conn.prepareStatement(SQL_INSERT);
      stmt.setString(1, usuario.getUsuario());
      stmt.setString(2, usuario.getPassword());

      registros = stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace(System.out);
    } finally {
      try {
        Conexion.close(stmt);
        Conexion.close(conn);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return registros;
  }

  public int actualizar(UsuarioDTO usuario) {
    Connection conn = null;
    PreparedStatement stmt = null;
    int registros = 0;

    try {
      conn = Conexion.getConnection();
      stmt = conn.prepareStatement(SQL_UPDATE);
      stmt.setString(1, usuario.getUsuario());
      stmt.setString(2, usuario.getPassword());
      stmt.setInt(3, usuario.getIdUsuario());

      registros = stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace(System.out);
    } finally {
      try {
        Conexion.close(stmt);
        Conexion.close(conn);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return registros;
  }

  public int eliminar(UsuarioDTO usuario) {
    Connection conn = null;
    PreparedStatement stmt = null;
    int registros = 0;

    try {
      conn = Conexion.getConnection();
      stmt = conn.prepareStatement(SQL_DELETE);
      stmt.setInt(1, usuario.getIdUsuario());

      registros = stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace(System.out);
    } finally {
      try {
        Conexion.close(stmt);
        Conexion.close(conn);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return registros;
  }
}
