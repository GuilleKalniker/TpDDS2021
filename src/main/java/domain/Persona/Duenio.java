package domain.Persona;


import Funciones.ValidadorContrasenias;
import domain.Exceptions.ContraseniaInvalidaException;
import domain.Exceptions.IDNoSeCorrespondeException;
import domain.Exceptions.RespuestasIncompletasException;
import domain.Exceptions.UsuarioYaRegistradoException;
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

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("duenio")
public class Duenio extends Usuario {

  @Embedded
  private DatosPersonales datosPersonales;
  @Transient
  private List<String> mascotasID = new ArrayList<>();
  @Transient
  private List<Notificador> notificadores = new ArrayList<>();


  public Duenio(String usuario, String contrasenia, DatosPersonales datosPersonales) {
    super(usuario, contrasenia);
    this.datosPersonales = datosPersonales;
  }

  public Duenio() {}

  @Override
  public void registrarse() {
    RepositorioUsuarios.getInstance().registrarUsuario(this);
    datosPersonales.getContactos().forEach(contacto -> {
      RepositorioUsuarios.getInstance().persistirContacto(contacto);
      contacto.setDuenio(this);});
  }

  public DatosPersonales getDatosPersonales() {
    return datosPersonales;
  }

  public List<String> getMascotasID() {
    return mascotasID;
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
  public void agregarNotificador(Notificador notificador) {
    notificadores.add(notificador);
  }

  public void quitarNotificador(Notificador notificador) {
    notificadores.remove(notificador);
  }

  public void notificarContactos(String asunto, String texto){
    notificadores.forEach(notificador ->{
      this.getDatosPersonales().getContactos().forEach(contacto -> {
        Mensaje mensaje = new Mensaje(contacto, asunto, texto);
        notificador.notificar(mensaje);
      });
    });
  }

  public void notificar(String asunto, String texto){
    notificadores.forEach(notificador ->{
      Mensaje mensaje = new Mensaje(this.datosPersonales.getContactos().get(0), asunto, texto );
      notificador.notificar(mensaje);
    });
  }

  public void darEnAdopcionA(String ID, CentroDeRescate centroDeRescate) {
    if (!mascotasID.contains(ID)) {
      throw new IDNoSeCorrespondeException("El ID de la mascota enviado no pertenece a una mascota propia");
    }

    List<Pregunta> preguntasCentro = centroDeRescate.getPreguntasDeAdopcion();
    preguntasCentro.forEach(pregunta -> {
      contestarPregunta(pregunta);
    });

    if (!preguntasCentro.stream().allMatch(pregunta -> pregunta.esValida())) {
      throw new RespuestasIncompletasException();
    }

    centroDeRescate.generarPublicacionAdopcion(preguntasCentro, ID);
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
