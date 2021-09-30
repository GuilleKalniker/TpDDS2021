package domain.Publicacion;

import domain.Persona.Duenio;
import domain.Sistema.CentroDeRescate;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "publicacionAdoptante")
public class PublicacionAdoptante {

  @Id
  @GeneratedValue
  private long id;

  @ManyToOne
  private Duenio duenioAsociado;
  @ElementCollection
  private List<String> preferencias = new ArrayList<>();

  public PublicacionAdoptante(Duenio duenioAsociado) {
    this.duenioAsociado = duenioAsociado;

  }
  public PublicacionAdoptante(){}

  public List<String> getPreferencias() {
    return preferencias;
  }

  public void agregarPreferencia(String preferencia) {
    preferencias.add(preferencia);
  }
  public void quitarPreferencia(String preferencia) {
    preferencias.remove(preferencia);
  }

  public void recibirSugerenciaAdopcion(PublicacionAdopcion publicacionAdopcion) {
    if(publicacionAdopcion == null) {
      duenioAsociado.notificar("Nueva sugerencia semanal", "No hay :(");
    }
    duenioAsociado.notificar("Nueva sugerencia semanal", "Aca tenes una mascota que podrias estar interesado en adoptar");
  }
  /*
  public void revisarSugerenciasRecibidas() {
    // TODO: Implementar, ciclaria por las sugerenciasAdopcion (GUI)
    // Si le gusto alguna, centroDeRescate.publicacionAdopcionMatcheada(duenioAsociado.getDatosPersonales(), publicacion)
  }

  public void revisarPublicacionesCentro() {
    centroDeRescate.getPublicacionesAdopcion();
    // TODO: Revisarlas
    // Si le gusto alguna, centroDeRescate.publicacionAdopcionMatcheada(duenioAsociado.getDatosPersonales(), publicacion)
  }
  */
}
