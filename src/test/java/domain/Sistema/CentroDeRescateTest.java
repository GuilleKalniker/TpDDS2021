package domain.Sistema;

import domain.Mascota.*;
import domain.Mascota.AtributosMascota.Sexo;
import domain.Mascota.AtributosMascota.TipoMascota;
import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Persona.*;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Repositorio.RepositorioMascotas;
import domain.Repositorio.RepositorioUsuarios;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class CentroDeRescateTest {

  @BeforeEach
  void init() {
    //TODO borrar la base de datos de prueba ¿?
  }

  /** Nuevos **/

  @Test
  public void obtenemosUnaListaDeIDsAcordeALasMascotasRegistradas(){
    String id_pepita = RepositorioMascotas.getInstance().registrarMascota(pepita);
    String id_chinchulin = RepositorioMascotas.getInstance().registrarMascota(chinchulin);

    assertTrue(this.centro.existeMascota(id_pepita));
    assertTrue(this.centro.existeMascota(id_chinchulin));
  }

  @Test
  public void BuscamosElDueñoApartirDeUnaMascotaRegistrada(){
    Duenio duenioDePrueba = new Duenio("juan4321", "guilloteelmaskpox2",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("Jesus", "DeNazareth"), "nose 123"));
    duenioDePrueba.registrarse();
    duenioDePrueba.registrarMascota(pepita, new CentroDeRescate(new Ubicacion(2.2,2.2)));
    assertTrue(this.centro.buscarDuenioApartirIDMascota(pepita.getID()).getNombreUsuario().equals(duenioDePrueba.getNombreUsuario()));
  }

  @Test
  public void identificoUnaMascotaIdentificaBien(){
    String id = RepositorioMascotas.getInstance().registrarMascota(pepita);
    MascotaRegistrada mascotaEncontrada = this.centro.buscarMascota(id);
    assertEquals(pepita.getID(), mascotaEncontrada.getID());
  }


  @Test
  public void seEncuentraElCentroMasCercanoAUnaUbicacion() {
    CentroDeRescate centroLejano = new CentroDeRescate(new Ubicacion(10.0, 0.0));
    CentroDeRescate centroCercano = new CentroDeRescate(new Ubicacion(1.0, 1.0));
    CentroDeRescate centroLejano1 = new CentroDeRescate(new Ubicacion(4.0, 1.0));
    CentroDeRescate centroLejano2 = new CentroDeRescate(new Ubicacion(1.0, 3.0));
    CentroDeRescate centroLejano3 = new CentroDeRescate(new Ubicacion(1.5, 1.5));

    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(centroLejano);
    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(centroCercano);
    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(centroLejano1);
    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(centroLejano2);
    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(centroLejano3);

    Assertions.assertEquals(centroCercano, RepositorioCentroDeRescate.getInstance().getCentroDeRescateMasCercanoA(new Ubicacion(0.0, 0.0)));
  }


  /** Funciones y definiciones **/

  private CentroDeRescate centro = new CentroDeRescate(new Ubicacion(2.2,2.2));

  private Duenio duenioDePruebaUno = new Duenio("juanito123", "guilloteelmaskpo",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("Jesus", "DeNazareth"), "nose 123"));

  private Duenio duenioDePruebaDos = new Duenio("pedritokpo1", "willirex777",new DatosPersonales("Pedro", "Martinez", LocalDate.now(), TipoDocumento.DNI, 20123457, contactoDePrueba("Jesus", "DeNazareth"), "nose 123"));

  private DatosPersonales datosRescastista = new DatosPersonales("Guillermo", "Francella", LocalDate.now(), TipoDocumento.DNI, 14235653, contactoDePrueba("Jesus", "DeNazareth"), "nose 123");

  private FormularioMascotaPerdida pepitaPerdida = new FormularioMascotaPerdida(datosRescastista, "Bastante saludable", new ArrayList<String>(), new Ubicacion(2.2,2.2), LocalDate.now(), "saasdasdasd");

  private FormularioMascotaPerdida chinchulinPerdido = new FormularioMascotaPerdida(datosRescastista, "Bastante saludable", new ArrayList<String>(), new Ubicacion(2.2,2.2), LocalDate.now().minusDays(11), "asdsadadsaads");

  private MascotaRegistrada pepita = new MascotaRegistrada(TipoMascota.PERRO, "Pepita", "Pepisauria", 9, Sexo.FEMENINO, "Perra corgi muy linda", new ArrayList<String>(),new ArrayList<>());

  private MascotaRegistrada chinchulin = new MascotaRegistrada(TipoMascota.PERRO, "Chinchulin", "Asadito", 9, Sexo.MASCULINO, "Perro shiba muy lindo", new ArrayList<String>(),new ArrayList<>());


  void registrarleMascotaADuenio(Duenio unDuenio) {
    MascotaRegistrada mascota = new MascotaRegistrada(TipoMascota.PERRO, "Pepito", "Pepisaurio", 10, Sexo.MASCULINO, "Perro salchicha muy lindo", new ArrayList<String>(), new ArrayList<>());
    unDuenio.registrarMascota(mascota, new CentroDeRescate(new Ubicacion(2.2,2.2)));
  }

  private ArrayList<Contacto> contactoDePrueba(String nombre, String apellido){
    ArrayList<Contacto> contactos = new ArrayList<>();
    Contacto contacto = new Contacto(nombre, apellido, 1145686431 , nombre.charAt(0) + apellido.toLowerCase() + "@frba.utn.edu.ar");
    contactos.add(contacto);
    return contactos;
  }
}
