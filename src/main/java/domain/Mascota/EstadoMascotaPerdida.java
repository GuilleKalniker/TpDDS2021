package domain.Mascota;

import domain.Persona.Rescatista;
import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.util.List;

public class EstadoMascotaPerdida {
  private Rescatista rescatistaDeEncuentro;
  private String descripcionEstado;
  private List<Foto> fotosEncuentro;
  private String lugarEncuentro;
  private LocalDate fechaEncuentro;
  private Integer qrMascotaPerdida;

  public EstadoMascotaPerdida(Rescatista rescatistaDeEncuentro, String descripcionEstado, List<Foto> fotosEncuentro, String lugarEncuentro, LocalDate fechaEncuentro, Integer qrMascotaPerdida) {
    this.rescatistaDeEncuentro = rescatistaDeEncuentro;
    this.descripcionEstado = descripcionEstado;
    this.fotosEncuentro = fotosEncuentro;
    this.lugarEncuentro = lugarEncuentro;
    this.fechaEncuentro = fechaEncuentro;
    this.qrMascotaPerdida = qrMascotaPerdida;
  }

  public Integer getQrMascotaPerdida() {
    return qrMascotaPerdida;
  }

  public LocalDate getFechaEncuentro() {
    return fechaEncuentro;
  }

}
