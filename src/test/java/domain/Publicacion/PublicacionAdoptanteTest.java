package domain.Publicacion;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Mascota.AtributosMascota.Sexo;
import domain.Mascota.AtributosMascota.TipoMascota;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import domain.Repositorio.AdapterJPA;
import domain.Servicios.Notificadores.Notificador;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class PublicacionAdoptanteTest {

  @BeforeEach
  public void setup() {
    AdapterJPA.beginTransaction();
  }

  @AfterEach
  public void teardown() {
    AdapterJPA.rollback();
  }

  @Test
  public void seAgregaYseQuitaCaractOK(){
    PublicacionAdoptante publicacion = new PublicacionAdoptante(crearDuenio( "matiReCapoo123", "algoRePicante", "calle falsa123") );
    publicacion.agregarPreferencia("le gustan los raviole");

    Assertions.assertEquals(publicacion.getPreferencias().size(),1);

    publicacion.quitarPreferencia("le gustan los raviole");
    Assertions.assertEquals(publicacion.getPreferencias().size(),0);

  }

  @Test
  public void seNotificaOk(){
    Notificador notificadorMock = mock(Notificador.class);
    Duenio duenio = crearDuenio( "matiReCapoo1223", "algoRePica2nte", "calle falsa2123");
    duenio.setNotificadores(Arrays.asList(notificadorMock));

    PublicacionAdoptante publicacion = new PublicacionAdoptante(duenio);
    PublicacionAdopcion publiAdopcion = new PublicacionAdopcion(new ArrayList<>(), crearMascota("tilin", new ArrayList<>()));

    publicacion.recibirSugerenciaAdopcion(publiAdopcion);
    verify(notificadorMock).notificar(any());

  }

  public Duenio crearDuenio(String nombre, String apellido, String direccion) {
    Contacto c = new Contacto("Andres", "Calamaro", 1138636324, "andres_calamaro@hotmail.com.ar");
    List<Contacto> contactos = new ArrayList<>();
    contactos.add(c);
    DatosPersonales dp = new DatosPersonales(nombre, apellido, LocalDate.of(2010, 4, 6), TipoDocumento.DNI, 30214664, contactos, direccion);
    Duenio duenio = new Duenio(nombre.substring(0, 3).toLowerCase() + "_" + apellido.substring(0, 3).toLowerCase(), apellido + apellido.length() + "@!", dp);
    return duenio;
  }

  public MascotaRegistrada crearMascota(String nombre, ArrayList<String> fotos) {
    return new MascotaRegistrada(TipoMascota.PERRO, nombre, nombre.substring(0, 2) + nombre.substring(0, 2).toLowerCase(),4, Sexo.HEMBRA, "Muerta de hambre", fotos, new ArrayList<Caracteristica>());
  }

}
