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
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.Arisco);
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.Castrada);
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.Bajo);
    Assertions.assertEquals(repositorioCaracteristicas.getCaracteristicasVigentes().size(), 3);
  }

  @Test
  public void agregarDosVecesLaMismaCaracteristicaNoLaRepite() {
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.Arisco);
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.Arisco);
    Assertions.assertEquals(repositorioCaracteristicas.getCaracteristicasVigentes().size(), 1);
  }

  @Test
  public void sacarCaracteristicaSacaOK() {
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.Arisco);
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.Marron);
    repositorioCaracteristicas.agregarCaracteristica(Caracteristica.Bajo);
    repositorioCaracteristicas.sacarCaracteristica(Caracteristica.Marron);
    Assertions.assertEquals(repositorioCaracteristicas.getCaracteristicasVigentes().size(), 2);
  }

}
