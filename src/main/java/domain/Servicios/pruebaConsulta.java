package domain.Servicios;


import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.DatosMascotaPerdida;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Servicios.ClasesParaLaConsulta.HogarTransito;
import domain.Servicios.ClasesParaLaConsulta.ListadoHogaresTransito;
import domain.Servicios.Notificadores.JavaMailApi;
import domain.Sistema.CentroDeRescate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class pruebaConsulta {

    public static void main(String[] args) {

      List<HogarTransito> listado = ServicioHogaresTransito.getInstance().solicitarTodosLosHogares();

      listado.forEach(hogar -> System.out.println(hogar.getNombre()));

      CentroDeRescate centroDeRescateDos = new CentroDeRescate(new Ubicacion(2.0,2.0));
      CentroDeRescate centroDeRescateUno = new CentroDeRescate(new Ubicacion(1.0,1.0));
      CentroDeRescate centroDeRescateTres = new CentroDeRescate(new Ubicacion(6.3,12.3));
      System.out.println(RepositorioCentroDeRescate.getInstance().getCentroDeRescateMasCercanoA(new Ubicacion(0.0,0.0)).getUbicacion().getLatitud());
    }
}
