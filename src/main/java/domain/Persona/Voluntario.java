package domain.Persona;

import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Repositorio.RepositorioUsuarios;
import domain.Sistema.CentroDeRescate;

import javax.persistence.*;

@Entity
@Table(name = "voluntario")
public class Voluntario{
  @Id
  @GeneratedValue
  private long id;

  private String nombreDeUsuario;
  private String contrasenia;

  @Transient
  private CentroDeRescate centroDeRescate;

  public Voluntario(String nombreDeUsuario, String contrasenia, CentroDeRescate centroDeRescate) {
    this.nombreDeUsuario = nombreDeUsuario;
    this.contrasenia = contrasenia;
    this.centroDeRescate = centroDeRescate;
  }

  public void aprobarSolicitud(PublicacionMascotaPerdida solicitud) {
    solicitud.aceptarseEnElCentro();
  }

  public void rechazarSolicitud(PublicacionMascotaPerdida solicitudPublicacion){
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
