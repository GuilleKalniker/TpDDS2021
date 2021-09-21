package domain.Mascota;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Persona.AtributosPersona.DatosPersonales;

import java.time.LocalDate;
import java.util.List;

public class FormularioMascotaPerdida {
  private DatosPersonales datosRescastista;
  private String descripcionEstado;
  private List<String> fotosEncuentro;
  private Ubicacion lugarEncuentro;
  private LocalDate fechaEncuentro;
  private String ID;

  public FormularioMascotaPerdida(DatosPersonales datosRescastista, String descripcionEstado, List<String> fotosEncuentro, Ubicacion lugarEncuentro, LocalDate fechaEncuentro, String ID) {
    this.datosRescastista = datosRescastista;
    this.descripcionEstado = descripcionEstado;
    this.fotosEncuentro = fotosEncuentro;
    this.lugarEncuentro = lugarEncuentro;
    this.fechaEncuentro = fechaEncuentro;
    this.ID = ID;
  }

  public DatosPersonales getDatosRescastista() {
    return getDatosRescastista();
  }

  public FormularioMascotaPerdida(DatosPersonales datosRescastista, String descripcionEstado, List<String> fotosEncuentro, Ubicacion lugarEncuentro, LocalDate fechaEncuentro) {
    this.datosRescastista = datosRescastista;
    this.descripcionEstado = descripcionEstado;
    this.fotosEncuentro = fotosEncuentro;
    this.lugarEncuentro = lugarEncuentro;
    this.fechaEncuentro = fechaEncuentro;
  }

  public String getIDMascotaPerdida() {
    return this.ID;
  }

  public LocalDate getFechaEncuentro() {
    return fechaEncuentro;
  }

  public Ubicacion getLugarEncuentro() {
    return lugarEncuentro;
  }

}
