package Sistema;

import Funciones.creacionDeQR;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

public class creacionDeQRTest {

  @Test
  public void CrearQr(){
     String file = creacionDeQR.generarQR("chau", "primerQr", 1000, 1000);
     System.out.println(creacionDeQR.leerQR(file));
  }
}
