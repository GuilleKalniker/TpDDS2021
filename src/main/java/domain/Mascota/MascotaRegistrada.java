package domain.Mascota;

import Funciones.manejoQR;
import domain.Mascota.AtributosMascota.Foto;
import domain.Mascota.AtributosMascota.Sexo;
import domain.Mascota.AtributosMascota.TipoMascota;

import java.util.ArrayList;

public class MascotaRegistrada {
  private TipoMascota tipo;
  private String nombre;
  private String apodo;
  private Integer edad;
  private Sexo sexo;
  private String descripcionFisica;
  private ArrayList<Foto> fotos;
  private String ID;
  //TODO caracteristica mascota
  //private HashMap<String, String> caracteristicas;

  public MascotaRegistrada(TipoMascota tipo, String nombre, String apodo, Integer edad, Sexo sexo, String descripcionFisica, ArrayList<Foto> fotos) {
    this.tipo = tipo;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edad = edad;
    this.sexo = sexo;
    this.descripcionFisica = descripcionFisica;
    this.fotos = fotos;
  }

  public String getNombre() {
    return nombre;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public String getID(){
    return this.ID;
  }

  public Boolean coincideID(String ID) {
    return this.ID == ID;
  }

  public String generarQR() {
    return manejoQR.generarQR(this.getID(), this.getNombre(), 500, 500);
  }

  public String leerQR(String pathQR) {
    return manejoQR.leerQR(pathQR);
  }

  public Boolean esMiQR(String pathQR){
    return this.leerQR(pathQR) == this.getID();
  }
}
