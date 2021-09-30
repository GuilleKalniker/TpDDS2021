package domain.Persona;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Repositorio.AdapterJPA;
import domain.Sistema.CentroDeRescate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VoluntarioTest {

  private CentroDeRescate centroDeRescate = new CentroDeRescate(new Ubicacion(42.56, 864.23));
  private Voluntario voluntarioPrueba = new Voluntario("panfleto32", "Sacerdocio369");
  private PublicacionMascotaPerdida solicitud = solicitudDePrueba();

  private PublicacionMascotaPerdida solicitudDePrueba() {
    Contacto contacto = new Contacto("Juan", "Perez", 113232424, "cachito@a.com");
    List<Contacto> contactos = new ArrayList<>();
    contactos.add(contacto);
    DatosPersonales datos = new DatosPersonales("Juan", "Perez", LocalDate.now(), TipoDocumento.DNI, 42447322, contactos, "nose 123");
    FormularioMascotaPerdida formulario = new FormularioMascotaPerdida(datos, "Masomenos", new ArrayList<String>(), new Ubicacion(1.1, 532.2), LocalDate.now());
    return new PublicacionMascotaPerdida(formulario);
  }

  @BeforeEach
  public void init() {
    AdapterJPA.beginTransaction();
    centroDeRescate.getSolicitudesPublicacion().clear();
    centroDeRescate.getPublicacionesMascotaPerdidasSinID().clear();

    centroDeRescate.generarSolicitud(solicitud);

    AdapterJPA.persist(centroDeRescate);
    AdapterJPA.persist(solicitud);
  }

  @AfterEach
  public void deinit() {
    AdapterJPA.rollback();
  }

  @Test
  public void seApruebanLasSolicitudesCorrectamente() {
    voluntarioPrueba.aprobarSolicitud(solicitud);

    Assertions.assertEquals(1, centroDeRescate.getPublicacionesMascotaPerdidasSinID().size());
    Assertions.assertEquals(0, centroDeRescate.getSolicitudesPublicacion().size());
  }

  @Test
  public void seRechazanLasSolicitudesCorrectamente() {
    voluntarioPrueba.rechazarSolicitud(solicitud);

    Assertions.assertEquals(0, centroDeRescate.getPublicacionesMascotaPerdidasSinID().size());
    Assertions.assertEquals(0, centroDeRescate.getSolicitudesPublicacion().size());
  }
}
