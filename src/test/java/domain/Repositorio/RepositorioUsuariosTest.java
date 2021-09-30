package domain.Repositorio;

import static org.junit.jupiter.api.Assertions.*;

import Funciones.ValidadorContrasenias;
import domain.Persona.Administrador;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class RepositorioUsuariosTest {

  @BeforeEach
  public void init(){

    AdapterJPA.beginTransaction();
    RepositorioUsuarios repositorioUsuarios = RepositorioUsuarios.getInstance();
  }

  @AfterEach
  public void deinit(){
    AdapterJPA.rollback();
  }

  @Test
  public void existeUsuarioYaRegistrado() {

    //RepositorioUsuarios.getInstance().registrarUsuario(willian);
    willian.registrarse();
    assertThrows(Exception.class, () ->new Administrador(willian.getNombreUsuario(),"iansdaida23"));

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
