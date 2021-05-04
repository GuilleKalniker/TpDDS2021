package domain.Persona;

import java.time.LocalDate;
import java.util.List;

public class Rescatista {
  private String nombre;
  private String apellido;
  private LocalDate fechaDeNacimiento;
  private TipoDocumento tipoDocumento;
  private Integer nroDocumento;
  private String direccion;
  private List<Contacto> contacto;

  public Rescatista(String nombre, String apellido, LocalDate fechaDeNacimiento, TipoDocumento tipoDocumento, Integer nroDocumento, String direccion, List<Contacto> contacto) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaDeNacimiento = fechaDeNacimiento;
    this.tipoDocumento = tipoDocumento;
    this.nroDocumento = nroDocumento;
    this.direccion = direccion;
    this.contacto = contacto;
  }

  public void notificarMascotaEncontrada(){}
}
