package domain.Publicacion;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Persona.Duenio;
import domain.Sistema.CentroDeRescate;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "publicacionAdoptante")
public class PublicacionAdoptante {

  @Id
  @GeneratedValue
  private long id;

  @ManyToOne
  private Duenio duenioAsociado;

  @ManyToMany
  private List<Caracteristica> preferencias = new ArrayList<>();

  public PublicacionAdoptante(Duenio duenioAsociado) {
    this.duenioAsociado = duenioAsociado;

  }
  public PublicacionAdoptante(){}

  public List<Caracteristica> getPreferencias() {
    return preferencias;
  }

  public void agregarPreferencia(Caracteristica preferencia) {
    preferencias.add(preferencia);
  }
  public void quitarPreferencia(Caracteristica preferencia) {
    preferencias.remove(preferencia);
  }

  public void recibirSugerenciaAdopcion(PublicacionAdopcion publicacionAdopcion) {
    if(publicacionAdopcion == null) {
      duenioAsociado.notificar("Nueva sugerencia semanal", "No hay :(");
    }
    duenioAsociado.notificar("Nueva sugerencia semanal", "Aca tenes una mascota que podrias estar interesado en adoptar");
  }
}
