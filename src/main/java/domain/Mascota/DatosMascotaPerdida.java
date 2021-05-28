package domain.Mascota;

import domain.Mascota.AtributosMascota.Foto;
import domain.Mascota.AtributosMascota.TipoMascota;
import domain.Persona.AtributosPersona.DatosPersonales;

import java.time.LocalDate;
import java.util.List;

public class DatosMascotaPerdida {
  private TipoMascota tipo;
  private DatosPersonales datosRescastista;
  private String descripcionEstado;
  private List<Foto> fotosEncuentro;
  private String lugarEncuentro;
  private LocalDate fechaEncuentro;
  private String ID;

  public DatosMascotaPerdida(TipoMascota tipo, DatosPersonales datosRescastista, String descripcionEstado, List<Foto> fotosEncuentro, String lugarEncuentro, LocalDate fechaEncuentro, String ID) {
    this.tipo = tipo;
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

  public TipoMascota getTipo() {
    return tipo;
  }
}
