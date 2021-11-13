package domain.Mascota;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import domain.Mascota.AtributosMascota.Sexo;
import domain.Mascota.AtributosMascota.TipoMascota;

public class MascotaTest {

  /*@Test
  public void reconocePropioQR() {
    pepita.generarQR();

    assert(pepita.esMiQR("1"));
  }*/


  private MascotaRegistrada pepita = new MascotaRegistrada(TipoMascota.PERRO, "Pepita", "Pepisauria", 9, Sexo.HEMBRA, "Perra corgi muy linda", new ArrayList<String>(),new ArrayList<>());

}
