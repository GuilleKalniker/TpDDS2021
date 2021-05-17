package domain.Sistema;

import domain.Repositorio.RepositorioUsuarios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RepositorioUsuariosTest {

  @Test
  public void existeUsuarioYaRegistrado() throws Exception {
    RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
    repositorioUsuarios.registrarse("willian","sdhasuhpfdsdhfp");
    Assertions.assertThrows(Exception.class, () -> repositorioUsuarios.existeUsuario("willian"));
  }

  @Test
  public void NoEsValidaUnaContraseñaConLongitudMenosA8() throws Exception {
    RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
    Assertions.assertFalse(repositorioUsuarios.cumpleLongitudMinima("willi"));
  }

  /*@Test
  public void contraseniaEsValidaConLongitudValidaYNoSeEncuentraEnLista(){
    AltaUsuarios altaUsuarios = new AltaUsuarios();
    Assertions.assertThrows(ContraseniaInvalidaException.class, () ->altaUsuarios.esUnaContraseniaValida("willian1234"));
  }*/

  @Test
  public void contraseñaEsInvalidaPorPertenecerAListaContraseñas() throws Exception {
    RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
    Assertions.assertTrue(repositorioUsuarios.existeContraseniaEnListaContraseniasNoSeguras("123456789"));
  }

  @Test
  public void contraseñaEsvalidaPorNoPertenecerAListaContraseñas() throws Exception {
    RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
    Assertions.assertFalse(repositorioUsuarios.existeContraseniaEnListaContraseniasNoSeguras("tpanual2021"));
  }

}
