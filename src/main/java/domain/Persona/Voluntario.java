package domain.Persona;

import domain.Publicacion.SolicitudPublicacion;
import domain.Repositorio.RepositorioUsuarios;
import domain.Sistema.CentroDeRescate;

public class Voluntario{

  private String nombreDeUsuario;
  private String contrasenia;

  private CentroDeRescate centroDeRescate;

  private RepositorioUsuarios repositorioUsuarios = RepositorioUsuarios.getInstance();

  public Voluntario(String nombreDeUsuario, String contrasenia, CentroDeRescate centroDeRescate) {
    this.nombreDeUsuario = nombreDeUsuario;
    this.contrasenia = contrasenia;
    this.centroDeRescate = centroDeRescate;
  }

  public void setRepositorioUsuarios(RepositorioUsuarios repositorioUsuarios) {
    this.repositorioUsuarios = repositorioUsuarios;
  }

  public void aprobarSolicitud(SolicitudPublicacion solicitudPublicacion){
    centroDeRescate.aceptarSolicitud(solicitudPublicacion);
  }

  public void rechazarSolicitud(SolicitudPublicacion solicitudPublicacion){
    centroDeRescate.eliminarSolicitud(solicitudPublicacion);
  }

  public void registrarse() {
    repositorioUsuarios.registrarVoluntario(this);
  }

  public String getNombreUsuario() {
    return nombreDeUsuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }
}
