package domain.Persona;


import Funciones.StringDate;
import domain.Exceptions.IDNoSeCorrespondeException;
import domain.Exceptions.RespuestasIncompletasException;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Pregunta.Pregunta;
import domain.Pregunta.PreguntaResuelta;
import domain.Publicacion.PublicacionAdoptante;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioMascotas;
import domain.Repositorio.RepositorioUsuarios;
import domain.Servicios.Notificadores.Mail.Mensaje;
import domain.Servicios.Notificadores.Notificador;
import domain.Sistema.CentroDeRescate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue("duenio")
public class Duenio extends Usuario {
  @Embedded
  private DatosPersonales datosPersonales;
  @OneToMany(mappedBy = "duenio")
  private List<MascotaRegistrada> mascotas = new ArrayList<>();

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

  public String getNombre() { return getDatosPersonales().getNombre();}

  public String getApellido() { return getDatosPersonales().getApellido();}

  public String getFechaNacimiento() {
    return StringDate.localDateToString(datosPersonales.getFechaDeNacimiento());
  }

  public List<MascotaRegistrada> getMascotas() {
    return mascotas;
  }

  public void setNotificadores(List<Notificador> notificadores) {
    this.notificadores = notificadores;
  }

  /**
  * registrarMascota(1)
  * Agrega a la mascota recien registrada a la lista de mascotas del duenio y del centro de rescate.
  */
  public void registrarMascota(MascotaRegistrada mascota){
    RepositorioMascotas.getInstance().registrarMascota(mascota);
    this.mascotas.add(mascota);
    mascota.setDuenio(this);

  }

  public boolean tieneA(long IDMascota){
    return this.getMascotas().stream().map(mascota -> mascota.getID()).collect(Collectors.toList()).contains(IDMascota);
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

  public void darEnAdopcionA(MascotaRegistrada mascota, CentroDeRescate centroDeRescate, List<PreguntaResuelta> preguntasResueltas) {
    if (!mascotas.contains(mascota)) {
      throw new IDNoSeCorrespondeException("El ID de la mascota enviado no pertenece a una mascota propia");
    }

    centroDeRescate.generarPublicacionAdopcion(preguntasResueltas, mascota);
  }

  public void contestarPregunta(Pregunta pregunta) {
    // TODO: Conseguir la respuesta (GUI)
    // pregunta.setRespuesta(respuesta);
  }

  public void mostrarIntencionDeAdopcion(CentroDeRescate centroDeRescate) {
    PublicacionAdoptante publicacionAdoptante = new PublicacionAdoptante(this);
    centroDeRescate.nuevoInteresadoEnAdoptar(publicacionAdoptante);
  }
}
