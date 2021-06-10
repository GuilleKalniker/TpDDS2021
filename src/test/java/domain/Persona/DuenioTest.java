package domain.Persona;

import domain.Exceptions.ContraseniaInvalidaException;
import domain.Mascota.AtributosMascota.*;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Repositorio.RepositorioMascotas;
import domain.Repositorio.RepositorioUsuarios;
import domain.Servicios.Notificadores.JavaMailApi;
import domain.Servicios.Notificadores.Notificador;
import domain.Servicios.ServicioHogaresTransito;
import domain.Sistema.CentroDeRescate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.ArrayList;

public class DuenioTest {

  @BeforeEach
  void init() {
    ServicioHogaresTransito servicioMock = mock(ServicioHogaresTransito.class);
    Notificador notificadorMock = mock(Notificador.class);

    centroDeRescateDePrueba.setServicioHogaresTransito(servicioMock);
    centroDeRescateDePrueba.setNotificador(notificadorMock);
  }

  @Test
  public void duenioInicialNoTieneMascotas() {
    assert(duenioDePruebaUno.getMascotasID().isEmpty());
  }

  @Test
  public void alAgregarleUnaMascotaAUnDuenioSuListaPoseeUnaMascota() {
    registrarleMascotaADuenio(duenioDePruebaUno);
    assertEquals(duenioDePruebaUno.getMascotasID().size(), 1, 0);
  }

  @Test
  public void unDuenioPuedeRegistrarMasDeUnaMascota() {
    registrarleMascotaADuenio(duenioDePruebaUno);
    registrarleOtraMascotaADuenio(duenioDePruebaUno);
    assertEquals(duenioDePruebaUno.getMascotasID().size(), 2,0);
  }

  @Test
  public void unDuenioLanzaExcepcionCuandoNoTieneContactos() {
    assertThrows(RuntimeException.class,
        () -> { new Duenio("robertoLagarto", "robertito5432",
            new DatosPersonales("Roberto", "Lagarto", LocalDate.now(), TipoDocumento.DNI, 12345342, new ArrayList<Contacto>()));
    });
  }

  @Test
  public void unDuenioSeCreaBien() {
    assertEquals(duenioDePruebaUno.getClass(), Duenio.class);
  }

  @Test
  public void unDuenioNoSePuedeCrearConContraseniasYUsuarioInvalido() {
    Duenio duenio = new Duenio("moreeee", "12345",
        new DatosPersonales("morena", "Sisro", LocalDate.now(), TipoDocumento.DNI, 123456, contactoDePrueba("MCQueen", "Rodriguez", 1138475426, "elrayomcqueen@hotmail.com")));
    Assertions.assertThrows(ContraseniaInvalidaException.class, () -> {duenio.registrarse();});
  }

  /** FUNCIONES **/

  public Duenio duenioDePruebaUno = new Duenio("juancitoGomez", "matuTesterkpo",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("MCQueen", "Rodriguez", 1138475426, "elrayomcqueen@hotmail.com")));

  public CentroDeRescate centroDeRescateDePrueba = new CentroDeRescate(new Ubicacion(0.0, 0.0));

  public void registrarleMascotaADuenio(Duenio unDuenio) {
    MascotaRegistrada mascota = new MascotaRegistrada(TipoMascota.PERRO, "Pepito", "Pepisaurio", 10, Sexo.MASCULINO, "Perro salchicha muy lindo", new ArrayList<Foto>(),new ArrayList<>());
    unDuenio.registrarMascota(mascota, centroDeRescateDePrueba);
  }

  public void registrarleOtraMascotaADuenio(Duenio unDuenio) {
    MascotaRegistrada mascota = new MascotaRegistrada(TipoMascota.PERRO, "Jorgito", "Alfajor", 10, Sexo.MASCULINO, "Perro labrador muy lindo", new ArrayList<Foto>(), new ArrayList<>());
    unDuenio.registrarMascota(mascota, centroDeRescateDePrueba);
  }

  private ArrayList<Contacto> contactoDePrueba(String nombre, String apellido, Integer telefono, String email){
    ArrayList<Contacto> contactos = new ArrayList<>();
    Contacto contacto = new Contacto(nombre, apellido, telefono, email);
    contactos.add(contacto);
    return contactos;
  }
}