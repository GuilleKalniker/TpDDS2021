package domain.Sistema;

import domain.Exceptions.RespuestaInvalidaException;
import domain.Mascota.*;
import domain.Mascota.AtributosMascota.Sexo;
import domain.Mascota.AtributosMascota.TipoMascota;
import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Persona.*;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Pregunta.Pregunta;
import domain.Publicacion.PublicacionAdopcion;
import domain.Publicacion.PublicacionAdoptante;
import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Repositorio.RepositorioMascotas;
import domain.Servicios.Notificadores.Notificador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CentroDeRescateTest {
/*
  Notificador notificadorMock = mock(Notificador.class);

  @BeforeEach
  void init() {
    //TODO borrar la base de datos de prueba ¿?

    centro.setNotificador(notificadorMock);
    List<Notificador> notificadores = new ArrayList<>();
    notificadores.add(notificadorMock);
    duenioDePruebaUno.setNotificadores(notificadores);
    AdapterJPA.beginTransaction();
  }

  @AfterEach
  public void teardown() {
    AdapterJPA.rollback();
  }


  @Test
  public void obtenemosUnaListaDeIDsAcordeALasMascotasRegistradas(){
    RepositorioMascotas.getInstance().registrarMascota(pepita);
    RepositorioMascotas.getInstance().registrarMascota(chinchulin);

    assertTrue(this.centro.existeMascota(pepita.getID()));
    assertTrue(this.centro.existeMascota(chinchulin.getID()));
  }

  @Test
  public void seEncuentraElDueñoAPartirDeUnaMascotaRegistrada(){
    Duenio duenioDePrueba = new Duenio("juan4321", "guilloteelmaskpox2",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("Jesus", "DeNazareth"), "nose 123"));
    duenioDePrueba.registrarse();
    duenioDePrueba.registrarMascota(pepita);
    assertTrue(this.centro.buscarDuenioApartirIDMascota(pepita.getID()).getNombreUsuario().equals(duenioDePrueba.getNombreUsuario()));
  }

  @Test
  public void seEncuentraElCentroMasCercanoAUnaUbicacion() {
    CentroDeRescate centroLejano = new CentroDeRescate(new Ubicacion(10.0, 0.0));
    CentroDeRescate centroCercano = new CentroDeRescate(new Ubicacion(1.0, 1.0));
    CentroDeRescate centroLejano1 = new CentroDeRescate(new Ubicacion(4.0, 1.0));
    CentroDeRescate centroLejano2 = new CentroDeRescate(new Ubicacion(1.0, 3.0));
    CentroDeRescate centroLejano3 = new CentroDeRescate(new Ubicacion(1.5, 1.5));

    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(centroLejano);
    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(centroCercano);
    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(centroLejano1);
    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(centroLejano2);
    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(centroLejano3);

    Assertions.assertEquals(centroCercano, RepositorioCentroDeRescate.getInstance().getCentroDeRescateMasCercanoA(new Ubicacion(0.0, 0.0)));
  }

  @Test
  public void seEnviaNotificacionSemanal() {
    // Necesito agregar un interesado en adoptar, o sea, una publicacion adoptante

    PublicacionAdoptante interesado = new PublicacionAdoptante(duenioDePruebaUno);
    centro.nuevoInteresadoEnAdoptar(interesado);

    List<Pregunta> preguntas = new ArrayList<>();
    preguntas.add(preguntaValida);

    centro.generarPublicacionAdopcion(preguntas, pepita);

    centro.notificacionSemanal();

    verify(notificadorMock).notificar(any());
  }

  @Test
  public void seAvisaSiSeEncontroMascota() {
    // Necesito unos datos de duenio y una publicacion mascota perdida

    DatosPersonales datosDuenio = new DatosPersonales("Pedro", "Martinez", LocalDate.now(), TipoDocumento.DNI, 20123457, contactoDePrueba("Jesus", "DeNazareth"), "nose 123");
    PublicacionMascotaPerdida publicacionMascotaPerdida = new PublicacionMascotaPerdida(new FormularioMascotaPerdida(datosRescastista, "?", new ArrayList<String>(), new Ubicacion(5.0, 5.0), LocalDate.now(), 1));

    centro.publicacionMascotaPerdidaMatcheada(datosDuenio, publicacionMascotaPerdida);

    verify(notificadorMock).notificarRescatista(datosRescastista, datosDuenio);
  }

  @Test
  public void seAvisaSiAlguienDeseaAdoptarMascota() {
    // Necesito datos de algun adoptante y una publicacion de adopcion

    List<Pregunta> preguntas = new ArrayList<>();
    preguntas.add(preguntaValida);

    // TODO: FALTA AGREGAR COMPORTAMIENTO CON SQL EN REPO, ADEMAS DE ASOCIAR EL ID
    // TODO: Hace falta registrar?
    duenioDePruebaUno.registrarMascota(pepita);


    PublicacionAdopcion publicacionAdopcion = new PublicacionAdopcion(preguntas, pepita);
    centro.generarPublicacionAdopcion(preguntas,pepita);
    centro.publicacionAdopcionMatcheada(datosRescastista, publicacionAdopcion);

    verify(notificadorMock).notificar(any());
  }

  // Temporales hasta cambio de preguntas
  @Test
  public void seAgregaPreguntaValida() {
    centro.agregarPregunta(preguntaValida);

    assertEquals(centro.getPreguntasDeAdopcion().size(), 1, 0);
  }


  @Test
  public void seQuitaPregunta() {
    centro.agregarPregunta(preguntaValida);
    centro.quitarPregunta(preguntaValida);

    assertEquals(centro.getPreguntasDeAdopcion().size(), 0, 0);
  }
*/

  /** Funciones y definiciones **/ /*

  private CentroDeRescate centro = new CentroDeRescate(new Ubicacion(2.2,2.2));

  private Duenio duenioDePruebaUno = new Duenio("juanito123", "guilloteelmaskpo",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("Jesus", "DeNazareth"), "nose 123"));

  private Duenio duenioDePruebaDos = new Duenio("pedritokpo1", "willirex777",new DatosPersonales("Pedro", "Martinez", LocalDate.now(), TipoDocumento.DNI, 20123457, contactoDePrueba("Jesus", "DeNazareth"), "nose 123"));

  private DatosPersonales datosRescastista = new DatosPersonales("Guillermo", "Francella", LocalDate.now(), TipoDocumento.DNI, 14235653, contactoDePrueba("Jesus", "DeNazareth"), "nose 123");

  private FormularioMascotaPerdida pepitaPerdida = new FormularioMascotaPerdida(datosRescastista, "Bastante saludable", new ArrayList<String>(), new Ubicacion(2.2,2.2), LocalDate.now(), 123123123);

  private FormularioMascotaPerdida chinchulinPerdido = new FormularioMascotaPerdida(datosRescastista, "Bastante saludable", new ArrayList<String>(), new Ubicacion(2.2,2.2), LocalDate.now().minusDays(11), 212);

  private MascotaRegistrada pepita = new MascotaRegistrada(TipoMascota.PERRO, "Pepita", "Pepisauria", 9, Sexo.FEMENINO, "Perra corgi muy linda", new ArrayList<String>(),new ArrayList<>());

  private MascotaRegistrada chinchulin = new MascotaRegistrada(TipoMascota.PERRO, "Chinchulin", "Asadito", 9, Sexo.MASCULINO, "Perro shiba muy lindo", new ArrayList<String>(),new ArrayList<>());

  private List<String> respuestas() {
    List<String> respuestas = new ArrayList<>();
    respuestas.add("Si");
    respuestas.add("No");
    respuestas.add("Mas o menos");

    return respuestas;
  }

  private Pregunta preguntaValida = new Pregunta("Es lindo?", respuestas(), false);

  private Pregunta preguntaInvalida = new Pregunta("Es lindo?", new ArrayList<String>(), false);


  void registrarleMascotaADuenio(Duenio unDuenio) {
    MascotaRegistrada mascota = new MascotaRegistrada(TipoMascota.PERRO, "Pepito", "Pepisaurio", 10, Sexo.MASCULINO, "Perro salchicha muy lindo", new ArrayList<String>(), new ArrayList<>());
    unDuenio.registrarMascota(mascota);
  }

  private ArrayList<Contacto> contactoDePrueba(String nombre, String apellido){
    ArrayList<Contacto> contactos = new ArrayList<>();
    Contacto contacto = new Contacto(nombre, apellido, 1145686431 , nombre.charAt(0) + apellido.toLowerCase() + "@frba.utn.edu.ar");
    contactos.add(contacto);
    return contactos;
  }

  */
}
