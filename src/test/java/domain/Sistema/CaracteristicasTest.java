package domain.Sistema;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CaracteristicasTest {
  private Caracteristicas caracteristicas = new Caracteristicas();

  @Test
  public void agregarCaracteristicaAgregaKeyYValue() {
    caracteristicas.agregarCaracteristicas("Color", valoresPosibles());
    Assertions.assertEquals(caracteristicas.obtenerValoresPosibles("Color").size(), 2);
  }

  @Test
  public void agregarValorACaracteristicaAgregaAKeyEspecificada() {
    caracteristicas.agregarCaracteristicas("Color", valoresPosibles());
    caracteristicas.agregarValorA("Color", "Gris");
    Assertions.assertEquals(caracteristicas.obtenerValoresPosibles("Color").size(), 3);
  }


  public ArrayList<String> valoresPosibles() {
    ArrayList<String> valoresPosibles = new ArrayList<>();
    valoresPosibles.add("Blanco");
    valoresPosibles.add("Negro");
    return valoresPosibles;
  }
}
