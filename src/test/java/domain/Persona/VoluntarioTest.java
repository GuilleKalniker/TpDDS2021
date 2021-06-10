package domain.Persona;

import domain.Mascota.AtributosMascota.Foto;
import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Publicacion.SolicitudPublicacion;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Sistema.CentroDeRescate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VoluntarioTest {

  private CentroDeRescate centroDeRescateCorrecto = new CentroDeRescate(new Ubicacion(42.56, 864.23));
  private CentroDeRescate centroDeRescateIncorrecto = new CentroDeRescate(new Ubicacion(864.23, 42.56));
  private Voluntario voluntarioPrueba = new Voluntario("panfleto32", "Sacerdocio369", centroDeRescateCorrecto);
  private SolicitudPublicacion solicitud = solicitudDePrueba();

  private SolicitudPublicacion solicitudDePrueba() {
    Contacto contacto = new Contacto("Juan", "Perez", 113232424, "cachito@a.com");
    List<Contacto> contactos = new ArrayList<>();
    contactos.add(contacto);
    DatosPersonales datos = new DatosPersonales("Juan", "Perez", LocalDate.now(), TipoDocumento.DNI, 42447322, contactos);
    FormularioMascotaPerdida formulario = new FormularioMascotaPerdida(datos, "Masomenos", new ArrayList<Foto>(), new Ubicacion(1.1, 532.2), LocalDate.now());
    PublicacionMascotaPerdida publicacion = new PublicacionMascotaPerdida(formulario);
    return new SolicitudPublicacion(publicacion);
  }

  @BeforeEach
  public void init() {
    RepositorioCentroDeRescate.getInstance().getCentrosDeRescateRegistrados().clear();

    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(centroDeRescateCorrecto);
    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(centroDeRescateIncorrecto);

    centroDeRescateCorrecto.getSolicitudesPublicacion().clear();
    centroDeRescateCorrecto.getPublicacionesMascotaPerdidasSinID().clear();

    centroDeRescateIncorrecto.getSolicitudesPublicacion().clear();
    centroDeRescateIncorrecto.getPublicacionesMascotaPerdidasSinID().clear();

    centroDeRescateCorrecto.getSolicitudesPublicacion().add(solicitud);
    centroDeRescateIncorrecto.getSolicitudesPublicacion().add(solicitud);
  }

  @Test
  public void seApruebanLasSolicitudesCorrectamente() {
    voluntarioPrueba.aprobarSolicitud(solicitud);

    Assertions.assertEquals(1, centroDeRescateCorrecto.getPublicacionesMascotaPerdidasSinID().size());
    Assertions.assertEquals(0, centroDeRescateCorrecto.getSolicitudesPublicacion().size());
  }

  @Test
  public void noSePuedeAprobarPublicacionesQueNoEstanEnSuCentro() {
    centroDeRescateCorrecto.getSolicitudesPublicacion().clear();

    Assertions.assertThrows(Exception.class, () -> voluntarioPrueba.aprobarSolicitud(solicitud));
  }

  @Test
  public void seRechazanLasSolicitudesCorrectamente() {
    voluntarioPrueba.rechazarSolicitud(solicitud);

    Assertions.assertEquals(0, centroDeRescateCorrecto.getPublicacionesMascotaPerdidasSinID().size());
    Assertions.assertEquals(0, centroDeRescateCorrecto.getSolicitudesPublicacion().size());
  }
}
