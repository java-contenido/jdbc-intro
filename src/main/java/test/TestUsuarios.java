package test;

import java.util.List;

import datos.UsuarioDaoJDBC;
import domain.UsuarioDTO;

public class TestUsuarios {
  public static void main(String[] args) {
    System.out.println();

    UsuarioDaoJDBC usuarioDAO = new UsuarioDaoJDBC();

    
    /*  
    //* Insertar una nueva usuario
    UsuarioDTO nuevoUsuario = new UsuarioDTO("clara23", "123");
    usuarioDAO.insertar(nuevoUsuario);
    
    //* Actualizar una usuario
    UsuarioDTO usuarioActualizar = new UsuarioDTO(6, "clara23pro", "123admin");
    usuarioDAO.actualizar(usuarioActualizar);

    //* Eliminar una usuario
    UsuarioDTO usuarioEliminar = new UsuarioDTO(6);
    usuarioDAO.eliminar(usuarioEliminar);
    */
    
    
    //* Listar todas las usuarios 
    List<UsuarioDTO> usuarios = usuarioDAO.seleccionar();
    usuarios.forEach(usuario -> {
      System.out.println("usuario = " + usuario);
    });

  }
}
