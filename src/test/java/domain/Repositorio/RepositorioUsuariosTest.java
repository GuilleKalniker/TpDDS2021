package domain.Repositorio;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepositorioUsuariosTest {

  @BeforeEach
  public void init(){
    RepositorioUsuarios repositorioUsuarios = RepositorioUsuarios.getInstance();
    repositorioUsuarios.clear();
  }

  @Test
  public void existeUsuarioYaRegistrado() {
    RepositorioUsuarios.getInstance().registrarse("willian","sdhasuhpfdsdhfp");
    assertThrows(Exception.class, () -> RepositorioUsuarios.getInstance().existeUsuario("willian"));
  }

  @Test
  public void NoEsValidaUnaContraseñaConLongitudMenosA8() {
    assertFalse(RepositorioUsuarios.getInstance().cumpleLongitudMinima("willi"));
  }

  @Test
  public void contraseñaEsInvalidaPorPertenecerAListaContraseñas() {
    assertTrue(RepositorioUsuarios.getInstance().existeContraseniaEnListaContraseniasNoSeguras("123456789"));
  }

  @Test
  public void contraseñaEsvalidaPorNoPertenecerAListaContraseñas() {
    assertFalse(RepositorioUsuarios.getInstance().existeContraseniaEnListaContraseniasNoSeguras("tpanual2021"));
  }

}
