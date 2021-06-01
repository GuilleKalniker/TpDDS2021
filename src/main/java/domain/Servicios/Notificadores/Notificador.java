package domain.Servicios.Notificadores;

import domain.Mascota.FormularioMascotaPerdida;
import domain.Persona.Duenio;

//buscarle un mejor nombre
public interface Notificador {

  void notificar(Duenio duenio, FormularioMascotaPerdida formularioMascotaPerdida);

}
