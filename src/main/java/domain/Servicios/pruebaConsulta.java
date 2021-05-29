package domain.Servicios;


import domain.Mascota.DatosMascotaPerdida;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import domain.Servicios.ClasesParaLaConsulta.HogarTransito;
import domain.Servicios.ClasesParaLaConsulta.ListadoHogaresTransito;
import domain.Servicios.Notificadores.JavaMailApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class pruebaConsulta {

    public static void main(String[] args) {

      List<HogarTransito> listado = ServicioHogaresTransito.getInstance().solicitarTodosLosHogares();

      listado.forEach(hogar -> System.out.println(hogar.getNombre()));
    }
}
