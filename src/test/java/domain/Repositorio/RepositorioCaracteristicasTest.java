package domain.Repositorio;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Repositorio.RepositorioCaracteristicas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RepositorioCaracteristicasTest {

  private RepositorioCaracteristicas repositorioCaracteristicas =  RepositorioCaracteristicas.getInstance();

  @BeforeEach
  void init() {
    RepositorioCaracteristicas.getInstance().getCaracteristicasVigentes().clear();
  }

  @Test
  public void agregarCaracteristicaAgregaOK() {
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.ARISCO);
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.ALTO);
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
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.ALTO);
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.BAJO);
    repositorioCaracteristicas.sacarCaracteristica(Caracteristica.ALTO);
    Assertions.assertEquals(repositorioCaracteristicas.getCaracteristicasVigentes().size(), 2);
  }

}
