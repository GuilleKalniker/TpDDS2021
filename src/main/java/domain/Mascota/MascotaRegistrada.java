package domain.Mascota;

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

  //TODO caracteristica mascota

  public MascotaRegistrada(TipoMascota tipo, String nombre, String apodo, Integer edad, Sexo sexo, String descripcionFisica, ArrayList<Foto> fotos) {
    this.tipo = tipo;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edad = edad;
    this.sexo = sexo;
    this.descripcionFisica = descripcionFisica;
    this.fotos = fotos;
  }

  public Integer getQr() {
    return qr;
  }
}
