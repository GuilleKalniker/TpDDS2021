package domain.Persona;

import domain.Exceptions.ContraseniaInvalidaException;
import domain.Mascota.Foto;
import domain.Mascota.Sexo;
import domain.Mascota.TipoMascota;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class DuenioTest {

  public DuenioTest() throws Exception {
  }

  @Test
  public void duenioInicialNoTieneMascotas() {
    assert(duenioDePruebaUno.getMascotas().isEmpty());
  }

  @Test
  public void alAgregarleUnaMascotaAUnDuenioSuListaPoseeUnaMascota() {
    registrarleMascotaADuenio(duenioDePruebaUno);
    assertEquals(duenioDePruebaUno.getMascotas().size(), 1, 0);
  }

  @Test
  public void unDuenioPuedeRegistrarMasDeUnaMascota() {
    registrarleMascotaADuenio(duenioDePruebaUno);
    registrarleOtraMascotaADuenio(duenioDePruebaUno);
    assertEquals(duenioDePruebaUno.getMascotas().size(), 2,0);
  }

  @Test
  public void unDuenioLanzaExcepcionCuandoNoTieneContactos() {
    assertThrows(Exception.class, () -> {new Duenio("robertoLagarto", "robertito5432","Roberto", "Lagarto", LocalDate.now(), TipoDocumento.DNI, 12345342, new ArrayList<Contacto>());});
  }

  @Test
  public void unDuenioSeCreaBien() {
    assertEquals(duenioDePruebaUno.getClass(), Duenio.class);
  }

  @Test
  public void unDuenioNoSePuedeCrearConContraseniasYUsuarioInvalido() {
    Assertions.assertThrows(ContraseniaInvalidaException.class, () -> {new Duenio("moreeee", "12345", "morena", "Sisro", LocalDate.now(), TipoDocumento.DNI, 123456, contactoDePrueba("MCQueen", "Rodriguez", 1138475426, "elrayomcqueen@hotmail.com"));});
  }

  public Duenio duenioDePruebaUno = new Duenio("juancitoGomez", "matuTesterkpo","Juan", "Gomez", LocalDate.now(), TipoDocumento.DNI, 20123456, contactoDePrueba("MCQueen", "Rodriguez", 1138475426, "elrayomcqueen@hotmail.com"));

  public void registrarleMascotaADuenio(Duenio unDuenio) {
    unDuenio.registrarMascota(TipoMascota.PERRO, "Pepito", "Pepisaurio", 10, Sexo.MASCULINO, "Perro salchicha muy lindo", new ArrayList<Foto>());
  }
  public void registrarleOtraMascotaADuenio(Duenio unDuenio) {
    unDuenio.registrarMascota(TipoMascota.PERRO, "Jorgito", "Alfajor", 10, Sexo.MASCULINO, "Perro labrador muy lindo", new ArrayList<Foto>());
  }

  private ArrayList<Contacto> contactoDePrueba(String nombre, String apellido, Integer telefono, String email){
    ArrayList<Contacto> contactos = new ArrayList<>();
    Contacto contacto = new Contacto(nombre, apellido, telefono, email);
    contactos.add(contacto);
    return contactos;
  }
}