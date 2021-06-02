package domain.Persona;
import static org.mockito.Mockito.*;

import domain.Mascota.AtributosMascota.*;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Repositorio.RepositorioMascotas;
import domain.Servicios.ClasesParaLaConsulta.HogarTransito;
import domain.Servicios.HogaresTransitoService;
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
  /*
  @BeforeEach
  void init() {
    CentroDeRescate.getInstance().getEstadosMascotasPerdidas().clear();
   RepositorioMascotas.getInstance().getMascotasRegistradas().clear();
  }

  @Test
  public void llegaLaNotificacionDeMascotaEncontradaAlCentro() {
    CentroDeRescate.getInstance().agregarMascotaRegistrada(mascotaDePrueba);
    rescatistaDePrueba.notificarMascotaEncontrada("Patita cortada :(", new ArrayList<>(), "Lugano 3431", LocalDate.now(), 3);
    assertEquals(1, CentroDeRescate.getInstance().getEstadosMascotasPerdidas().size(), 0);
  }

  @Test

  public void noSePuedeEncontrarMascotaConUnQRInexistente() {
    assertThrows( QRInexistenteException.class, () -> {rescatistaDePrueba.notificarMascotaEncontrada("adfasdfad", new ArrayList<>(), "dagsdgasdg", LocalDate.now(), 666);});
  }

  //coment ario
  private ArrayList<Contacto> contactoDePrueba(String nombre, String apellido, Integer telefono, String email){
    ArrayList<Contacto> contactos = new ArrayList<>();
    Contacto contacto = new Contacto(nombre, apellido, telefono, email);
    contactos.add(contacto);
    return contactos;
  }

  //private Duenio duenioDePrueba = new Duenio("juan", "juan555431",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com")));

  //private MascotaRegistrada mascotaDePrueba = new MascotaRegistrada(TipoMascota.PERRO, "Chinchulin", "Asadito", 9, Sexo.MASCULINO, "Perro shiba muy lindo", new ArrayList<Foto>(), 3);

  private Rescatista rescatistaDePrueba = new Rescatista(new DatosPersonales("Guillermo", "Francella", LocalDate.now(), TipoDocumento.DNI, 14235653,contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com")),"Ituzaingo 1532");
*/
  //TODO: ARREGLAR TESTS
  /*@Test
  public void llegaLaNotificacionDeMascotaEncontradaAlCentro() {
     ArrayList<String> caracteristicas = new ArrayList<>(Arrays.asList("Manso","Jugueton"));

    FormularioMascotaPerdida formularioMascota = new FormularioMascotaPerdida(new DatosPersonales("Pepe", "Alvarez", java.time.LocalDate.now(), TipoDocumento.DNI, 1,contactoDePrueba("Carlos","ASD",48569878,"hola@gmail.com")),"loco", null, new Ubicacion(1.0,2.0), java.time.LocalDate.now(),"1");

    MascotaRegistrada mascota1 = new MascotaRegistrada(TipoMascota.PERRO, "Jorgito", "Alfajor", 10, Sexo.MASCULINO, "Perro labrador muy lindo", new ArrayList<Foto>(), new ArrayList<Caracteristica>(Arrays.asList(Caracteristica.Manso, Caracteristica.Jugueton)));
    mascota1.setID("1");

    RepositorioMascotas.getInstance().getMascotasRegistradas().add(mascota1);

    List<HogarTransito> hogaresTransito = new ArrayList<HogarTransito>();
      hogaresTransito.add(new HogarTransito("1", "willyElGrandioso", "callefalsa123", 200.0,300.0, "1134657895",true,false,10,4,true, caracteristicas));
      hogaresTransito.add(new HogarTransito("2", "willyElNOGrandioso", "callefalsa1234", 205.0,304.0, "1134659895",false,true,10,4,true, caracteristicas));


      HogarTransito hogarmock1 = Mockito.mock(HogarTransito.class);
      HogarTransito hogarmock2 = Mockito.mock(HogarTransito.class);
      when(hogarmock1.esAdecuado(mascota1, 5000.0, new Ubicacion(200.0,300.0))).thenReturn(true);
      when(hogarmock1.esAdecuado(mascota1, 5000.0, new Ubicacion(200.0,300.0))).thenReturn(false);

    List<HogarTransito> hogaresTransito = new ArrayList<HogarTransito>();
    hogaresTransito.add(hogarmock1);
    hogaresTransito.add(hogarmock2);

    ServicioHogaresTransito hogarMock = Mockito.mock(ServicioHogaresTransito.class);
    when(hogarMock.solicitarTodosLosHogares()).thenReturn(hogaresTransito);
    Rescatista rescatista = new Rescatista();

   //verify(hogarTransitoMock).estaEnElRadio(new Ubicacion(200.0,300.0),5000.0);
    assertEquals(rescatista.buscarHogaresDeTransito(formularioMascota, 5000.0, new CentroDeRescate(new Ubicacion(0.0,0.0))).size(),1);
  }
  private ArrayList<Contacto> contactoDePrueba(String nombre, String apellido, Integer telefono, String email){
    ArrayList<Contacto> contactos = new ArrayList<>();
    Contacto contacto = new Contacto(nombre, apellido, telefono, email);
    contactos.add(contacto);
    return contactos;
  }
*/
}
