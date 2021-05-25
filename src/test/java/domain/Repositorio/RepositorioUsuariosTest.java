package domain.Repositorio;

import static org.junit.jupiter.api.Assertions.*;

import Funciones.ValidadorContrasenias;
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
    assertFalse(validador.cumpleLongitudMinima("willi"));
  }

  @Test
  public void contraseñaEsInvalidaPorPertenecerAListaContraseñas() {
    assertTrue(validador.existeContraseniaEnListaContraseniasNoSeguras("123456789"));
  }

  @Test
  public void contraseñaEsvalidaPorNoPertenecerAListaContraseñas() {
    assertFalse(validador.existeContraseniaEnListaContraseniasNoSeguras("tpanual2021"));
  }

  private ValidadorContrasenias validador = new ValidadorContrasenias(8);

}
