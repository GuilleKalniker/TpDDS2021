package domain.Mascota;

import domain.Persona.Duenio;

import java.time.LocalDate;
import java.util.ArrayList;

public class MascotaRegistrada {
  private TipoMascota tipo;
  private String nombre;
  private String apodo;
  private Integer edad;
  private Sexo sexo;
  private String descripcionFisica;
  private ArrayList<Foto> fotos;
  private Integer qr;
  private Duenio duenio;

  //TODO caracteristica mascota

  public MascotaRegistrada(TipoMascota tipo, String nombre, String apodo, Integer edad, Sexo sexo, String descripcionFisica, ArrayList<Foto> fotos, Integer qr, Duenio duenio) {
    this.tipo = tipo;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edad = edad;
    this.sexo = sexo;
    this.descripcionFisica = descripcionFisica;
    this.fotos = fotos;
    this.qr = qr;
    this.duenio = duenio;
  }

  public Integer getQr() {
    return qr;
  }

  public Duenio getDuenio() {
    return duenio;
  }
}
