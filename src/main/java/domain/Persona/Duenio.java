package domain.Persona;


import domain.Exceptions.IDNoSeCorrespondeException;
import domain.Exceptions.RespuestasIncompletasException;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Repositorio.RepositorioMascotas;
import domain.Repositorio.RepositorioUsuarios;
import domain.Sistema.CentroDeRescate;

import java.util.ArrayList;
import java.util.List;

public class Duenio{

  private String nombreDeUsuario;
  private String contrasenia;
  private DatosPersonales datosPersonales;
  private List<String> mascotasID = new ArrayList<>();

  public Duenio(String usuario, String contrasenia,DatosPersonales datosPersonales) {
    this.nombreDeUsuario = usuario;
    this.contrasenia = contrasenia;
    this.datosPersonales = datosPersonales;
  }

  public String getNombreUsuario() {
    return nombreDeUsuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public DatosPersonales getDatosPersonales() {
    return datosPersonales;
  }

  public List<String> getMascotasID() {
    return this.mascotasID;
  }

  public void registrarse() {
    RepositorioUsuarios.getInstance().registrarDuenio(this);
  }
  /*
  * registrarMascota(1)
  * Agrega a la mascota recien registrada a la lista de mascotas del duenio y del centro de rescate.
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

  public void darEnAdopcionA(String ID) {
    if (mascotasID.contains(ID)) {
      centroDeRescate.getPreguntasDeAdopcion(); // TODO: Inyectar centro de rescate, ver criterio
      // TODO: Contestar preguntas
        if (centroDeRescate.validarPreguntas(respuestas)) {
          centroDeRescate(generarPublicacionAdopcion(RepositorioMascotas.getInstance().buscarMascotaPorID(ID)));
        } else {
          throw new RespuestasIncompletasException();
        }
    } else {
      throw new IDNoSeCorrespondeException();
    }
  }
}
