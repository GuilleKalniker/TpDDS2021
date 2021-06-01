package domain.Persona;

import domain.Publicacion.SolicitudPublicacion;
import domain.Sistema.CentroDeRescate;

public class Voluntario extends Usuario{

  private CentroDeRescate centroDeRescate;

  public Voluntario(String nombreDeUsuario, String contrasenia, CentroDeRescate centroDeRescate) {
    super(nombreDeUsuario, contrasenia);
    this.centroDeRescate = centroDeRescate;
  }

  public void aprobarSolicitud(SolicitudPublicacion solicitudPublicacion){
    centroDeRescate.aceptarSolicitud(solicitudPublicacion);
  }

  public void rechazarSolicitud(SolicitudPublicacion solicitudPublicacion){
    centroDeRescate.eliminarSolicitud(solicitudPublicacion);
  }
}
