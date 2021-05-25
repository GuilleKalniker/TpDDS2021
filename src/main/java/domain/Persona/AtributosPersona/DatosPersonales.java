package domain.Persona.AtributosPersona;

import domain.Exceptions.SinContactosException;

import java.time.LocalDate;
import java.util.List;

public class DatosPersonales {

  private String nombre;
  private String apellido;
  private LocalDate fechaDeNacimiento;
  private TipoDocumento tipoDocumento;
  private Integer nroDocumento;
  private List<Contacto> contactos;
  private String direccion;

  public DatosPersonales(String nombre, String apellido, LocalDate fechaDeNacimiento, TipoDocumento tipoDocumento, Integer nroDocumento, List<Contacto> contactos) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaDeNacimiento = fechaDeNacimiento;
    this.tipoDocumento = tipoDocumento;
    this.nroDocumento = nroDocumento;

    if (contactos.isEmpty()) {
      throw new SinContactosException();
    }
    this.contactos = contactos;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }
}
