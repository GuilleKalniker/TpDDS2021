package domain.Persona;

import domain.Exceptions.CentroInvalidoException;
import domain.Publicacion.SolicitudPublicacion;
import domain.Repositorio.RepositorioUsuarios;
import domain.Sistema.CentroDeRescate;

import java.util.List;

public class Voluntario{

  private String nombreDeUsuario;
  private String contrasenia;

  private CentroDeRescate centroDeRescate;

  public Voluntario(String nombreDeUsuario, String contrasenia, CentroDeRescate centroDeRescate) {
    this.nombreDeUsuario = nombreDeUsuario;
    this.contrasenia = contrasenia;
    this.centroDeRescate = centroDeRescate;
  }

  public void aprobarSolicitud(SolicitudPublicacion solicitudPublicacion) {
    validarCentro(solicitudPublicacion);
    solicitudPublicacion.aceptarEn(centroDeRescate);
  }

  public void rechazarSolicitud(SolicitudPublicacion solicitudPublicacion){
    validarCentro(solicitudPublicacion);
    centroDeRescate.eliminarSolicitud(solicitudPublicacion);
  }

  public void validarCentro(SolicitudPublicacion solicitud) {
    if (!centroDeRescate.getSolicitudesPublicacion().contains(solicitud)) {
      throw new CentroInvalidoException();
    }
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
