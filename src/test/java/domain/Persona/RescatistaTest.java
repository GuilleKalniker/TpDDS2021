package domain.Persona;
import static org.mockito.Mockito.*;


import domain.Mascota.AtributosMascota.*;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Repositorio.*;
import domain.Servicios.HogarTransitoAdaptado;
import domain.Servicios.Notificadores.Notificador;
import domain.Servicios.ServicioHogaresTransito;
import domain.Sistema.CentroDeRescate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RescatistaTest {

  private ServicioHogaresTransito servicioHogaresMock;
  private Notificador notificadorMock;

  private Rescatista rescatistaPrueba;
  private FormularioMascotaPerdida formulario;
  private Duenio duenioDePruebaUno;
  private MascotaRegistrada mascota;
  private CentroDeRescate centroDeRescate;
  private HogarTransitoAdaptado hogarAdecuado;
  private HogarTransitoAdaptado hogarInadecuado;
  private List<TipoMascota> mascotasPermitidas = new ArrayList<TipoMascota>();


  @BeforeEach
  void init() {
    servicioHogaresMock = mock(ServicioHogaresTransito.class);
    notificadorMock = mock(Notificador.class);

    centroDeRescate = new CentroDeRescate(new Ubicacion(100.0, 100.0));
    centroDeRescate.setServicioHogaresTransito(servicioHogaresMock);
    centroDeRescate.setNotificador(notificadorMock);

    RepositorioCaracteristicas.getInstance().getCaracteristicasVigentes().clear();
    RepositorioUsuarios.getInstance().getDueniosRegistrados().clear();
    RepositorioUsuarios.getInstance().getAdministradoresRegistrados().clear();
    RepositorioUsuarios.getInstance().getVoluntariosRegistrados().clear();
    RepositorioCentroDeRescate.getInstance().getCentrosDeRescateRegistrados().clear();
    RepositorioMascotas.getInstance().getMascotasRegistradas().clear();
    mascotasPermitidas.clear();


    rescatistaPrueba = new Rescatista();
    formulario = new FormularioMascotaPerdida(new DatosPersonales("Pablo", "Perez", LocalDate.now(), TipoDocumento.DNI, 1, contactoDePrueba("Pablo", "Perez", 47483233, "pablop@shimeil.com"), "nose 123"), "Re loco", new ArrayList<Foto>(), new Ubicacion(0.0, 0.0), LocalDate.now(), "123");
    duenioDePruebaUno = new Duenio("juancitoGomez123", "xXpanchito94Xx",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("MCQueen", "Rodriguez", 1138475426, "elrayomcqueen@hotmail.com"), "nose 123"));
    mascota = new MascotaRegistrada(TipoMascota.GATO, "Don Gato", "Gatokun", 46, Sexo.FEMENINO, "Lindo", new ArrayList<Foto>(), new ArrayList<Caracteristica>());
    mascotasPermitidas.add(TipoMascota.GATO);
    mascotasPermitidas.add(TipoMascota.PERRO);
    hogarAdecuado = new HogarTransitoAdaptado("0001", "Lo de Roberto", "Pichula 456", new Ubicacion(100.0, 100.0), "47481564", mascotasPermitidas, 50, true, RepositorioCaracteristicas.getInstance().todasLasCaracteristicas());
    hogarInadecuado = new HogarTransitoAdaptado("0002", "Zootopia", "Cachito 333", new Ubicacion(700.0, 700.0), "47481565", mascotasPermitidas, 50, true, RepositorioCaracteristicas.getInstance().todasLasCaracteristicas());


    centroDeRescate.setNotificador(notificadorMock);
    centroDeRescate.setServicioHogaresTransito(servicioHogaresMock);
    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(centroDeRescate);
    duenioDePruebaUno.registrarse();
    duenioDePruebaUno.registrarMascota(mascota, centroDeRescate);
    mascota.setID("123");
  }

  @Test
  public void alFiltrarTodosLosHogaresSoloDevuelveElAdecuado() {
    List<HogarTransitoAdaptado> hogares = new ArrayList<>();
    hogares.add(hogarAdecuado);
    hogares.add(hogarInadecuado);

    when(servicioHogaresMock.solicitarTodosLosHogares()).thenReturn(hogares);
    doCallRealMethod().when(servicioHogaresMock).filtrarHogaresPara(any(), any());

    List<HogarTransitoAdaptado> hogaresFiltrados = rescatistaPrueba.buscarHogaresDeTransito(formulario, 500.0);

    assert(hogaresFiltrados.contains(hogarAdecuado));
    assert(!hogaresFiltrados.contains(hogarInadecuado));
  }

  @Test
  public void solicitudesSeGeneranCorrectamente() {

    rescatistaPrueba.generarSolicitudPublicacion(formulario);

    Assertions.assertEquals(1, centroDeRescate.getSolicitudesPublicacion().size());
  }

  private List<Contacto> contactoDePrueba(String nombre, String apellido, Integer telefono, String email){
    List<Contacto> contactos = new ArrayList<>();
    Contacto contacto = new Contacto(nombre, apellido, telefono, email);
    contactos.add(contacto);
    return contactos;
  }
}
