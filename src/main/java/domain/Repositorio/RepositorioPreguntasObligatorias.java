package domain.Repositorio;

import domain.Pregunta.Pregunta;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPreguntasObligatorias {

  private static final RepositorioPreguntasObligatorias INSTANCE = new RepositorioPreguntasObligatorias();

  public static RepositorioPreguntasObligatorias getInstance() {
    return INSTANCE;
  }

  public void agregarPregunta(Pregunta pregunta) {
    AdapterJPA.persist(pregunta);
  }

  public List<Pregunta> getPreguntas() {
    TypedQuery<Pregunta> query = AdapterJPA.entityManager().createQuery("select p from Pregunta p", Pregunta.class);
    return query.getResultList();
  }

  public List<Pregunta> getPreguntasObligatorias() {
    //TODO implementar
    return new ArrayList<>();
  }
}
