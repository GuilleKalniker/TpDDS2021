package domain.Mascota;

import domain.Persona.UsuarioDuenio;

import java.util.List;

public class Mascota {
  private String nombre;
  private String apodo;
  private Integer edad;
  private Sexo sexo;
  private TipoMascota tipo;
  private String descripcion;
  private List<Foto> fotos;
  private UsuarioDuenio duenio; //TODO UsuarioDuenio
  private Integer id;

  //TODO caracteristica mascota

  public Mascota(String nombre, String apodo, Integer edad, Sexo sexo, TipoMascota tipo, String descripcion, List<Foto> fotos, UsuarioDuenio duenio) {
    this.nombre = nombre;
    this.apodo = apodo;
    this.edad = edad;
    this.sexo = sexo;
    this.tipo = tipo;
    this.descripcion = descripcion;
    this.fotos = fotos;
    this.duenio = duenio;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
