package domain.Persona;


import domain.Exceptions.IDNoSeCorrespondeException;
import domain.Exceptions.RespuestasIncompletasException;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Publicacion.PublicacionAdopcion;
import domain.Repositorio.RepositorioMascotas;
import domain.Repositorio.RepositorioUsuarios;
import domain.Servicios.Notificadores.Notificador;
import domain.Sistema.CentroDeRescate;

import java.util.ArrayList;
import java.util.List;

public class Duenio{

  private String nombreDeUsuario;
  private String contrasenia;
  private DatosPersonales datosPersonales;
  private List<String> mascotasID = new ArrayList<>();

  private List<Notificador> notificadores = new ArrayList<>();

  // TODO: Revisar
  private List<PublicacionAdopcion> sugerenciasAdopcion = new ArrayList<>();
  //private Preferencias preferencias;
  //private Comodidades comodidades;

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
    this.mascotasID.add(RepositorioMascotas.getInstance().registrarMascota(mascota));
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

  public List<Notificador> getNotificadores() {
    return notificadores;
  }

  public void setNotificadores(List<Notificador> notificadores) {
    this.notificadores = notificadores;
  }

  public void serNotificado() {
    notificadores.forEach(notificador -> notificador(this, "holi"));
  }

  /*
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

  public void mostrarIntencionDeAdopcion() {
    // En vez de un duenio podria hacerse una clase nueva (publicacion o adoptador) pero ver abajo
    centroDeRescate.nuevoInteresadoEnAdoptar(this);
  }

  // Funcionalidad de querer adoptar podria ser otra clase, por ahora intente y es muy middleman
  // Si comodidades y preferencias lo maneja duenio y centro, no tiene sentido, si el manejos se hace
  // propio del adoptador puede ser, pero pareceria ser redundante
  public boolean mascotaSeriaApta(PublicacionAdopcion publicacionAdopcion) {
    return preferencias.cumple(publicacionAdopcion) && comodidades.cumple(publicacionAdopcion);
  }

  public void recibirSugerenciaAdopcion(PublicacionAdopcion publicacionAdopcion) {
    sugerenciasAdopcion.add(publicacionAdopcion);
  }

  public void revisarSugerencias() {
    // TODO: Implementar
  }*/
}
