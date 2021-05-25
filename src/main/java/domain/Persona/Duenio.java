package domain.Persona;


import domain.Mascota.MascotaRegistrada;
import domain.Repositorio.RepositorioDuenios;
import domain.Repositorio.RepositorioMascotas;

import java.util.ArrayList;
import java.util.List;

public class Duenio extends Usuario{
  DatosPersonales datosPersonales;
  private List<String> mascotasID = new ArrayList<>();

  public Duenio(String usuario, String contrasenia,DatosPersonales datosPersonales) {
    super(usuario,contrasenia);
    this.datosPersonales = datosPersonales;
    RepositorioDuenios.getInstance().registrarDuenio(this);
  }

  /**
  * registrarMascota(1)
  * Consigue del centro un ID nuevo para la mascota que se va a registrar, luego crea el objeto mascota con todos los parametros de la funcion + this, para que mascota conozca a su duenio
  * Posteriormente agrega a la mascota recien registrada a la lista de mascotas del duenio y del centro de rescate.
  */
  public void registrarMascota(MascotaRegistrada mascota){
    this.mascotasID.add(RepositorioMascotas.getInstance().registrarMascota(mascota));
  }

  public List<String> getMascotasID() {
    return mascotasID;
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

}
