package domain.Servicios.Notificadores;

import domain.Mascota.DatosMascotaPerdida;
import domain.Persona.Duenio;

//buscarle un mejor nombre
public interface Notificador {

  void notificar(Duenio duenio, DatosMascotaPerdida datosMascotaPerdida);

}
