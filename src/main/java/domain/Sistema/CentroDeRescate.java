package domain.Sistema;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.Duenio;
import domain.Pregunta.Pregunta;
import domain.Publicacion.PublicacionAdopcion;
import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Publicacion.SolicitudPublicacion;
import domain.Repositorio.*;
import domain.Servicios.HogarTransitoAdaptado;
import domain.Servicios.Notificadores.JavaMailApi;
import domain.Servicios.Notificadores.Notificador;
import domain.Servicios.ServicioHogaresTransito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CentroDeRescate {

  private Ubicacion ubicacion;
  private List<SolicitudPublicacion> solicitudesPublicacion = new ArrayList<>();
  private List<PublicacionMascotaPerdida> publicacionesMascotaPerdidasSinID = new ArrayList<>();

  // TODO: Revisar
  private List<Pregunta> preguntasDeAdopcion = new ArrayList<>();
  private List<PublicacionAdopcion> publicacionesAdopcion = new ArrayList<>(); // Podrian estar en repo si son independientes de centro
  private List<PublicacionAdopcion> publicacionesIntencionAdopcion = new ArrayList<>(); // Podrian estar en repo si son independientes de centro
  private List<Duenio> interesadosEnAdoptar = new ArrayList<>(); // Observers

  private Notificador notificador = new JavaMailApi();
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

  public List<Pregunta> getPreguntasDeAdopcion() {
    return preguntasDeAdopcion;
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

  //Requerimiento 5:
  public void publicacionMascotaPerdidaMatcheada(DatosPersonales datosDuenio, PublicacionMascotaPerdida publicacionMascotaPerdida){
    DatosPersonales datosRescatista = publicacionMascotaPerdida.getFormularioMascotaPerdida().getDatosRescastista();
    notificador.notificarRescatista(datosRescatista, datosDuenio);
  }

  public void publicacionAdopcionMatcheada(DatosPersonales datosAdoptante, PublicacionAdopcion publicacionAdopcion) {
    Duenio duenio = RepositorioUsuarios.getInstance().getDuenioPorUsuario(publicacionAdopcion.getId());
    duenio.notificarme("A un adoptante le interesó su publicacion de adopción de su mascota...",
        "El adoptante se llama" + datosAdoptante.getNombre() + datosAdoptante.getApellido());
    //TODO: Acá deberíamos eliminar la publicación directo o tiene que haber una confirmación por parte del duenio?
  }


  /** FUNCIONES PARA EL MANEJO DE ADOPCIONES */

  public void agregarPregunta(Pregunta pregunta) {
    preguntasDeAdopcion.add(pregunta);
  }

  public void quitarPregunta(Pregunta pregunta) {
    preguntasDeAdopcion.remove(pregunta);
  }

  public void nuevoInteresadoEnAdoptar(Duenio duenio) {
    interesadosEnAdoptar.add(duenio);
  }

  public void quitarInteresadoEnAdoptar(Duenio duenio) {
    interesadosEnAdoptar.remove(duenio);
  }
/*
  public boolean validarPreguntas(respuestas) {
    // TODO: Implementar
  }*/

  public void generarPublicacionAdopcion(List<Pregunta> preguntasRespondidas, String id) {
    publicacionesAdopcion.add(new PublicacionAdopcion(preguntasRespondidas, id));
  }

  public void generarPublicacionIntencionAdopcion(List<Pregunta> preguntasRespondidas, String Usuario) {
    publicacionesIntencionAdopcion.add(new PublicacionAdopcion(preguntasRespondidas, Usuario));
    Duenio duenio =  RepositorioUsuarios.getInstance().getDuenioPorID(Usuario);
    //TODO: intentar hacer funciones que generen el asunto y el texto
    duenio.notificarme("Link de baja de tu publicacion", "<Inserte link aki>");
  }

  public void notificacionSemanal(){

    this.publicacionesIntencionAdopcion.forEach(publicacion -> {
      List<String> filtros = publicacion.obtenerRespuestas();
      List<PublicacionAdopcion> publicacionAdopciones = this.filtrarPublicaciones(filtros);
      //String mensaje = armarMaensaje(publicacionAdopciones) //TODO: hacerlo :c
      RepositorioUsuarios.getInstance().getDuenioPorUsuario(publicacion.getId()).notificarme("Recomendacion semanal de adopcion", "mensaje");
    });
  }

  public List<PublicacionAdopcion> filtrarPublicaciones(List<String> filtros) {
    return this.publicacionesAdopcion.stream()
        .filter(publicacionAdopcion -> publicacionAdopcion.matcheaConTodosFiltros(filtros))
        .collect(Collectors.toList());
  }
  
}