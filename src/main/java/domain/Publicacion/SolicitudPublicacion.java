package domain.Publicacion;

import domain.Sistema.CentroDeRescate;

public class  SolicitudPublicacion {
  private PublicacionMascotaPerdida publicacion;

  public SolicitudPublicacion(PublicacionMascotaPerdida publicacion) {
    this.publicacion = publicacion;
  }

  public void aceptarEn(CentroDeRescate centro){
    centro.aceptarSolicitud(this);
  }

  public void rechazarEn(CentroDeRescate centro){
    centro.eliminarSolicitud(this);
  }

  public PublicacionMascotaPerdida getPublicacion(){
    return publicacion;
  }
}
