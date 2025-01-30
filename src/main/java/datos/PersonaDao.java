package datos;

import java.util.List;

import domain.PersonaDTO;

public interface PersonaDao {
  public List<PersonaDTO> select();
  public int insert(PersonaDTO persona);
  public int update(PersonaDTO persona);
  public int delete(PersonaDTO persona);
}
