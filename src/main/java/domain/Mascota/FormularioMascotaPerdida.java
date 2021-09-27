package domain.Mascota;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Persona.AtributosPersona.DatosPersonales;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;
import java.util.List;

@Embeddable
public class FormularioMascotaPerdida {
  @Embedded
  private DatosPersonales datosRescastista;
  private String descripcionEstado;

  @ElementCollection
  private List<String> fotosEncuentro;

  @Embedded
  private Ubicacion lugarEncuentro;

  @Column(columnDefinition = "DATE")
  private LocalDate fechaEncuentro;
  private String mascotaID;

  public FormularioMascotaPerdida(DatosPersonales datosRescastista, String descripcionEstado, List<String> fotosEncuentro, Ubicacion lugarEncuentro, LocalDate fechaEncuentro, String mascotaID) {
    this.datosRescastista = datosRescastista;
    this.descripcionEstado = descripcionEstado;
    this.fotosEncuentro = fotosEncuentro;
    this.lugarEncuentro = lugarEncuentro;
    this.fechaEncuentro = fechaEncuentro;
    this.mascotaID = mascotaID;
  }

  public FormularioMascotaPerdida() {}

  public DatosPersonales getDatosRescastista() {
    return datosRescastista;
  }

  public FormularioMascotaPerdida(DatosPersonales datosRescastista, String descripcionEstado, List<String> fotosEncuentro, Ubicacion lugarEncuentro, LocalDate fechaEncuentro) {
    this.datosRescastista = datosRescastista;
    this.descripcionEstado = descripcionEstado;
    this.fotosEncuentro = fotosEncuentro;
    this.lugarEncuentro = lugarEncuentro;
    this.fechaEncuentro = fechaEncuentro;
  }

  public String getIDMascotaPerdida() {
    return this.mascotaID;
  }

  public LocalDate getFechaEncuentro() {
    return fechaEncuentro;
  }

  public Ubicacion getLugarEncuentro() {
    return lugarEncuentro;
  }

}
