package domain.Mascota;

import Funciones.ManejoQR;
import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Mascota.AtributosMascota.Sexo;
import domain.Mascota.AtributosMascota.TipoMascota;
import domain.Persona.Duenio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mascota")
public class MascotaRegistrada {

  @Id
  @GeneratedValue
  private long id;

  @Enumerated
  private TipoMascota tipo;

  private String nombre;
  private String apodo;
  private Integer edad;

  @ManyToOne
  private Duenio duenio;
  @Enumerated
  private Sexo sexo;

  private String descripcionFisica;

  @ElementCollection
  private List<String> fotos = new ArrayList<>();

  @ManyToMany
  private List<Caracteristica> caracteristicas;

  public MascotaRegistrada(TipoMascota tipo, String nombre, String apodo, Integer edad, Sexo sexo, String descripcionFisica, ArrayList<String> fotos, List<Caracteristica> caracteristicas) {
    this.tipo = tipo;
    this.nombre = nombre;
    this.apodo = apodo;
    this.edad = edad;
    this.sexo = sexo;
    this.descripcionFisica = descripcionFisica;
    this.fotos = fotos;
    this.caracteristicas = caracteristicas;
  }

  public MascotaRegistrada() {}

  public TipoMascota getTipo() {
    return tipo;
  }

  public List<Caracteristica> getCaracteristicas() {
    return caracteristicas;
  }

  public String getNombre() {
    return nombre;
  }

  public void agregarFoto(String url) {
    fotos.add(url);
  }


  public long getID(){
    return this.id;
  }

  public Boolean coincideID(long ID) {
    return this.getID() == ID;
  }

  //TODO: Ver que hacemos con esto
 /* public String generarQR() {
    return ManejoQR.generarQR(this.getID(), this.getNombre(), 500, 500);
  }

  public String leerQR(String pathQR) {
    return ManejoQR.leerQR(pathQR);
  }

  public Boolean esMiQR(String pathQR){
    return this.leerQR(pathQR) == String.valueOf(this.getID());
  }
  */
  public Duenio getDuenio() {
    return duenio;
  }

  public void setDuenio(Duenio duenio) {
    this.duenio = duenio;
  }
}
