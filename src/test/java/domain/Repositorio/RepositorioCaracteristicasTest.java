package domain.Repositorio;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Repositorio.RepositorioCaracteristicas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class RepositorioCaracteristicasTest {


  @BeforeEach
  void init() {
    RepositorioCaracteristicas.getInstance().getCaracteristicasVigentes().clear();
  }

  @Test
  public void agregarCaracteristicaAgregaOK() {
    RepositorioCaracteristicas.getInstance().agregarCaracteristica(Caracteristica.ARISCO);
    RepositorioCaracteristicas.getInstance().agregarCaracteristica(Caracteristica.CASTRADO);
    RepositorioCaracteristicas.getInstance().agregarCaracteristica(Caracteristica.BAJO);
    Assertions.assertEquals(3, RepositorioCaracteristicas.getInstance().getCaracteristicasVigentes().size());
  }

  @Test
  public void agregarDosVecesLaMismaCaracteristicaNoLaRepite() {
    RepositorioCaracteristicas.getInstance().agregarCaracteristica(Caracteristica.ARISCO);
    RepositorioCaracteristicas.getInstance().agregarCaracteristica(Caracteristica.ARISCO);
    Assertions.assertEquals(1, RepositorioCaracteristicas.getInstance().getCaracteristicasVigentes().size());
  }

  @Test
  public void sacarCaracteristicaSacaOK() {
    RepositorioCaracteristicas.getInstance().agregarCaracteristica(Caracteristica.ARISCO);
    RepositorioCaracteristicas.getInstance().agregarCaracteristica(Caracteristica.MARRON);
    RepositorioCaracteristicas.getInstance().agregarCaracteristica(Caracteristica.BAJO);
    RepositorioCaracteristicas.getInstance().sacarCaracteristica(Caracteristica.MARRON);
    Assertions.assertEquals(2, RepositorioCaracteristicas.getInstance().getCaracteristicasVigentes().size());
  }

}
