package domain.Persona;
import static org.mockito.Mockito.*;

import domain.Exceptions.TestCorrectoException;
import domain.Mascota.AtributosMascota.*;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Repositorio.RepositorioMascotas;
import domain.Repositorio.RepositorioUsuarios;
import domain.Servicios.ClasesParaLaConsulta.HogarTransito;
import domain.Servicios.HogaresTransitoService;
import domain.Servicios.Notificadores.JavaMailApi;
import domain.Servicios.Notificadores.Notificador;
import domain.Servicios.ServicioHogaresTransito;
import domain.Sistema.CentroDeRescate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RescatistaTest {

  @BeforeEach
  void init() {
    RepositorioCentroDeRescate repositorioCentroDeRescateMock = mock(RepositorioCentroDeRescate.class);
    RepositorioUsuarios repositorioUsuariosMock = mock(RepositorioUsuarios.class);
    RepositorioMascotas repositorioMascotasMock = mock(RepositorioMascotas.class);
    ServicioHogaresTransito servicioMock = mock(ServicioHogaresTransito.class);
    Notificador notificadorMock = mock(JavaMailApi.class);

    rescatistaPrueba.setRepositorioCentroDeRescate(repositorioCentroDeRescateMock);
    centroDeRescate.setRepositorioCentroDeRescate(repositorioCentroDeRescateMock);
    centroDeRescate.setRepositorioUsuarios(repositorioUsuariosMock);
    centroDeRescate.setRepositorioMascotas(repositorioMascotasMock);
    centroDeRescate.setServicioHogaresTransito(servicioMock);
    centroDeRescate.setNotificador(notificadorMock);
    duenioDePruebaUno.setRepositorioUsuarios(repositorioUsuariosMock);

    duenioDePruebaUno.registrarse();
    mascota.setID("123");
    duenioDePruebaUno.registrarMascota(mascota, centroDeRescate);
  }

  @Test
  public void seNotificoMascotaEncontradaConID() {
    doThrow(TestCorrectoException.class).when(centroDeRescate).notificar(duenioDePruebaUno, formulario);
    assertThrows(TestCorrectoException.class, () -> {rescatistaPrueba.notificarMascotaEncontradaConID(formulario, centroDeRescate);});
  }

  @Test
  public void devuelveHogarAdecuado() {
    List<HogarTransito> hogares = new ArrayList<>();
    hogares.add(hogarPrueba);
    doReturn(hogares).when(centroDeRescate).solicitarListaHogaresDeTransito();
    assert(rescatistaPrueba.buscarHogaresDeTransito(formulario, 500.0, centroDeRescate).contains(hogarPrueba));
  }

  public Rescatista rescatistaPrueba = new Rescatista();
  public FormularioMascotaPerdida formulario = new FormularioMascotaPerdida(new DatosPersonales("Pablo", "Perez", LocalDate.now(), TipoDocumento.DNI, 1, agregarContactos()), "Re loco", new ArrayList<Foto>(), new Ubicacion(0.0, 0.0), LocalDate.now(), "123");
  public Duenio duenioDePruebaUno = new Duenio("juancitoGomez123", "m15215atuTsdfjhbfdskjhsdfgjkhjsdrgafkljdfgrahkjgdfresterkpo",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("MCQueen", "Rodriguez", 1138475426, "elrayomcqueen@hotmail.com")));
  public MascotaRegistrada mascota = new MascotaRegistrada(TipoMascota.GATO, "Don Gato", "Gatokun", 46, Sexo.FEMENINO, "Lindo", new ArrayList<Foto>(), new ArrayList<Caracteristica>());
  public CentroDeRescate centroDeRescate = mock(CentroDeRescate.class);
  public HogarTransito hogarPrueba = new HogarTransito("321", "Papa", "Av Liao", 400.0, 400.0, "1231423", true, true, 50, 50, true, new ArrayList<String>());

  public List<Contacto> agregarContactos() {
    List<Contacto> contactos = new ArrayList<>();
    contactos.add(new Contacto("Pablo", "Perez", 5345684, "bjsdfgjd"));
    return contactos;
  }

  private ArrayList<Contacto> contactoDePrueba(String nombre, String apellido, Integer telefono, String email){
    ArrayList<Contacto> contactos = new ArrayList<>();
    Contacto contacto = new Contacto(nombre, apellido, telefono, email);
    contactos.add(contacto);
    return contactos;
  }
}
