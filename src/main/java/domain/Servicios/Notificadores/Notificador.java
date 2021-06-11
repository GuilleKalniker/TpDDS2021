package domain.Servicios.Notificadores;

import domain.Mascota.FormularioMascotaPerdida;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.Duenio;
import domain.Persona.Rescatista;
import domain.Publicacion.PublicacionMascotaPerdida;

//buscarle un mejor nombre
public interface Notificador {

  void notificarDuenio(Duenio duenio, FormularioMascotaPerdida formularioMascotaPerdida);

  void notificarRescatista(DatosPersonales datosRescatista, DatosPersonales datosDuenio);
}
