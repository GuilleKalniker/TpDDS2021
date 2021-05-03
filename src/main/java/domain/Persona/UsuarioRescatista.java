package domain.Persona;

import domain.Mascota.EstadoMascotaEncontrada;
import domain.Mascota.Foto;
import domain.Mascota.Mascota;
import java.time.LocalDate;
import java.util.List;

public class UsuarioRescatista extends Usuario {
  private String direccion;

  public void notificarMascotaEncontrada(Mascota mascota, List<Foto> fotos, String descripcion, String lugar, LocalDate fecha) {
    EstadoMascotaEncontrada mascotaEncontrada = new EstadoMascotaEncontrada(mascota, fotos, descripcion, lugar, fecha);
    //centroDeRescate.avisoMascotaEncontrada(mascotaEncontrada);
  }
}
