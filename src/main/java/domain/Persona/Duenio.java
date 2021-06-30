package domain.Persona;


import domain.Exceptions.IDNoSeCorrespondeException;
import domain.Exceptions.RespuestasIncompletasException;
import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Pregunta.Pregunta;
import domain.Publicacion.PublicacionAdopcion;
import domain.Publicacion.PublicacionAdoptante;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Repositorio.RepositorioMascotas;
import domain.Repositorio.RepositorioUsuarios;
import domain.Servicios.Notificadores.Mail.Mensaje;
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
    return mascotasID;
  }

  public void registrarse() {
    RepositorioUsuarios.getInstance().registrarDuenio(this);
  }

  /**
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

  /**vision de observers sobre el duenio**/
  public void notificarContactos(String asunto, String texto){
    notificadores.forEach(notificador ->{
      this.getDatosPersonales().getContactos().forEach(contacto -> {
        Mensaje mensaje = new Mensaje(contacto, asunto, texto);
        notificador.notificar(mensaje);
      });
    });
  }

  public void agregarNotificador(Notificador notificador) {
    notificadores.add(notificador);
  }

  public void quitarNotificador(Notificador notificador) {
    notificadores.remove(notificador);
  }

  public void notificarme(String asunto, String texto){
    notificadores.forEach(notificador ->{
      Mensaje mensaje = new Mensaje(this.datosPersonales.getContactos().get(0), asunto, texto );
      notificador.notificar(mensaje);
    });
  }

  public void darEnAdopcionA(String ID, CentroDeRescate centroDeRescate) {
    if (mascotasID.contains(ID)) {

      List<Pregunta> preguntasCentro = centroDeRescate.getPreguntasDeAdopcion();
      preguntasCentro.forEach(pregunta -> {contestarPregunta(pregunta);});

      if (preguntasCentro.stream().allMatch(pregunta -> pregunta.esValida())) {
          centroDeRescate.generarPublicacionAdopcion(preguntasCentro, ID);
      } else {
          throw new RespuestasIncompletasException();
      }
    } else {
      throw new IDNoSeCorrespondeException("El ID de la mascota enviado no pertenece a una mascota propia.");
    }
  }

  public void contestarPregunta(Pregunta pregunta) {
    // TODO: Conseguir la respuesta (GUI)
    // pregunta.setRespuesta(respuesta);
  }

  public void mostrarIntencionDeAdopcion(CentroDeRescate centroDeRescate) {
    PublicacionAdoptante publicacionAdoptante = new PublicacionAdoptante(this, centroDeRescate);
    centroDeRescate.nuevoInteresadoEnAdoptar(publicacionAdoptante);
  }
}
