package domain.Publicacion;

import domain.Exceptions.PublicacionAdopcionInvalidaException;
import domain.Exceptions.SinContactosException;
import domain.Mascota.MascotaRegistrada;
import domain.Pregunta.Pregunta;
import domain.Sistema.CentroDeRescate;

import java.util.ArrayList;
import java.util.List;

public class PublicacionAdopcion {
  private MascotaRegistrada mascota;
  private List<Pregunta> preguntas;

  public PublicacionAdopcion(List<Pregunta> preguntasRespondidas, MascotaRegistrada mascota) {

    if(!preguntasRespondidas.stream().allMatch(pregunta -> pregunta.esValida()))
      throw new PublicacionAdopcionInvalidaException("Alguna pregunta obligatoria no fue respondida");

    this.preguntas = preguntasRespondidas;
    this.mascota = mascota;
  }

  public MascotaRegistrada getMascota() {
    return mascota;
  }
  public long getID(){
    return mascota.getID();
  }
  public List<Pregunta> getPreguntas() {
    return preguntas;
  }

  public Boolean matcheaConRespuesta(String respuesta) {
    return this.getPreguntas().stream()
        .anyMatch(pregunta ->
          pregunta.getRespuesta().equals(respuesta.toLowerCase())
        );
  }

  public Boolean matcheaConTodosFiltros(List<String> filtros) {
    return filtros.stream().allMatch(filtro -> this.matcheaConRespuesta(filtro));
  }

  public List<String> obtenerRespuestas() {
    List<String> respuestas = new ArrayList<>();
    this.getPreguntas().forEach(pregunta -> respuestas.add(pregunta.getRespuesta()));
    return respuestas;
  }
}

