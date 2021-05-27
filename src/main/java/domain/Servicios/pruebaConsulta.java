package domain.Servicios;


import domain.Mascota.DatosMascotaPerdida;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class pruebaConsulta {

    public static void main(String[] args) {
      Contacto matu = new Contacto("M", "Y", 1, "myogui99@gmail.com");
      Contacto guille = new Contacto("G", "K", 1, "kalnikerg@gmail.com");
      Contacto yo = new Contacto("W", "R", 1, "willianramirez33@gmail.com");

      List<Contacto> contactos = new ArrayList<Contacto>();
      contactos.add(yo);
      contactos.add(guille);
      contactos.add(matu);

      DatosPersonales datos = new DatosPersonales(
          "ro",
          "as",
          LocalDate.now(),
          TipoDocumento.DNI,
          1234,
          contactos
      );

      Duenio duenio = new Duenio(
          "wil",
          "alsjdhasdjhaspdiasdpoaisdjasidja´sdjas´djas´diajsdásjdásdija´sd",
          datos
      );

      DatosMascotaPerdida datosPerdida = new DatosMascotaPerdida(
          datos,
          "estaba perdida",
          null,
          "por ahi",
          LocalDate.now(),
          "23sd"
      );

      JavaMailApi api = new JavaMailApi("centrodemascotasdds@gmail.com","tpdds2021");

      api.notificar(duenio, datosPerdida);
    }
}
