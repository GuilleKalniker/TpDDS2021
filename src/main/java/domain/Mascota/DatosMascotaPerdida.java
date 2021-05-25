package domain.Mascota;

import domain.Mascota.AtributosMascota.Foto;
import domain.Persona.AtributosPersona.DatosPersonales;

import java.time.LocalDate;
import java.util.List;

public class DatosMascotaPerdida {
  private DatosPersonales datosRescastista;
  private String descripcionEstado;
  private List<Foto> fotosEncuentro;
  private String lugarEncuentro;
  private LocalDate fechaEncuentro;
  private String ID;


  //TODO: Manejamos el qr como id para no sobre diseñar :D atte:willy ;)
  public DatosMascotaPerdida(DatosPersonales rescatistaDeEncuentro, String descripcionEstado, List<Foto> fotosEncuentro, String lugarEncuentro, LocalDate fechaEncuentro, String ID) {
    this.datosRescastista = rescatistaDeEncuentro;
    this.descripcionEstado = descripcionEstado;
    this.fotosEncuentro = fotosEncuentro;
    this.lugarEncuentro = lugarEncuentro;
    this.fechaEncuentro = fechaEncuentro;
    //this.qrMascotaPerdida = qrMascotaPerdida;
    this.ID = ID;
  }

  public String getQrMascotaPerdida() {
    // hacer la lectura del qr y pasarlo como id
    return this.ID;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public LocalDate getFechaEncuentro() {
    return fechaEncuentro;
  }

}
