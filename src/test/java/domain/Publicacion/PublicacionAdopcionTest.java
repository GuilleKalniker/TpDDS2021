package domain.Publicacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.Exceptions.PublicacionAdopcionInvalidaException;
import domain.Pregunta.Pregunta;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PublicacionAdopcionTest {

  @Test
  public void sePuedeCrearUnaPublicacionDeAdopcionCorrectamente() {
    PublicacionAdopcion publicacion = new PublicacionAdopcion(listaPreguntas(false), "");
    assertEquals(publicacion.getClass(), PublicacionAdopcion.class);
  }

  @Test
  public void noSePuedeCrearUnaPublicacionSiAlMenosUnaDeLasPreguntasObligatoriasNoFueRespondida() {
    assertThrows(PublicacionAdopcionInvalidaException.class, () ->{ new PublicacionAdopcion(listaPreguntas(true), "");});
  }

  @Test
  public void sePuedenObtenerLasRespuestasDeLasPreguntasDeLaPublicacion() {
    PublicacionAdopcion publicacionAdopcion = new PublicacionAdopcion(listaDeDosPreguntas(), "");
    assertEquals(publicacionAdopcion.obtenerRespuestas().size(), 2);
  }

  @Test
  public void sePuedeSaberSiUnaPublicacionMatcheaConTodosLosFiltros() {
    PublicacionAdopcion publicacionAdopcion = new PublicacionAdopcion(listaDeDosPreguntas(), "");
    List<String> filtros = new ArrayList<>();
    filtros.add("Gato");
    filtros.add("Patio");
    assertTrue(publicacionAdopcion.matcheaConTodosFiltros(filtros));
  }

  public List<Pregunta> listaPreguntas(boolean esObligatoria) {
    List<Pregunta> preguntas = new ArrayList<>();

    List<String> listaDeRespuestas = new ArrayList<>();
    listaDeRespuestas.add("Perro");
    listaDeRespuestas.add("Gato");
    Pregunta pregunta = new Pregunta("Gato o perro?", listaDeRespuestas, esObligatoria);

    preguntas.add(pregunta);
    return preguntas;
  }

  public List<Pregunta> listaDeDosPreguntas() {
    List<Pregunta> preguntas = new ArrayList<>();

    List<String> listaDeRespuestas = new ArrayList<>();
    listaDeRespuestas.add("Perro");
    listaDeRespuestas.add("Gato");
    Pregunta preguntaUno = new Pregunta("Gato o perro?", listaDeRespuestas, true);
    preguntaUno.setRespuesta("Gato");

    List<String> listaDeRespuestasDos = new ArrayList<>();
    listaDeRespuestasDos.add("Patio");
    listaDeRespuestasDos.add("Sin Patio");
    Pregunta preguntaDos = new Pregunta("Patio?", listaDeRespuestasDos, true);
    preguntaDos.setRespuesta("Patio");

    preguntas.add(preguntaUno);
    preguntas.add(preguntaDos);

    return preguntas;
  }
}
