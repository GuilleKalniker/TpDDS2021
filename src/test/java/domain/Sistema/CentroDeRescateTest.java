package domain.Sistema;

import domain.Mascota.*;
import domain.Persona.*;
import domain.Repositorio.RepositorioDuenios;
import domain.Repositorio.RepositorioMascotas;
import domain.Repositorio.RepositorioUsuarios;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class CentroDeRescateTest {

  @BeforeEach
  void init() {
    RepositorioMascotas.getInstance().getMascotasRegistradas().clear();
    RepositorioDuenios.getInstance().getDueniosRegistrados().clear();
    RepositorioUsuarios.getInstance().clear();
  }

  /** Nuevos **/

  @Test
  public void obtenemosUnaListaDeIDsAcordeALasMascotasRegistradas(){
    String id_pepita = RepositorioMascotas.getInstance().registrarMascota(pepita);
    String id_chinchulin = RepositorioMascotas.getInstance().registrarMascota(chinchulin);

    assertTrue(this.centro.obtenerListaDeIDs().contains(id_pepita));
    assertTrue(this.centro.obtenerListaDeIDs().contains(id_chinchulin));
  }

  @Test
  public void BuscamosElDueñoApartirDeUnaMascotaRegistrada(){
    Duenio duenioDePrueba = new Duenio("juan4321", "guilloteelmaskpox2",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com")));
    duenioDePrueba.registrarMascota(pepita);
    assertTrue(this.centro.buscarDuenioApartirMascota(pepita).getNombreDeUsuario().equals(duenioDePrueba.getNombreDeUsuario()));
  }

  @Test
  public void siSePerdieronDosMascotasPeroUnaHaceMasDeDiezDiasNoApareceEnLaLista() {
    this.centro.agregarDatosMascotaPerdida(pepitaPerdida);
    this.centro.agregarDatosMascotaPerdida(chinchulinPerdido);
    assertEquals(this.centro.listarEstadosDeMascotasPerdidasEnUltimosDiezDias().size(), 1);
  }

  @Test
  public void identificoUnaMascotaIdentificaBien(){
    String id = RepositorioMascotas.getInstance().registrarMascota(pepita);
    MascotaRegistrada mascotaEncontrada = this.centro.identificarMascota(id);
    assertEquals(pepita.getID(), mascotaEncontrada.getID());
  }

  @Test
  public void AlNotificarSeEliminaElDatoMascotaPerdida(){
    Duenio duenioDePrueba = new Duenio("juan4321", "guilloteelmaskpox2",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com")));
    duenioDePrueba.registrarMascota(pepita);
    pepitaPerdida.setID(pepita.getID());
    this.centro.agregarDatosMascotaPerdida(pepitaPerdida);
    this.centro.notificarMascotasDeLosUltimos10Dias();
    assertTrue(this.centro.getDatosMascotasPerdidas().isEmpty());
  }





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

  private CentroDeRescate centro = new CentroDeRescate();

  private Duenio duenioDePruebaUno = new Duenio("juanito123", "guilloteelmaskpo",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com")));

  private Duenio duenioDePruebaDos = new Duenio("pedritokpo1", "willirex777",new DatosPersonales("Pedro", "Martinez", LocalDate.now(), TipoDocumento.DNI, 20123457, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com")));

  private DatosPersonales datosRescastista = new DatosPersonales("Guillermo", "Francella", LocalDate.now(), TipoDocumento.DNI, 14235653, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com"));

  private DatosMascotaPerdida pepitaPerdida = new DatosMascotaPerdida(datosRescastista, "Bastante saludable", new ArrayList<Foto>(), "Medrano 754", LocalDate.now(), null);

  private DatosMascotaPerdida chinchulinPerdido = new DatosMascotaPerdida(datosRescastista, "Bastante saludable", new ArrayList<Foto>(), "Medrano 754", LocalDate.now().minusDays(11), null);

  private MascotaRegistrada pepita = new MascotaRegistrada(TipoMascota.PERRO, "Pepita", "Pepisauria", 9, Sexo.FEMENINO, "Perra corgi muy linda", new ArrayList<Foto>());

  private MascotaRegistrada chinchulin = new MascotaRegistrada(TipoMascota.PERRO, "Chinchulin", "Asadito", 9, Sexo.MASCULINO, "Perro shiba muy lindo", new ArrayList<Foto>());


  void registrarleMascotaADuenio(Duenio unDuenio) {
    MascotaRegistrada mascota = new MascotaRegistrada(TipoMascota.PERRO, "Pepito", "Pepisaurio", 10, Sexo.MASCULINO, "Perro salchicha muy lindo", new ArrayList<Foto>());
    unDuenio.registrarMascota(mascota);
  }

  private ArrayList<Contacto> contactoDePrueba(String nombre, String apellido, Integer telefono, String email){
    ArrayList<Contacto> contactos = new ArrayList<>();
    Contacto contacto = new Contacto(nombre, apellido, telefono, email);
    contactos.add(contacto);
    return contactos;
  }
}
