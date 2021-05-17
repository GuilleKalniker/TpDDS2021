package domain.Sistema;

import domain.Repositorio.AltaUsuarios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AltaUsuariosTest {

  @Test
  public void existeUsuarioYaRegistrado() throws Exception {
    AltaUsuarios altaUsuarios = new AltaUsuarios();
    altaUsuarios.registrarse("willian","sdhasuhpfdsdhfp");
    Assertions.assertThrows(Exception.class, () -> altaUsuarios.existeUsuario("willian"));
  }

  @Test
  public void NoEsValidaUnaContraseñaConLongitudMenosA8() throws Exception {
    AltaUsuarios altaUsuarios = new AltaUsuarios();
    Assertions.assertFalse(altaUsuarios.cumpleLongitudMinima("willi"));
  }

  /*@Test
  public void contraseniaEsValidaConLongitudValidaYNoSeEncuentraEnLista(){
    AltaUsuarios altaUsuarios = new AltaUsuarios();
    Assertions.assertThrows(ContraseniaInvalidaException.class, () ->altaUsuarios.esUnaContraseniaValida("willian1234"));
  }*/

  @Test
  public void contraseñaEsInvalidaPorPertenecerAListaContraseñas() throws Exception {
    AltaUsuarios altaUsuarios = new AltaUsuarios();
    Assertions.assertTrue(altaUsuarios.existeContraseniaEnListaContraseniasNoSeguras("123456789"));
  }

  @Test
  public void contraseñaEsvalidaPorNoPertenecerAListaContraseñas() throws Exception {
    AltaUsuarios altaUsuarios = new AltaUsuarios();
    Assertions.assertFalse(altaUsuarios.existeContraseniaEnListaContraseniasNoSeguras("tpanual2021"));
  }

}
