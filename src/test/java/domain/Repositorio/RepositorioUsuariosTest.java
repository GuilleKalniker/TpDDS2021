package domain.Repositorio;

import static org.junit.jupiter.api.Assertions.*;

import Funciones.ValidadorContrasenias;
import domain.Persona.Administrador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class RepositorioUsuariosTest {

  @BeforeEach
  public void init(){
    RepositorioUsuarios repositorioUsuarios = RepositorioUsuarios.getInstance();
  }

  @Test
  public void existeUsuarioYaRegistrado() {

    RepositorioUsuarios.getInstance().registrarUsuario(willian);
    assertThrows(Exception.class, () -> RepositorioUsuarios.getInstance().existeUsuario(willian.getNombreUsuario()));
  }

  @Test
  public void NoEsValidaUnaContraseñaConLongitudMenosA8() {
    assertFalse(ValidadorContrasenias.cumpleLongitudMinima("willi"));
  }

  @Test
  public void contraseñaEsInvalidaPorPertenecerAListaContraseñas() {
    assertTrue(ValidadorContrasenias.existeContraseniaEnListaContraseniasNoSeguras("123456789"));
  }

  @Test
  public void contraseñaEsvalidaPorNoPertenecerAListaContraseñas() {
    assertFalse(ValidadorContrasenias.existeContraseniaEnListaContraseniasNoSeguras("tpanual2021"));
  }

  Administrador willian = new Administrador("willian","sdhasuhpfdsdhfp");
}
