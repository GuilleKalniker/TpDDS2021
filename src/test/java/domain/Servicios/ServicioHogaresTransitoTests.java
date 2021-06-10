package domain.Servicios;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ServicioHogaresTransitoTests {

  @Test
  public void solicitarTodosLosHogaresRetornaTodosLosHogaresCompletos(){
    List<HogarTransitoAdaptado> listado = ServicioHogaresTransito.getInstance().solicitarTodosLosHogares();
    Assertions.assertEquals(listado.size(), ServicioHogaresTransito.getInstance().cantidadHogaresTotales());
  }

}
