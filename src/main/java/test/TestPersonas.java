package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import domain.PersonaDTO;
import datos.Conexion;
import datos.PersonaDaoJDBC;

public class TestPersonas {
  public static void main(String[] args) {
    System.out.println();

    Connection conexion = null;

    try {
      conexion = Conexion.getConnection();
      //* Si la conexión está en modo autocommit se desactiva 
      if (conexion.getAutoCommit()) {
        conexion.setAutoCommit(false);
        
      }

      // PersonaDAO personaDAO = new PersonaDAO();
      PersonaDaoJDBC personaDAO = new PersonaDaoJDBC(conexion);

      /*
      //* Insertar una nueva persona
      PersonaDTO nuevaPersona = new PersonaDTO("Alejandro", "Rojas", "alejandror@email.com",
      "5566778899");
      personaDAO.insert(nuevaPersona);
      
      //* Actualizar una persona
      PersonaDTO personaActualizar = new PersonaDTO(34,"Juan Carlos", "Lara",
      "clara23@email.com", "5566778899");
      personaDAO.update(personaActualizar);
      
      //* Eliminar una persona
      PersonaDTO personaEliminar = new PersonaDTO(34);
      personaDAO.delete(personaEliminar);
       */

      // * Listar todas las personas
      List<PersonaDTO> personas = personaDAO.select();
      personas.forEach(persona -> {
        System.out.println("persona = " + persona);
      });

      //* Hacer commit
      conexion.commit();
    } catch (SQLException e) {
      e.printStackTrace(System.out);
    }

  }
}
