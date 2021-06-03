package domain.Repositorio;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Repositorio.RepositorioCaracteristicas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class RepositorioCaracteristicasTest {

  private RepositorioCaracteristicas repositorioCaracteristicas =  mock(RepositorioCaracteristicas.class);

  @BeforeEach
  void init() {

  }

  @Test
  public void agregarCaracteristicaAgregaOK() {
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.ARISCO);
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.CASTRADO);
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.BAJO);
    Assertions.assertEquals(repositorioCaracteristicas.getCaracteristicasVigentes().size(), 3);
  }

  @Test
  public void agregarDosVecesLaMismaCaracteristicaNoLaRepite() {
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.ARISCO);
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.ARISCO);
    Assertions.assertEquals(repositorioCaracteristicas.getCaracteristicasVigentes().size(), 1);
  }

  @Test
  public void sacarCaracteristicaSacaOK() {
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.ARISCO);
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.MARRON);
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.BAJO);
    repositorioCaracteristicas.sacarCaracteristica(Caracteristica.MARRON);
    Assertions.assertEquals(repositorioCaracteristicas.getCaracteristicasVigentes().size(), 2);
  }

}
