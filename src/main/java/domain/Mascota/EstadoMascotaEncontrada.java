package domain.Mascota;

import java.time.LocalDate;
import java.util.List;

public class EstadoMascotaEncontrada {
  private Mascota mascotaEncontrada;
  private List<Foto> fotosEncuentro;
  private String descripcionEncuentro;
  private String lugarEncuentro;
  private LocalDate fechaEncuentro;

  public EstadoMascotaEncontrada(Mascota mascotaEncontrada, List<Foto> fotosEncuentro, String descripcionEncuentro, String lugarEncuentro, LocalDate fechaEncuentro) {
    this.mascotaEncontrada = mascotaEncontrada;
    this.fotosEncuentro = fotosEncuentro;
    this.descripcionEncuentro = descripcionEncuentro;
    this.lugarEncuentro = lugarEncuentro;
    this.fechaEncuentro = fechaEncuentro;
  }

  public boolean encontradaHaceMenosDe10Dias() {
    return this.fechaEncuentro.isAfter(LocalDate.now().minusDays(10));
  }
}