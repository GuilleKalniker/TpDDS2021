package domain.Mascota;

import java.time.LocalDate;
import java.util.List;

public class EstadoMascotaPerdida {
  private String descripcionEstado;
  private List<Foto> fotosEncuentro;
  private String lugarEncuentro;
  private LocalDate fechaEncuentro;
  private Integer qrMascotaPerdida;

  public EstadoMascotaPerdida(String descripcionEstado, List<Foto> fotosEncuentro, String lugarEncuentro, LocalDate fechaEncuentro, Integer qrMascotaPerdida) {
    this.descripcionEstado = descripcionEstado;
    this.fotosEncuentro = fotosEncuentro;
    this.lugarEncuentro = lugarEncuentro;
    this.fechaEncuentro = fechaEncuentro;
    this.qrMascotaPerdida = qrMascotaPerdida;
  }


}
