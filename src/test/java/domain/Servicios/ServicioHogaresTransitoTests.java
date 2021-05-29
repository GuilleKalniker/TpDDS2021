package domain.Servicios;

import domain.Servicios.ClasesParaLaConsulta.HogarTransito;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ServicioHogaresTransitoTests {

  @Test
  public void solicitarTodosLosHogaresRetornaTodosLosHogaresCompletos(){
    List<HogarTransito> listado = ServicioHogaresTransito.getInstance().solicitarTodosLosHogares();
    Assertions.assertEquals(listado.size(), ServicioHogaresTransito.getInstance().cantidadHogaresTotales());
  }

}
