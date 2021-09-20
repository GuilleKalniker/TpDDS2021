package domain.Persona.AtributosPersona;

import domain.Persona.Duenio;

import javax.persistence.*;

@Entity
@Table(name = "contacto")
public class Contacto {
  @Id
  @GeneratedValue
  private long id;

  private String nombre;
  private String apellido;
  private Integer telefono;
  private String email;

  //Agregado para que no se genere una nueva tabla en ORM-------
  @ManyToOne
  private Duenio duenio;

  public void setDuenio(Duenio duenio) {
    this.duenio = duenio;
  }
  //------------------------------------------------------------


  public Contacto(String nombre, String apellido, Integer telefono, String email) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.telefono = telefono;
    this.email = email;
  }

  public Contacto() {};



  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public Integer getTelefono() {
    return telefono;
  }

  public String getEmail() {
    return email;
  }
}
