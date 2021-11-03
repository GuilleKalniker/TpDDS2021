package domain.Persona.AtributosPersona;

import domain.Exceptions.SinContactosException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Embeddable
public class DatosPersonales {

  private String nombre;
  private String apellido;

  @Column(columnDefinition = "DATE")
  private LocalDate fechaDeNacimiento;

  @Enumerated
  private TipoDocumento tipoDocumento;
  private Integer nroDocumento;

  @OneToMany(mappedBy = "duenio")
  private List<Contacto> contactos;
  private String direccion;

  public DatosPersonales(String nombre, String apellido, LocalDate fechaDeNacimiento, TipoDocumento tipoDocumento, Integer nroDocumento, List<Contacto> contactos, String direccion) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaDeNacimiento = fechaDeNacimiento;
    this.tipoDocumento = tipoDocumento;
    this.nroDocumento = nroDocumento;
    this.direccion = direccion;

    if (contactos.isEmpty()) {
      throw new SinContactosException();
    }
    this.contactos = contactos;
  }

  public DatosPersonales() {
  }

  public LocalDate getFechaDeNacimiento() {
    return fechaDeNacimiento;
  }

  public TipoDocumento getTipoDocumento() {
    return tipoDocumento;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public List<Contacto> getContactos() {
    return contactos;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }
}
