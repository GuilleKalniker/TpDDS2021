package domain.Publicacion;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Repositorio.AdapterJPA;
import domain.Sistema.CentroDeRescate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PublicacionMascotaPerdidaTest {

  @BeforeEach
  public void setup() {
    AdapterJPA.beginTransaction();
  }

  @AfterEach
  public void teardown() {
    AdapterJPA.rollback();
  }

  @Test
  public void seAceptaYSeEliminanMascotasPerdidasOk(){
    PublicacionMascotaPerdida publicacion = crearPublicacion();
    PublicacionMascotaPerdida publicacion2 = crearPublicacion();
    CentroDeRescate centro = new CentroDeRescate(new Ubicacion(321.04, 6542.21));

    publicacion.setCentro(centro);
    publicacion2.setCentro(centro);
    publicacion.aceptarseEnElCentro();
    publicacion2.eliminarseEnElCentro();

    Assertions.assertEquals(centro.getPublicacionesMascotaPerdidasSinID().size(),1);
    Assertions.assertEquals(centro.getSolicitudesPublicacion().size(),0);
  }

  public PublicacionMascotaPerdida crearPublicacion() {
    Contacto c = new Contacto("Andres", "Calamaro", 1138636324, "andres_calamaro@hotmail.com.ar");
    List<Contacto> contactos = new ArrayList<>();
    contactos.add(c);
    DatosPersonales dp = new DatosPersonales("Rocket", "Launcher", LocalDate.of(1998, 12, 2), TipoDocumento.DNI, 38642215, contactos, "Johto 1995");

    FormularioMascotaPerdida f = new FormularioMascotaPerdida(dp, "Hecho mierda", new ArrayList<>(), new Ubicacion(214.83,53.75), LocalDate.of(2021, 9, 22), 3);

    return new PublicacionMascotaPerdida(f);

  }
}
