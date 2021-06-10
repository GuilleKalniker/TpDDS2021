package Funciones;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CreacionDeQRTest {

  @Test
  public void CrearQr(){
     String file = ManejoQR.generarQR(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1000, 1000);
     System.out.println(ManejoQR.leerQR(file));
  }
}
