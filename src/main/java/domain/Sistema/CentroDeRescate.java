package domain.Sistema;

import domain.Exceptions.NoHayPublicacionAptaException;
import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Publicacion.PublicacionAdopcion;
import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Persona.Duenio;
import domain.Publicacion.SolicitudPublicacion;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Repositorio.RepositorioMascotas;
import domain.Repositorio.RepositorioUsuarios;
import domain.Servicios.HogarTransitoAdaptado;
import domain.Servicios.Notificadores.JavaMailApi;
import domain.Servicios.Notificadores.Notificador;
import domain.Servicios.ServicioHogaresTransito;

import java.util.ArrayList;
import java.util.List;

public class CentroDeRescate {

  private String correoDelCentro = "centrodemascotasdds@gmail.com";
  private String contrasenia_correo = "tpdds2021";
  private Ubicacion ubicacion;
  private List<SolicitudPublicacion> solicitudesPublicacion = new ArrayList<>();
  private List<PublicacionMascotaPerdida> publicacionesMascotaPerdidasSinID = new ArrayList<>();

  // TODO: Revisar
  private List<String> preguntasDeAdopcion = new ArrayList<>();
  private List<PublicacionAdopcion>  publicacionesAdopcion = new ArrayList<>(); // Podrian estar en repo si son independientes de centro
  private List<Duenio> interesadosEnAdoptar = new ArrayList<>(); // Observers

  private Notificador notificador = new JavaMailApi(this.correoDelCentro, this.contrasenia_correo);
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

  public List<String> getPreguntasDeAdopcion() {
    return preguntasDeAdopcion;
  }

  /** FUNCIONES PARA MASCOTAS REGISTRADAS */

  public String registrarMascota(MascotaRegistrada mascota){
    return RepositorioMascotas.getInstance().registrarMascota(mascota);
  }

  public MascotaRegistrada buscarMascota(String ID){
    return RepositorioMascotas.getInstance().buscarMascotaPorID(ID);
  }

  public Boolean existeMascota(String ID) {
    return RepositorioMascotas.getInstance().existeMascota(ID);
  }

  /** FUNCIONES PARA MASCOTAS PERDIDAS*/

  public void cargarMascotaPerdida(FormularioMascotaPerdida formularioMascotaPerdida) {
    RepositorioMascotas.getInstance().agregarDatosMascotaPerdida(formularioMascotaPerdida);

    try{
      notificar(buscarDuenioApartirIDMascota(formularioMascotaPerdida.getIDMascotaPerdida()), formularioMascotaPerdida);
    } catch (RuntimeException e) {
      e.printStackTrace();
    }
  }

  public void notificar(Duenio duenio, FormularioMascotaPerdida formularioMascotaPerdida){
    notificador.notificarDuenio(duenio, formularioMascotaPerdida);
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
  public void publicacionMatcheada(DatosPersonales datosDuenio, PublicacionMascotaPerdida publicacionMascotaPerdida){
    DatosPersonales datosRescatista = publicacionMascotaPerdida.getFormularioMascotaPerdida().getDatosRescastista();
    notificador.notificarRescatista(datosRescatista, datosDuenio);
  }

  /** FUNCIONES PARA EL MANEJO DE ADOPCIONES */
/*
  public void agregarPregunta(String pregunta) {
    preguntasDeAdopcion.add(pregunta);
  }

  public void quitarPregunta(String pregunta) {
    preguntasDeAdopcion.remove(pregunta);
  }

  public void nuevoInteresadoEnAdoptar(Duenio duenio) {
    interesadosEnAdoptar.add(duenio);
  }

  public void quitarInteresadoEnAdoptar(Duenio duenio) {
    interesadosEnAdoptar.remove(duenio);
  }

  public boolean validarPreguntas(respuestas) {
    // TODO: Implementar
  }
  */

  public void generarPublicacionAdopcion() {
    publicacionesAdopcion.add(new PublicacionAdopcion()); // TODO: Implementar constructor
  }
/*
  public void enviarSugerenciaDeAdopcionSemanal() {
    interesadosEnAdoptar.forEach(duenio -> duenio.recibirSugerenciaAdopcion(obtenerSugerenciaAdopcionPara(duenio)));
  }*/
  /*
  public PublicacionAdopcion obtenerSugerenciaAdopcionPara(Duenio duenio) {
    publicacionesAdopcion.
        stream().
        filter(publicacion -> duenio.mascotaSeriaApta(publicacion)).
        findFirst().
        orElseThrow(() -> new NoHayPublicacionAptaException());
  }*/
}