package domain.Pregunta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.Exceptions.RespuestaInvalidaException;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PreguntaTest {
/*
  @Test
  public void unaPreguntaSeCreaCorrectamente() {
    Pregunta pregunta = new Pregunta("Gato o perro?", obtenerListaDePosiblesRespuestas(), false);
    assertEquals(pregunta.getClass(), Pregunta.class);
  }

  @Test
  public void sePuedeCrearUnaPreguntaObligatoriaCorrectamente() {
    Pregunta pregunta = new Pregunta("Gato o perro?", obtenerListaDePosiblesRespuestas(), true);
    assertTrue(pregunta.esObligatoria());
  }

  @Test
  public void seLePuedeSettearUnaRespuestaAUnaPregunta() {
    String respuesta  = "GaTO  ";
    Pregunta pregunta = new Pregunta("Gato o perro?", obtenerListaDePosiblesRespuestas(), true);
    pregunta.setRespuesta(respuesta);
    assertTrue(pregunta.getRespuesta().equals(respuesta.toLowerCase().trim()));
  }

  @Test
  public void noSePuedeCrearUnaPreguntaSinPosiblesRespuestas() {
    assertThrows(RespuestaInvalidaException.class, () -> { new Pregunta("Gato o perro?", new ArrayList<>(), true);});
  }

  @Test
  public void noSePuedeSettearUnaRespuestaVacia() {
    Pregunta pregunta = new Pregunta("Gato o perro?", obtenerListaDePosiblesRespuestas(), true);
    assertThrows(RespuestaInvalidaException.class, () -> { pregunta.setRespuesta(""); });
  }

  @Test
  public void noSePuedeSettearUnaRespuestaInvalida() {
    Pregunta pregunta = new Pregunta("Gato o perro?", obtenerListaDePosiblesRespuestas(), true);
    assertThrows(RespuestaInvalidaException.class, () -> { pregunta.setRespuesta("Oso"); });
  }

  @Test
  public void unaPreguntaObligatoriaSinRespuestaNoEsValida() {
    Pregunta pregunta = new Pregunta("Gato o perro?", obtenerListaDePosiblesRespuestas(), true);
    assertFalse(pregunta.esValida());
  }


  public List<String> obtenerListaDePosiblesRespuestas() {
    List<String> listaDeRespuestas = new ArrayList<>();
    listaDeRespuestas.add("Gato");
    listaDeRespuestas.add("Perro");
    return listaDeRespuestas;
  }
*/
}
