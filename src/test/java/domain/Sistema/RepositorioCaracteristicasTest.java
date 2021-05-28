package domain.Sistema;

import domain.Repositorio.RepositorioCaracteristicas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RepositorioCaracteristicasTest {

  private RepositorioCaracteristicas repositorioCaracteristicas =  RepositorioCaracteristicas.getInstance();

  @Test
  public void agregarCaracteristicaAgregaKeyYValue() {
    repositorioCaracteristicas.agregarCaracteristicas("Color", valoresPosibles());
    Assertions.assertEquals(repositorioCaracteristicas.obtenerValoresPosibles("Color").size(), 2);
  }

  @Test
  public void agregarValorACaracteristicaAgregaAKeyEspecificada() {
    repositorioCaracteristicas.agregarCaracteristicas("Color", valoresPosibles());
    repositorioCaracteristicas.agregarValorA("Color", "Gris");
    Assertions.assertEquals(repositorioCaracteristicas.obtenerValoresPosibles("Color").size(), 3);
  }


  public ArrayList<String> valoresPosibles() {
    ArrayList<String> valoresPosibles = new ArrayList<>();
    valoresPosibles.add("Blanco");
    valoresPosibles.add("Negro");
    return valoresPosibles;
  }
}
