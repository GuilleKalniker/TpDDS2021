package domain.Publicacion;

import domain.Exceptions.PublicacionAdopcionInvalidaException;
import domain.Exceptions.SinContactosException;
import domain.Pregunta.Pregunta;
import domain.Sistema.CentroDeRescate;

import java.util.ArrayList;
import java.util.List;

public class PublicacionAdopcion {
  private String id;
  private List<Pregunta> preguntas;

  public PublicacionAdopcion(List<Pregunta> preguntasRespondidas, String id) {

    if(!preguntasRespondidas.stream().allMatch(pregunta -> pregunta.esValida()))
      throw new PublicacionAdopcionInvalidaException("Alguna pregunta obligatoria no fue respondida");

    this.preguntas = preguntasRespondidas;
    this.id = id;
  }

  public String getId() {
    return id;
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

