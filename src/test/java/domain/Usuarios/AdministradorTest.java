package domain.Usuarios;

import domain.Persona.Administrador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdministradorTest {
  @Test
  public void unAdminSeCreaBien() throws Exception {
    assertEquals(new Administrador("soyadmin", "soyELadmin123").getClass(), Administrador.class);
  }

  @Test
  public void unDuenioNoSePuedeCrearConContraseniasYUsuarioInvalido() throws Exception {
    Assertions.assertThrows(Exception.class, () -> {new Administrador("soyadmin", "12345");});
  }
}
