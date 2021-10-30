package domain.Publicacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.Exceptions.PublicacionAdopcionInvalidaException;
import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Mascota.AtributosMascota.Sexo;
import domain.Mascota.AtributosMascota.TipoMascota;
import domain.Mascota.MascotaRegistrada;
import domain.Pregunta.OpcionMultiple;
import domain.Pregunta.Pregunta;
import domain.Repositorio.AdapterJPA;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PublicacionAdopcionTest {
/*
  @BeforeEach
  public void setup() {
    AdapterJPA.beginTransaction();
  }

  @AfterEach
  public void teardown() {
    AdapterJPA.rollback();
  }

  public MascotaRegistrada crearMascota(String nombre) {
    ArrayList<String> fotos = new ArrayList<>();
    fotos.add("https://foto.com.ar");
    fotos.add("https://foto2.com.gov");
    return new MascotaRegistrada(TipoMascota.PERRO, nombre, nombre.substring(0, 2) + nombre.substring(0, 2).toLowerCase(),4, Sexo.FEMENINO, "Muerta de hambre", fotos, new ArrayList<Caracteristica>());
  }

  @Test
  public void sePuedeCrearUnaPublicacionDeAdopcionCorrectamente() {
    PublicacionAdopcion publicacion = new PublicacionAdopcion(listaPreguntas(false), crearMascota("pepita"));
    assertEquals(publicacion.getClass(), PublicacionAdopcion.class);
  }

  @Test
  public void noSePuedeCrearUnaPublicacionSiAlMenosUnaDeLasPreguntasObligatoriasNoFueRespondida() {
    assertThrows(PublicacionAdopcionInvalidaException.class, () ->{ new PublicacionAdopcion(listaPreguntas(true), crearMascota("pepita"));});
  }

  @Test
  public void sePuedenObtenerLasRespuestasDeLasPreguntasDeLaPublicacion() {
    PublicacionAdopcion publicacionAdopcion = new PublicacionAdopcion(listaDeDosPreguntas(), crearMascota("pepita"));
    assertEquals(publicacionAdopcion.obtenerRespuestas().size(), 2);
  }

  @Test
  public void sePuedeSaberSiUnaPublicacionMatcheaConTodosLosFiltros() {
    PublicacionAdopcion publicacionAdopcion = new PublicacionAdopcion(listaDeDosPreguntas(), crearMascota("pepita"));
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
    Pregunta preguntaUno = new OpcionMultiple("Gato o perro?", listaDeRespuestas);
    preguntaUno.setRespuesta("Gato   ");

    List<String> listaDeRespuestasDos = new ArrayList<>();
    listaDeRespuestasDos.add("Patio");
    listaDeRespuestasDos.add("Sin Patio");
    Pregunta preguntaDos = new Pregunta("Patio?", listaDeRespuestasDos);
    preguntaDos.setRespuesta("Patio");

    preguntas.add(preguntaUno);
    preguntas.add(preguntaDos);

    return preguntas;
  }

  */

}
