package domain.Mascota;

import domain.Persona.Duenio;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class MascotaRegistrada {
  private TipoMascota tipo;
  private String nombre;
  private String apodo;
  private Integer edad;
  private Sexo sexo;
  private String descripcionFisica;
  private ArrayList<Foto> fotos;
  private Integer qr;
  private String ID;
  //TODO caracteristica mascota
  //private HashMap<String, String> caracteristicas;



  public MascotaRegistrada(TipoMascota tipo, String nombre, String apodo, Integer edad, Sexo sexo, String descripcionFisica, ArrayList<Foto> fotos, Integer qr) {
    this.tipo = tipo;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edad = edad;
    this.sexo = sexo;
    this.descripcionFisica = descripcionFisica;
    this.fotos = fotos;
    this.qr = qr;
  }

  public Integer getQr() {
    return qr;
  }

  public String getNombre() {
    return nombre;
  }

  public Boolean coincideQR(Integer QR){
    return this.getQr() == QR;
  }


  public void setID(String ID) {
    this.ID = ID;
  }

  public String getID(){
    return this.ID;
  }

  //TODO
  public void generarQR(){
    // generamos un QR en base al id de una mascota
    // return generarQR(this.ID);
  }
  //TODO
  public String esMiQR(){
    // leemos el QR para obtener el ID que guardado en el
    // return leer(QR) == this.ID
    return null;
  }
}
