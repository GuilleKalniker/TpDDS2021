package domain.Servicios.Notificadores;

import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Servicios.Notificadores.Mail.Mensaje;
//TODO: Preguntar a Santi
public interface Notificador {

  void notificarRescatista(DatosPersonales datosRescatista, DatosPersonales datosDuenio);

  void notificar(Mensaje mensaje);
}
