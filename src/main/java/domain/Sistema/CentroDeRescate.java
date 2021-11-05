package domain.Sistema;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.Duenio;
import domain.Pregunta.Abierta;
import domain.Pregunta.Booleana;
import domain.Pregunta.OpcionMultiple;
import domain.Pregunta.Pregunta;
import domain.Pregunta.PreguntaResuelta;
import domain.Publicacion.PublicacionAdopcion;
import domain.Publicacion.PublicacionAdoptante;
import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Repositorio.*;
import domain.Servicios.HogarTransitoAdaptado;
import domain.Servicios.Notificadores.JavaMailApi;
import domain.Servicios.Notificadores.Notificador;
import domain.Servicios.ServicioHogaresTransito;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "centroDeRescate")
public class CentroDeRescate {
  @Id
  @GeneratedValue
  private long id;

  @Embedded
  private Ubicacion ubicacion;

  @OneToMany(mappedBy = "centro")
  private List<PublicacionMascotaPerdida> solicitudesPublicacion = new ArrayList<>(); //Los aceptan los voluntarios

  @OneToMany(mappedBy = "centro")
  private List<PublicacionMascotaPerdida> publicacionesMascotaPerdidasSinID = new ArrayList<>();

  @OneToMany(mappedBy = "centro")
  private List<Pregunta> preguntasDeAdopcion = new ArrayList<>();

  //@OneToMany(mappedBy = "centro")
  @Transient
  private List<PublicacionAdopcion> publicacionesAdopcion = new ArrayList<>(); // Podrian estar en repo si son independientes de centro

  @Transient
  private List<PublicacionAdoptante> interesadosEnAdoptar = new ArrayList<>(); // Observers

  @Transient
  private Notificador notificador = new JavaMailApi(); //Creo que hay que quitarlo ya

  @Transient
  private ServicioHogaresTransito servicioHogaresTransito = ServicioHogaresTransito.getInstance();

  public CentroDeRescate(Ubicacion ubicacion) {
    this.ubicacion = ubicacion;
  }

  public CentroDeRescate() {}

  public void setUbicacion(Ubicacion ubicacion) {
    this.ubicacion = ubicacion;
  }

  public long getId() {
    return id;
  }

  public void setServicioHogaresTransito(ServicioHogaresTransito servicioHogaresTransito) {
    this.servicioHogaresTransito = servicioHogaresTransito;
  }

  public void setNotificador(Notificador notificador) {
    this.notificador = notificador;
  }

  public List<PublicacionMascotaPerdida> getSolicitudesPublicacion() {
    return solicitudesPublicacion;
  }

  public List<PublicacionMascotaPerdida> getPublicacionesMascotaPerdidasSinID() {
    return publicacionesMascotaPerdidasSinID;
  }

  public Ubicacion getUbicacion() {
    return ubicacion;
  }

  //Devuelve las propias + las obligatorias
  public List<Pregunta> getPreguntasDeAdopcion() {
    List<Pregunta> totalPreguntas = new ArrayList<>();
    totalPreguntas.addAll(RepositorioPreguntasObligatorias.getInstance().getPreguntas());
    totalPreguntas.addAll(preguntasDeAdopcion);
    return totalPreguntas;
  }

  public List<PublicacionAdopcion> getPublicacionesAdopcion() {
    return publicacionesAdopcion;
  }

  public List<PublicacionAdoptante> getInteresadosEnAdoptar() {
    return interesadosEnAdoptar;
  }

  /** FUNCIONES PARA MASCOTAS REGISTRADAS */

  public MascotaRegistrada buscarMascota(long ID){
    return RepositorioMascotas.getInstance().buscarMascotaPorID(ID);
  }

  public Boolean existeMascota(long ID) {
    return RepositorioMascotas.getInstance().existeMascota(ID);
  }

  /** FUNCIONES QUE SE COMUNICAN CON EL ADAPATER DE REPOSITORIO USUARIOS */

