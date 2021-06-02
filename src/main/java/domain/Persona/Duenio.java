package domain.Persona;


import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Repositorio.RepositorioUsuarios;
import domain.Sistema.CentroDeRescate;

import java.util.ArrayList;
import java.util.List;

public class Duenio{

  private String nombreDeUsuario;
  private String contrasenia;
  private DatosPersonales datosPersonales;
  private List<String> mascotasID = new ArrayList<>();

  private RepositorioUsuarios repositorioUsuarios = RepositorioUsuarios.getInstance();

  public Duenio(String usuario, String contrasenia,DatosPersonales datosPersonales) {
    this.nombreDeUsuario = usuario;
    this.contrasenia = contrasenia;
    this.datosPersonales = datosPersonales;
  }

  public void setRepositorioUsuarios(RepositorioUsuarios repositorioUsuarios) {
    this.repositorioUsuarios = repositorioUsuarios;
  }

  public DatosPersonales getDatosPersonales() {
    return datosPersonales;
  }

  public List<String> getMascotasID() {
    return this.mascotasID;
  }


  /*
  * registrarMascota(1)
  * Consigue del centro un ID nuevo para la mascota que se va a registrar, luego crea el objeto mascota con todos los parametros de la funcion + this, para que mascota conozca a su duenio
>>>>>>> 8fb96c99e1caa4af178dd2d9d982b0b670947cb5
  * Posteriormente agrega a la mascota recien registrada a la lista de mascotas del duenio y del centro de rescate.
  */
  public void registrarMascota(MascotaRegistrada mascota, CentroDeRescate centroDeRescate){
    this.mascotasID.add(centroDeRescate.registrarMascota(mascota));
  }

  public boolean tieneA(String IDMascota){
    return this.getMascotasID().contains(IDMascota);
  }

  /**
  * seEncontro(1)
  * Notifica al duenio de que se encontro una de sus mascotas (tal vez deberia verificarse que mascota pertenezca al duenio)
  */
  public void seEncontro(MascotaRegistrada unaMascota) {
    //TODO Comportamiento no definido, se hace "notificacion".

  }

  public void registrarse() {
    repositorioUsuarios.registrarDuenio(this);
  }

  public String getNombreUsuario() {
    return nombreDeUsuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }
}
