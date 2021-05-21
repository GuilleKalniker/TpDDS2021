package domain.Persona;

import domain.Exceptions.QRInexistenteException;
import domain.Mascota.Foto;
import domain.Mascota.MascotaRegistrada;
import domain.Mascota.Sexo;
import domain.Mascota.TipoMascota;
import domain.Repositorio.RepositorioMascotas;
import domain.Sistema.CentroDeRescate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class RescatistaTest {

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

  private MascotaRegistrada mascotaDePrueba = new MascotaRegistrada(TipoMascota.PERRO, "Chinchulin", "Asadito", 9, Sexo.MASCULINO, "Perro shiba muy lindo", new ArrayList<Foto>(), 3);

  private Rescatista rescatistaDePrueba = new Rescatista(new DatosPersonales("Guillermo", "Francella", LocalDate.now(), TipoDocumento.DNI, 14235653,contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com")),"Ituzaingo 1532");


}
