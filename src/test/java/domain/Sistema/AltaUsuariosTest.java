package domain.Sistema;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AltaUsuariosTest {

  @Test
  public void existeUsuarioYaRegistrado() throws Exception {
    AltaUsuarios altaUsuarios = new AltaUsuarios();
    altaUsuarios.registrarse("willian","sdhasuhpfdsdhfp");
    Assertions.assertTrue(altaUsuarios.existeUsuario("willian"));
  }

  @Test
  public void NoEsValidaUnaContraseñaConLongitudMenosA8() throws Exception {
    AltaUsuarios altaUsuarios = new AltaUsuarios();
    Assertions.assertFalse(altaUsuarios.cumpleLongitudMinima("willi"));
  }

  @Test
  public void contraseniaEsValidaConLongitudValidaYNoSeEncuentraEnLista() throws Exception {
    AltaUsuarios altaUsuarios = new AltaUsuarios();
    Assertions.assertTrue(altaUsuarios.esUnaContraseniaValida("willian1234"));
  }

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