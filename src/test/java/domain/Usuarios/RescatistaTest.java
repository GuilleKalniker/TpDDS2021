package domain.Usuarios;

import domain.Mascota.Foto;
import domain.Mascota.MascotaRegistrada;
import domain.Mascota.Sexo;
import domain.Mascota.TipoMascota;
import domain.Persona.Contacto;
import domain.Persona.Duenio;
import domain.Persona.Rescatista;
import domain.Persona.TipoDocumento;
import domain.Sistema.CentroDeRescate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class RescatistaTest {

  public RescatistaTest() throws Exception {
  }

  @Test
  public void llegaLaNotificacionDeMascotaEncontradaAlCentro() {
    CentroDeRescate.getInstance().getEstadosMascotasPerdidas().clear();
    CentroDeRescate.getInstance().getMascotasRegistradas().clear();
    CentroDeRescate.getInstance().agregarMascotaRegistrada(mascotaDePrueba);
    rescatistaDePrueba.notificarMascotaEncontrada("Patita cortada :(", new ArrayList<>(), "Lugano 3431", LocalDate.now(), 3);
    assertEquals(1, CentroDeRescate.getInstance().getEstadosMascotasPerdidas().size(), 0);
  }

  private ArrayList<Contacto> contactoDePrueba(String nombre, String apellido, Integer telefono, String email){
    ArrayList<Contacto> contactos = new ArrayList<>();
    Contacto contacto = new Contacto(nombre, apellido, telefono, email);
    contactos.add(contacto);
    return contactos;
  }

  private Duenio duenioDePrueba = new Duenio("juan", "juan555431","Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com"));

  private MascotaRegistrada mascotaDePrueba = new MascotaRegistrada(TipoMascota.PERRO, "Chinchulin", "Asadito", 9, Sexo.MASCULINO, "Perro shiba muy lindo", new ArrayList<Foto>(), 3, duenioDePrueba);

  private Rescatista rescatistaDePrueba = new Rescatista("Guillermo", "Francella", LocalDate.now(), TipoDocumento.DNI, 14235653, "Ituzaingo 1532", contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com"));

}
