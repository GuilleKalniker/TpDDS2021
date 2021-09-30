package domain.Persona;

import domain.Exceptions.ContraseniaInvalidaException;
import domain.Mascota.AtributosMascota.*;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Repositorio.AdapterJPA;
import domain.Servicios.Notificadores.Notificador;
import domain.Servicios.ServicioHogaresTransito;
import domain.Sistema.CentroDeRescate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;

public class DuenioTest {

  @BeforeEach
  void init() {
    AdapterJPA.beginTransaction();
    ServicioHogaresTransito servicioMock = mock(ServicioHogaresTransito.class);
    Notificador notificadorMock = mock(Notificador.class);

    centroDeRescateDePrueba.setServicioHogaresTransito(servicioMock);
    centroDeRescateDePrueba.setNotificador(notificadorMock);
  }
  @AfterEach
  void deinit(){
    AdapterJPA.rollback();
  }

  @Test
  public void duenioInicialNoTieneMascotas() {
    assert(duenioDePruebaUno.getMascotas().isEmpty());
  }

  @Test
  public void alAgregarleUnaMascotaAUnDuenioSuListaPoseeUnaMascota() {
    registrarleMascotaADuenio(duenioDePruebaUno);
    assertEquals(duenioDePruebaUno.getMascotas().size(), 1, 0);
  }

  @Test
  public void unDuenioPuedeRegistrarMasDeUnaMascota() {
    registrarleMascotaADuenio(duenioDePruebaUno);
    registrarleOtraMascotaADuenio(duenioDePruebaUno);
    assertEquals(duenioDePruebaUno.getMascotas().size(), 2,0);
  }

  @Test
  public void unDuenioLanzaExcepcionCuandoNoTieneContactos() {
    assertThrows(RuntimeException.class,
        () -> { new Duenio("robertoLagarto", "robertito5432",
            new DatosPersonales("Roberto", "Lagarto", LocalDate.now(), TipoDocumento.DNI, 12345342, new ArrayList<Contacto>(), "nose 123"));
    });
  }

  @Test
  public void unDuenioSeCreaBien() {
    assertEquals(duenioDePruebaUno.getClass(), Duenio.class);
  }

  @Test
  public void unDuenioNoSePuedeCrearConContraseniasYUsuarioInvalido() {
    Assertions.assertThrows(ContraseniaInvalidaException.class, () -> {Duenio duenio = new Duenio("moreeee", "12345",
        new DatosPersonales("Morena", "Sisro", LocalDate.now(), TipoDocumento.DNI, 123456, contactoDePrueba("MCQueen", "Rodriguez", 1138475426, "elrayomcqueen@hotmail.com"), "nose 123"));;});
  }

  @Test
  public void seGeneranLasPublicacionesDeAdopcion() {
    registrarleMascotaADuenio(duenioDePruebaUno);
    MascotaRegistrada mascota = duenioDePruebaUno.getMascotas().get(0);
    duenioDePruebaUno.darEnAdopcionA(mascota, centroDeRescateDePrueba);

    assertEquals(1, centroDeRescateDePrueba.getPublicacionesAdopcion().size());
  }

  @Test
  public void seAnotaComoAdoptante() {
    duenioDePruebaUno.mostrarIntencionDeAdopcion(centroDeRescateDePrueba);

    assertEquals(centroDeRescateDePrueba.getInteresadosEnAdoptar().size(), 1, 0);
  }

  @Test
  public void tieneUnaMascota() {

    MascotaRegistrada pepito = new MascotaRegistrada(TipoMascota.PERRO, "Pepito", "Pepisaurio", 10, Sexo.MASCULINO, "Perro salchicha muy lindo", new ArrayList<String>(),new ArrayList<>());

    duenioDePruebaUno.registrarMascota(pepito);

    long idPepito = pepito.getID();

    assert(duenioDePruebaUno.tieneA(idPepito));
  }

  //TODO ver como testear luego de quitar la excepcion
  /*
  @Test
  public void noRecibeNingunaNotificacionAlNoEspecificarPreferencias() {
    duenioDePruebaUno.mostrarIntencionDeAdopcion(centroDeRescateDePrueba);
    assertThrows(NoHayPublicacionAptaException.class, () -> {centroDeRescateDePrueba.notificacionSemanal();});
  }
  */

  /** FUNCIONES **/

  public Duenio duenioDePruebaUno = new Duenio("juancitoGomez", "matuTesterkpo",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("MCQueen", "Rodriguez", 1138475426, "elrayomcqueen@hotmail.com"), "nose 123"));

  public CentroDeRescate centroDeRescateDePrueba = new CentroDeRescate(new Ubicacion(0.0, 0.0));

  public void registrarleMascotaADuenio(Duenio unDuenio) {
    MascotaRegistrada mascota = new MascotaRegistrada(TipoMascota.PERRO, "Pepito", "Pepisaurio", 10, Sexo.MASCULINO, "Perro salchicha muy lindo", new ArrayList<String>(),new ArrayList<>());
    unDuenio.registrarMascota(mascota);
  }

  public void registrarleOtraMascotaADuenio(Duenio unDuenio) {
    MascotaRegistrada mascota = new MascotaRegistrada(TipoMascota.PERRO, "Jorgito", "Alfajor", 10, Sexo.MASCULINO, "Perro labrador muy lindo", new ArrayList<String>(), new ArrayList<>());
    unDuenio.registrarMascota(mascota);
  }

  private ArrayList<Contacto> contactoDePrueba(String nombre, String apellido, Integer telefono, String email){
    ArrayList<Contacto> contactos = new ArrayList<>();
    Contacto contacto = new Contacto(nombre, apellido, telefono, email);
    contactos.add(contacto);
    return contactos;
  }
}