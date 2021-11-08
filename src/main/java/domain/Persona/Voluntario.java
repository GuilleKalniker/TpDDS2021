package domain.Persona;

import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Repositorio.RepositorioUsuarios;
import domain.Sistema.CentroDeRescate;

import javax.persistence.*;

@Entity
@DiscriminatorValue("voluntario")
public class Voluntario extends Usuario {

  public Voluntario(String nombreUsuario, String contrasenia) {
    super(nombreUsuario, contrasenia);
  }

  public Voluntario() {}

  public void aprobarSolicitud(PublicacionMascotaPerdida solicitud) {
    solicitud.aceptarseEnElCentro();
  }

  public void rechazarSolicitud(PublicacionMascotaPerdida solicitudPublicacion){
    solicitudPublicacion.eliminarseEnElCentro();
  }

  public Boolean getEsVoluntario() {
    return true;
  }

}
