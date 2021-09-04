package domain.Sistema;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.Duenio;
import domain.Pregunta.Pregunta;
import domain.Publicacion.PublicacionAdopcion;
import domain.Publicacion.PublicacionAdoptante;
import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Publicacion.SolicitudPublicacion;
import domain.Repositorio.*;
import domain.Servicios.HogarTransitoAdaptado;
import domain.Servicios.Notificadores.JavaMailApi;
import domain.Servicios.Notificadores.Notificador;
import domain.Servicios.ServicioHogaresTransito;
import domain.Exceptions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CentroDeRescate {

  private Ubicacion ubicacion;
  private List<SolicitudPublicacion> solicitudesPublicacion = new ArrayList<>();
  private List<PublicacionMascotaPerdida> publicacionesMascotaPerdidasSinID = new ArrayList<>();

  private List<Pregunta> preguntasDeAdopcion = new ArrayList<>();
  private List<PublicacionAdopcion> publicacionesAdopcion = new ArrayList<>(); // Podrian estar en repo si son independientes de centro
  private List<PublicacionAdoptante> interesadosEnAdoptar = new ArrayList<>(); // Observers

  private Notificador notificador = new JavaMailApi(); //Creo que hay que quitarlo ya
  private ServicioHogaresTransito servicioHogaresTransito = ServicioHogaresTransito.getInstance();


  public CentroDeRescate(Ubicacion ubicacion) {
    this.ubicacion = ubicacion;
    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(this);
  }

  public void setServicioHogaresTransito(ServicioHogaresTransito servicioHogaresTransito) {
    this.servicioHogaresTransito = servicioHogaresTransito;
  }

  public void setNotificador(Notificador notificador) {
    this.notificador = notificador;
  }

  public List<SolicitudPublicacion> getSolicitudesPublicacion() {
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
    return preguntasDeAdopcion;
  }

  public List<PublicacionAdopcion> getPublicacionesAdopcion() {
    return publicacionesAdopcion;
  }

  /** FUNCIONES PARA MASCOTAS REGISTRADAS */

  public MascotaRegistrada buscarMascota(String ID){
    return RepositorioMascotas.getInstance().buscarMascotaPorID(ID);
  }

  public Boolean existeMascota(String ID) {
    return RepositorioMascotas.getInstance().existeMascota(ID);
  }

  /** FUNCIONES QUE SE COMUNICAN CON EL ADAPATER DE REPOSITORIO USUARIOS */

  public Duenio buscarDuenioApartirIDMascota(String ID){ // TODO: Pensar si un try-catch tiene sentido
    return RepositorioUsuarios.getInstance().getDuenioPorID(ID);
  }

  public List<HogarTransitoAdaptado> solicitarListaHogaresDeTransito() {
    return servicioHogaresTransito.solicitarTodosLosHogares();
  }

  /** FUNCIONES QUE SE COMUNICAN CON EL COMMAND DE REPOSITORIO USUARIOS */

  public void aceptarSolicitud(SolicitudPublicacion solicitudPublicacion){
    this.getPublicacionesMascotaPerdidasSinID().add(solicitudPublicacion.getPublicacion());
    eliminarSolicitud(solicitudPublicacion);
  }

  public void eliminarSolicitud(SolicitudPublicacion solicitud){
    getSolicitudesPublicacion().remove(solicitud);
  }

  public void generarSolicitud(SolicitudPublicacion solicitud){
    getSolicitudesPublicacion().add(solicitud);
  }

  public List<HogarTransitoAdaptado> hogaresAdecuadosParaMascota(FormularioMascotaPerdida formulario, Double radio){
    return servicioHogaresTransito.filtrarHogaresPara(formulario, radio);
  }

  public void publicacionMascotaPerdidaMatcheada(DatosPersonales datosDuenio, PublicacionMascotaPerdida publicacionMascotaPerdida){
    DatosPersonales datosRescatista = publicacionMascotaPerdida.getFormularioMascotaPerdida().getDatosRescastista();
    notificador.notificarRescatista(datosRescatista, datosDuenio);
  }

  public void publicacionAdopcionMatcheada(DatosPersonales datosAdoptante, PublicacionAdopcion publicacionAdopcion) {
    Duenio duenio = buscarDuenioApartirIDMascota(publicacionAdopcion.getId());
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

  public void generarPublicacionAdopcion(List<Pregunta> preguntasRespondidas, String id) {
    publicacionesAdopcion.add(new PublicacionAdopcion(preguntasRespondidas, id));
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