  public Duenio buscarDuenioApartirIDMascota(long ID){ // TODO: Pensar si un try-catch tiene sentido
    return RepositorioUsuarios.getInstance().getDuenioPorID(ID);
  }

  public List<HogarTransitoAdaptado> solicitarListaHogaresDeTransito() {
    return servicioHogaresTransito.solicitarTodosLosHogares();
  }

  /** FUNCIONES QUE SE COMUNICAN CON EL COMMAND DE REPOSITORIO USUARIOS */

  public void aceptarSolicitud(PublicacionMascotaPerdida solicitud){
    this.getPublicacionesMascotaPerdidasSinID().add(solicitud);
    eliminarSolicitud(solicitud);
  }

  public void eliminarSolicitud(PublicacionMascotaPerdida solicitud){
    getSolicitudesPublicacion().remove(solicitud);
  }

  public PublicacionMascotaPerdida generarSolicitud(PublicacionMascotaPerdida solicitud){
    getSolicitudesPublicacion().add(solicitud);
    solicitud.setCentro(this);
    return solicitud;
  }

  public List<HogarTransitoAdaptado> hogaresAdecuadosParaMascota(FormularioMascotaPerdida formulario, Double radio){
    return servicioHogaresTransito.filtrarHogaresPara(formulario, radio);
  }

  public void publicacionMascotaPerdidaMatcheada(DatosPersonales datosDuenio, PublicacionMascotaPerdida publicacionMascotaPerdida){
    DatosPersonales datosRescatista = publicacionMascotaPerdida.getFormularioMascotaPerdida().getDatosRescastista();
    notificador.notificarRescatista(datosRescatista, datosDuenio);
  }

  public void publicacionAdopcionMatcheada(DatosPersonales datosAdoptante, PublicacionAdopcion publicacionAdopcion) {
    Duenio duenio = buscarDuenioApartirIDMascota(publicacionAdopcion.getID());
    duenio.notificar("A un adoptante le interesó su publicacion de adopción de su mascota...",
        "El adoptante se llama" + datosAdoptante.getNombre() + datosAdoptante.getApellido()); // Deberia pasarle contacto
  }

  public void seConcretoAdopcion(PublicacionAdopcion publicacionAdopcion) {
    publicacionesAdopcion.remove(publicacionAdopcion);
  }

  /** FUNCIONES PARA EL MANEJO DE ADOPCIONES */
  public void agregarPregunta(Pregunta pregunta) {
    preguntasDeAdopcion.add(pregunta);
  }

  public void quitarPregunta(Pregunta pregunta) {
    preguntasDeAdopcion.remove(pregunta);
  }

  public void nuevoInteresadoEnAdoptar(PublicacionAdoptante adoptante) {
    interesadosEnAdoptar.add(adoptante);
  }

  public void quitarInteresadoEnAdoptar(PublicacionAdoptante adoptante) {
    interesadosEnAdoptar.remove(adoptante);
  }

  public void generarPublicacionAdopcion(List<PreguntaResuelta> preguntasRespondidas, MascotaRegistrada mascota) {
    PublicacionAdopcion publicacion = new PublicacionAdopcion(preguntasRespondidas, mascota);
    publicacionesAdopcion.add(publicacion);
    RepositorioCentroDeRescate.getInstance().registrarPublicacionAdopcion(publicacion);
  }

  public void notificacionSemanal() {
    interesadosEnAdoptar.forEach(interesado -> {
      interesado.recibirSugerenciaAdopcion(
          filtrarPublicaciones(interesado.getPreferencias())
              .stream()
              .findFirst()
              .orElse(null));
    });
  }

  public List<PublicacionAdopcion> filtrarPublicaciones(List<String> filtros) {
    return this.publicacionesAdopcion.stream()
        .filter(publicacionAdopcion -> publicacionAdopcion.matcheaConTodosFiltros(filtros))
        .collect(Collectors.toList());
  }
}