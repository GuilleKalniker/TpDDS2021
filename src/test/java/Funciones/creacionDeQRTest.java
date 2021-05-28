package Funciones;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class creacionDeQRTest {

  @Test
  public void CrearQr(){
     String file = manejoQR.generarQR(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1000, 1000);
     System.out.println(manejoQR.leerQR(file));
  }
}
