package domain.Persona;

import domain.Repositorio.RepositorioCaracteristicas;
import domain.Repositorio.RepositorioCaracteristicasTest;
import domain.Repositorio.RepositorioUsuarios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class AdministradorTest {

  @BeforeEach
  void init() {
    RepositorioUsuarios repoUsuariosMock = mock(RepositorioUsuarios.class);
    RepositorioCaracteristicas repoCaracteristicasMock = mock(RepositorioCaracteristicas.class);

    admin.setRepositorioCaracteristicas(repoCaracteristicasMock);
    admin.setRepositorioUsuarios(repoUsuariosMock);
  }

  @Test
  public void unAdminSeCreaBien() throws Exception {
    assertEquals(new Administrador("soyadmin", "soyELadmin123").getClass(), Administrador.class);
  }

  @Test
  public void unAdminNoSePuedeCrearConContraseniasYUsuarioInvalido() throws Exception {
    Assertions.assertThrows(Exception.class, () -> {adminErroneo().registrarse();});
  }

  public Administrador admin = new Administrador("soyadmin", "soyELadmin123");

  public Administrador adminErroneo() {
    return new Administrador("soyadmin", "12345");
  }
}


