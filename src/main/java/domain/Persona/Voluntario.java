package domain.Persona;

import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Repositorio.RepositorioUsuarios;
import domain.Sistema.CentroDeRescate;

import javax.persistence.*;

@Entity
@DiscriminatorValue("voluntario")
public class Voluntario extends Usuario {

  @Transient
  private CentroDeRescate centroDeRescate;

  public Voluntario(String nombreUsuario, String contrasenia, CentroDeRescate centroDeRescate) {
    super(nombreUsuario, contrasenia);
    this.centroDeRescate = centroDeRescate;
  }

  public void aprobarSolicitud(PublicacionMascotaPerdida solicitud) {
    solicitud.aceptarseEnElCentro();
  }

  public void rechazarSolicitud(PublicacionMascotaPerdida solicitudPublicacion){
    centroDeRescate.eliminarSolicitud(solicitudPublicacion);
  }

}
