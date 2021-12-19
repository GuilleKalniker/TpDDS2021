package domain.Publicacion;

import domain.Exceptions.PublicacionAdopcionInvalidaException;
import domain.Exceptions.SinContactosException;
import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Mascota.MascotaRegistrada;
import domain.Pregunta.Pregunta;
import domain.Pregunta.PreguntaResuelta;
import domain.Sistema.CentroDeRescate;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "publicacionAdopcion")
public class PublicacionAdopcion {

  @Id
  @GeneratedValue
  private long id;

  @OneToOne
  private MascotaRegistrada mascota;
  @Transient
  private List<PreguntaResuelta> preguntasResueltas;

  public PublicacionAdopcion(List<PreguntaResuelta> preguntasRespondidas, MascotaRegistrada mascota) {

    this.preguntasResueltas = preguntasRespondidas;
    this.mascota = mascota;
  }

  public PublicacionAdopcion() {

  }

  public MascotaRegistrada getMascota() {
    return mascota;
  }
  public long getID(){
    return mascota.getID();
  }
  public List<PreguntaResuelta> getPreguntas() {
    return preguntasResueltas;
  }

/*  public Boolean matcheaConRespuesta(String respuesta) {
    return this.getPreguntas().stream()
        .anyMatch(pregunta ->
            pregunta.getRespuesta().equals(respuesta.toLowerCase())
        );
  } */

  public Boolean matcheaConTodosFiltros(List<Caracteristica> filtros) {
    return mascota.getCaracteristicas().containsAll(filtros);
    //return filtros.stream().allMatch(filtro -> this.mascota.getCaracteristicas().contains(filtro));
  }

  public List<String> obtenerRespuestas() {
    List<String> respuestas = new ArrayList<>();
    this.getPreguntas().forEach(pregunta -> respuestas.add(pregunta.getRespuesta()));
    return respuestas;
  }
}

