package domain.Persona;

import domain.Publicacion.SolicitudPublicacion;
import domain.Repositorio.RepositorioUsuarios;
import domain.Sistema.CentroDeRescate;

public class Voluntario{

  private String nombreDeUsuario;
  private String contrasenia;

  private CentroDeRescate centroDeRescate;

  public Voluntario(String nombreDeUsuario, String contrasenia, CentroDeRescate centroDeRescate) {
    this.nombreDeUsuario = nombreDeUsuario;
    this.contrasenia = contrasenia;
    this.centroDeRescate = centroDeRescate;
  }

  public void aprobarSolicitud(SolicitudPublicacion solicitudPublicacion){
    centroDeRescate.aceptarSolicitud(solicitudPublicacion);
  }

  public void rechazarSolicitud(SolicitudPublicacion solicitudPublicacion){
    centroDeRescate.eliminarSolicitud(solicitudPublicacion);
  }

  public void registrarse() {
    RepositorioUsuarios.getInstance().registrarVoluntario(this);
  }

  public String getNombreUsuario() {
    return nombreDeUsuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }
}
