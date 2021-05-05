package domain;

import domain.Mascota.Foto;
import domain.Mascota.MascotaRegistrada;
import domain.Mascota.Sexo;
import domain.Mascota.TipoMascota;
import domain.Persona.Contacto;
import domain.Persona.Duenio;
import domain.Persona.TipoDocumento;

import java.time.LocalDate;
import java.util.ArrayList;

public class DuenioTest {

  // Test 1: Un duenio puede tener más de una mascota registrada

  private Duenio duenioDePruebaUno = duenioDePrueba("Juan", "Gomez", 1532498392, "gomezj@gmail.com", LocalDate.of(1968,1,1), TipoDocumento.DNI, 20123456);

  private Duenio duenioDePrueba(String nombre, String apellido, Integer telefono, String email, LocalDate fechaNacimiento, TipoDocumento tipoDoc, Integer nroDoc) {
    return new Duenio(nombre, apellido, fechaNacimiento, tipoDoc, nroDoc, this.contactoDePrueba(nombre, apellido, telefono, email));
  }

  private ArrayList<Contacto> contactoDePrueba(String nombre, String apellido, Integer telefono, String email){
    ArrayList<Contacto> contactos = new ArrayList<>();
    Contacto contacto = new Contacto(nombre, apellido, telefono, email);
    contactos.add(contacto);
    return contactos;
  }

  private MascotaRegistrada mascotaDePrueba(TipoMascota tipoMascota, String nombre, String apodo, Integer edad, Sexo sexo, String descripcion, ArrayList<Foto> fotos, Duenio duenio) {

  }
}