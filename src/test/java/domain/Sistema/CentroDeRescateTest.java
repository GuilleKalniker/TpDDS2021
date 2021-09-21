package domain.Sistema;

import domain.Mascota.*;
import domain.Mascota.AtributosMascota.Sexo;
import domain.Mascota.AtributosMascota.TipoMascota;
import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Persona.*;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Repositorio.RepositorioMascotas;
import domain.Repositorio.RepositorioUsuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class CentroDeRescateTest {

  @BeforeEach
  void init() {
    RepositorioMascotas.getInstance().getMascotasRegistradas().clear();
    RepositorioUsuarios.getInstance().clear();
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
    Duenio duenioDePrueba = new Duenio("juan4321", "guilloteelmaskpox2",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com"), "nose 123"));
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
/*
  @Test
  public void AlNotificarSeEliminaElDatoMascotaPerdida(){
    Duenio duenioDePrueba = new Duenio("juan4321", "guilloteelmaskpox2",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com")));
    duenioDePrueba.registrarMascota(pepita, new CentroDeRescate());
    pepitaPerdida.setID(pepita.getID());
    this.centro.agregarDatosMascotaPerdida(pepitaPerdida);
    this.centro.notificarMascotasDeLosUltimos10Dias();
    assertTrue(this.centro.listaDatosMascotasPerdidasUltimosDiezDias().isEmpty());
  }*/





  /** Antiguos **/

/*
  @Test
  public void seRegistroUnaMascotaCorrectamente() {

    this.registrarleMascotaADuenio(duenioDePruebaUno);
    assertEquals(RepositorioMascotas.getInstance().getMascotasRegistradas().size(), 1, 0);
  }

  @Test
  public void listaDeIDsFuncionaAlTenerUnaMascotaRegistrada() {

    this.registrarleMascotaADuenio(duenioDePruebaUno);
    assertEquals(this.centro.obtenerListaDeIDs().size(), 1, 0);
  }

  @Test
  public void seIdentificaCorrectamenteMascota() {

    this.centro.agregarMascotaRegistrada(pepita);
    assertEquals(CentroDeRescate.getInstance().identificarMascota(1), pepita);
  }

  @Test
  public void siRegistroUnDuenioAumentaLaCantidad() {
    Duenio duenio = new Duenio("juanito123", "guilloteelmaskpo",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com")));
    assertEquals(RepositorioDuenios.getInstance().getDueniosRegistrados().size(),1);
  }
*/

  /** Funciones y definiciones **/

  private CentroDeRescate centro = new CentroDeRescate(new Ubicacion(2.2,2.2));

  private Duenio duenioDePruebaUno = new Duenio("juanito123", "guilloteelmaskpo",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com"), "nose 123"));

  private Duenio duenioDePruebaDos = new Duenio("pedritokpo1", "willirex777",new DatosPersonales("Pedro", "Martinez", LocalDate.now(), TipoDocumento.DNI, 20123457, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com"), "nose 123"));

  private DatosPersonales datosRescastista = new DatosPersonales("Guillermo", "Francella", LocalDate.now(), TipoDocumento.DNI, 14235653, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com"), "nose 123");

  private FormularioMascotaPerdida pepitaPerdida = new FormularioMascotaPerdida(datosRescastista, "Bastante saludable", new ArrayList<String>(), new Ubicacion(2.2,2.2), LocalDate.now(), "saasdasdasd");

  private FormularioMascotaPerdida chinchulinPerdido = new FormularioMascotaPerdida(datosRescastista, "Bastante saludable", new ArrayList<String>(), new Ubicacion(2.2,2.2), LocalDate.now().minusDays(11), "asdsadadsaads");

  private MascotaRegistrada pepita = new MascotaRegistrada(TipoMascota.PERRO, "Pepita", "Pepisauria", 9, Sexo.FEMENINO, "Perra corgi muy linda", new ArrayList<String>(),new ArrayList<>());

  private MascotaRegistrada chinchulin = new MascotaRegistrada(TipoMascota.PERRO, "Chinchulin", "Asadito", 9, Sexo.MASCULINO, "Perro shiba muy lindo", new ArrayList<String>(),new ArrayList<>());


  void registrarleMascotaADuenio(Duenio unDuenio) {
    MascotaRegistrada mascota = new MascotaRegistrada(TipoMascota.PERRO, "Pepito", "Pepisaurio", 10, Sexo.MASCULINO, "Perro salchicha muy lindo", new ArrayList<String>(), new ArrayList<>());
    unDuenio.registrarMascota(mascota, new CentroDeRescate(new Ubicacion(2.2,2.2)));
  }

  private ArrayList<Contacto> contactoDePrueba(String nombre, String apellido, Integer telefono, String email){
    ArrayList<Contacto> contactos = new ArrayList<>();
    Contacto contacto = new Contacto(nombre, apellido, telefono, email);
    contactos.add(contacto);
    return contactos;
  }
}
