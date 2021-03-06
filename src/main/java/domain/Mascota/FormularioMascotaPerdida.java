package domain.Mascota;

import Funciones.Utils;
import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Persona.AtributosPersona.DatosPersonales;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "formularioMascotaPerdida")
public class FormularioMascotaPerdida {
  @Id
  @GeneratedValue
  private long id;

  @Embedded
  private DatosPersonales datosRescastista;
  private String descripcionEstado;

  @ElementCollection (fetch = FetchType.EAGER)
  private List<String> fotosEncuentro;

  @Embedded
  private Ubicacion lugarEncuentro;

  @Column(columnDefinition = "DATE")
  private LocalDate fechaEncuentro;

  // TODO: Esto puede ser NULL
  private long mascotaID;

  public FormularioMascotaPerdida(DatosPersonales datosRescastista, String descripcionEstado, List<String> fotosEncuentro, Ubicacion lugarEncuentro, LocalDate fechaEncuentro, long mascotaID) {
    this.datosRescastista = datosRescastista;
    this.descripcionEstado = descripcionEstado;
    this.fotosEncuentro = fotosEncuentro;
    this.lugarEncuentro = lugarEncuentro;
    this.fechaEncuentro = fechaEncuentro;
    this.mascotaID = mascotaID;
  }

  public FormularioMascotaPerdida() {}

  public FormularioMascotaPerdida(DatosPersonales datosRescastista, String descripcionEstado, List<String> fotosEncuentro, Ubicacion lugarEncuentro, LocalDate fechaEncuentro) {
    this.datosRescastista = datosRescastista;
    this.descripcionEstado = descripcionEstado;
    this.fotosEncuentro = fotosEncuentro;
    this.lugarEncuentro = lugarEncuentro;
    this.fechaEncuentro = fechaEncuentro;
  }

  public DatosPersonales getDatosRescastista() {
    return datosRescastista;
  }

  public long getIDMascotaPerdida() {
    return this.mascotaID;
  }

  public String getFechaEncuentro() {
    return Utils.localDateToString(fechaEncuentro);
  }

  public Ubicacion getLugarEncuentro() {
    return lugarEncuentro;
  }

  public String getFoto() {
    return fotosEncuentro.get(0);
  }

  public String getDescripcion() {
    return descripcionEstado;
  }
}
