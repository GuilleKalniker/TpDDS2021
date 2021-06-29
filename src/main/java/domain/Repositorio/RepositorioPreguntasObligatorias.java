package domain.Repositorio;

import domain.Pregunta.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPreguntasObligatorias {

  private List<Pregunta> preguntas;

  //TODO hacerlo singleton esta clase
  public RepositorioPreguntasObligatorias() {
    this.preguntas = new ArrayList<>();
  }

  private void agregarPregunta(Pregunta pregunta) {
    if(!pregunta.esObligatoria())
      throw new RuntimeException("la pregunta no es obligatoria");
    this.preguntas.add(pregunta);
  }

  public List<Pregunta> getPreguntas() {
    return preguntas;
  }

  public void eliminarPregunta(Pregunta pregunta) {
    this.preguntas.remove(pregunta);
  }
}
