package domain.Sistema;

import domain.Mascota.*;
import domain.Persona.*;
import domain.Repositorio.RepositorioMascotas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class CentroDeRescateTest {

  @BeforeEach
  void init() {
    RepositorioMascotas.getInstance().getMascotasRegistradas().clear();
  }

  @Test
  public void seRegistroUnaMascotaCorrectamente() {

    this.registrarleMascotaADuenio(duenioDePruebaUno);
    assertEquals(RepositorioMascotas.getInstance().getMascotasRegistradas().size(), 1, 0);
  }

  @Test
  public void qrLanzaExcepcionAlNoEstarRegistrado() {
    assertThrows(Exception.class, () -> {CentroDeRescate.getInstance().validarQR(465);});
  }

  @Test
  public void listaDeQRFuncionaAlTenerUnaMascotaRegistrada() {

    this.registrarleMascotaADuenio(duenioDePruebaUno);
    assertEquals(CentroDeRescate.getInstance().obtenerListaDeQRs().size(), 1, 0);
  }

  @Test
  public void seIdentificaCorrectamenteMascota() {

    CentroDeRescate.getInstance().agregarMascotaRegistrada(pepita);
    assertEquals(CentroDeRescate.getInstance().identificarMascota(1), pepita);
  }

  @Test
  public void siSePerdieronDosMascotasPeroUnaHaceMasDeDiezDiasNoApareceEnLaLista() {
    CentroDeRescate.getInstance().agregarEstadoMascotaPerdida(pepitaPerdida);
    CentroDeRescate.getInstance().agregarEstadoMascotaPerdida(chinchulinPerdido);
    assertEquals(CentroDeRescate.getInstance().listarEstadosDeMascotasPerdidasEnUltimosDiezDias().size(), 1);
  }

  @Test
  public void despuesDeNotificarUnaMascotaPerdidaLaListaQuedaVacia() {
    CentroDeRescate.getInstance().agregarMascotaRegistrada(pepita);
    CentroDeRescate.getInstance().agregarEstadoMascotaPerdida(pepitaPerdida);
    CentroDeRescate.getInstance().notificarMascotaEncontrada(pepitaPerdida);
    assertTrue(CentroDeRescate.getInstance().getEstadosMascotasPerdidas().isEmpty());
  }

  private Duenio duenioDePruebaUno = new Duenio("juanito123", "guilloteelmaskpo",new DatosPersonales("Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com")));

  private Duenio duenioDePruebaDos = new Duenio("pedritokpo1", "willirex777",new DatosPersonales("Pedro", "Martinez", LocalDate.now(), TipoDocumento.DNI, 20123457, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com")));

  void registrarleMascotaADuenio(Duenio unDuenio) {
    unDuenio.registrarMascota(TipoMascota.PERRO, "Pepito", "Pepisaurio", 10, Sexo.MASCULINO, "Perro salchicha muy lindo", new ArrayList<Foto>());
  }

  private ArrayList<Contacto> contactoDePrueba(String nombre, String apellido, Integer telefono, String email){
    ArrayList<Contacto> contactos = new ArrayList<>();
    Contacto contacto = new Contacto(nombre, apellido, telefono, email);
    contactos.add(contacto);
    return contactos;
  }

  private Rescatista rescatistaDePrueba = new Rescatista(new DatosPersonales("Guillermo", "Francella", LocalDate.now(), TipoDocumento.DNI, 14235653, contactoDePrueba("Jesus", "ALSD", 1234, "ASD@hotmail.com")), "Ituizango 3843");

  private EstadoMascotaPerdida pepitaPerdida = new EstadoMascotaPerdida(rescatistaDePrueba, "Bastante saludable", new ArrayList<Foto>(), "Medrano 754", LocalDate.now(), 1);

  private EstadoMascotaPerdida chinchulinPerdido = new EstadoMascotaPerdida(rescatistaDePrueba, "Bastante saludable", new ArrayList<Foto>(), "Medrano 754", LocalDate.now().minusDays(11), 2);

  private MascotaRegistrada pepita = new MascotaRegistrada(TipoMascota.PERRO, "Pepita", "Pepisauria", 9, Sexo.FEMENINO, "Perra corgi muy linda", new ArrayList<Foto>(), 1, duenioDePruebaDos);

  private MascotaRegistrada chinchulin = new MascotaRegistrada(TipoMascota.PERRO, "Chinchulin", "Asadito", 9, Sexo.MASCULINO, "Perro shiba muy lindo", new ArrayList<Foto>(), 2, duenioDePruebaDos);
}
