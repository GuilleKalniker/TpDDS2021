package domain.Mascota;

import domain.Mascota.AtributosMascota.Foto;
import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Persona.AtributosPersona.DatosPersonales;

import java.time.LocalDate;
import java.util.List;

public class DatosMascotaPerdida {
  private DatosPersonales datosRescastista;
  private String descripcionEstado;
  private List<Foto> fotosEncuentro;
  private Ubicacion lugarEncuentro;
  private LocalDate fechaEncuentro;
  private String ID;

  public DatosMascotaPerdida(DatosPersonales datosRescastista, String descripcionEstado, List<Foto> fotosEncuentro, Ubicacion lugarEncuentro, LocalDate fechaEncuentro, String ID) {
    this.datosRescastista = datosRescastista;
    this.descripcionEstado = descripcionEstado;
    this.fotosEncuentro = fotosEncuentro;
    this.lugarEncuentro = lugarEncuentro;
    this.fechaEncuentro = fechaEncuentro;
    this.ID = ID; //TODO recibimos el qr por el contructor y lo leemos para obtener el ID
  }

  public String getIDMascotaPerdida() {
    return this.ID;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public LocalDate getFechaEncuentro() {
    return fechaEncuentro;
  }

  public Ubicacion getLugarEncuentro() {
    return lugarEncuentro;
  }
}
