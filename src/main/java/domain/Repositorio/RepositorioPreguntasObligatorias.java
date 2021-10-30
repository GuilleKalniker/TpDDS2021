package domain.Repositorio;

import domain.Exceptions.PreguntaNoObligatoriaException;
import domain.Pregunta.Pregunta;

import java.util.ArrayList;
import java.util.List;

public class RepositorioPreguntasObligatorias {

  private List<Pregunta> preguntas;
  private static final RepositorioPreguntasObligatorias INSTANCE = new RepositorioPreguntasObligatorias();

  public RepositorioPreguntasObligatorias() {
    this.preguntas = new ArrayList<>();
  }

  public static RepositorioPreguntasObligatorias getInstance() {
    return INSTANCE;
  }

  private void agregarPregunta(Pregunta pregunta) {/*
    if(!pregunta.esObligatoria())
      throw new PreguntaNoObligatoriaException("La pregunta no es obligatoria.");*/
    this.preguntas.add(pregunta);
  }

  public List<Pregunta> getPreguntas() {
    return preguntas;
  }

  public void eliminarPregunta(Pregunta pregunta) {
    this.preguntas.remove(pregunta);
  }
}
