package domain.Persona;

import domain.Mascota.MascotaRegistrada;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Duenio {
  private String nombre;
  private String apellido;
  private LocalDate fechaNacimiento;
  private TipoDocumento tipoDocumento;
  private Integer nroDocumento;
  private List<Contacto> contacto;
  private List<MascotaRegistrada> mascotas;

  //TODO
  private String usuario;
  private String contrasenia;

  public Duenio(String nombre, String apellido, LocalDate fechaNacimiento, TipoDocumento tipoDocumento, Integer nroDocumento, List<Contacto> contacto) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
    this.tipoDocumento = tipoDocumento;
    this.nroDocumento = nroDocumento;
    this.contacto = contacto;
  }

  public void registrarMascota(MascotaRegistrada mascota) {
    this.mascotas.add(mascota);
    //TODO agregar mascota en el centro
  }

}