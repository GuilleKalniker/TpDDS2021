package domain.Sistema;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Persona.Duenio;
import domain.Publicacion.SolicitudPublicacion;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Repositorio.RepositorioMascotas;
import domain.Repositorio.RepositorioUsuarios;
import domain.Servicios.ClasesParaLaConsulta.HogarTransito;
import domain.Servicios.Notificadores.JavaMailApi;
import domain.Servicios.Notificadores.Notificador;
import domain.Servicios.ServicioHogaresTransito;

import java.util.List;

public class CentroDeRescate {

  private String correoDelCentro = "centrodemascotasdds@gmail.com";
  private String contrasenia_correo = "tpdds2021";
  private Ubicacion ubicacion;
  private List<SolicitudPublicacion> solicitudesPublicacion;
  private List<PublicacionMascotaPerdida> publicacionesMascotaPerdidasSinID;

  private Notificador notificador = new JavaMailApi(this.correoDelCentro, this.contrasenia_correo);
  private RepositorioCentroDeRescate repositorioCentroDeRescate = RepositorioCentroDeRescate.getInstance();
  private RepositorioMascotas repositorioMascotas = RepositorioMascotas.getInstance();
  private RepositorioUsuarios repositorioUsuarios = RepositorioUsuarios.getInstance();
  private ServicioHogaresTransito servicioHogaresTransito = ServicioHogaresTransito.getInstance();

  public CentroDeRescate(Ubicacion ubicacion) {
    this.ubicacion = ubicacion;
    repositorioCentroDeRescate.registrarCentroDeRescate(this);
  }

  public void setRepositorioCentroDeRescate(RepositorioCentroDeRescate repositorioCentroDeRescate) { // Para posibilitar mockeo
    this.repositorioCentroDeRescate = repositorioCentroDeRescate;
  }

  public void setRepositorioMascotas(RepositorioMascotas repositorioMascotas) { // Para posibilitar mockeo
    this.repositorioMascotas = repositorioMascotas;
  }

  public void setRepositorioUsuarios(RepositorioUsuarios repositorioUsuarios) {
    this.repositorioUsuarios = repositorioUsuarios;
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

  /** FUNCIONES PARA MASCOTAS REGISTRADAS */

  public String registrarMascota(MascotaRegistrada mascota){
    return repositorioMascotas.registrarMascota(mascota);
  }

  public MascotaRegistrada buscarMascota(String ID){
    return repositorioMascotas.buscarMascotaPorID(ID);
  }

  public Boolean existeMascota(String ID) {
    return repositorioMascotas.existeMascota(ID);
  }

  /** FUNCIONES PARA MASCOTAS PERDIDAS*/

  public void cargarMascotaPerdida(FormularioMascotaPerdida formularioMascotaPerdida) {
    repositorioMascotas.agregarDatosMascotaPerdida(formularioMascotaPerdida);

    try{
      notificar(buscarDuenioApartirIDMascota(formularioMascotaPerdida.getIDMascotaPerdida()), formularioMascotaPerdida);
    } catch (RuntimeException e) {
      e.printStackTrace();
    }
  }

  public void notificar(Duenio duenio, FormularioMascotaPerdida formularioMascotaPerdida){
    notificador.notificar(duenio, formularioMascotaPerdida);
  }

  /** FUNCIONES QUE SE COMUNICAN CON EL ADAPATER DE REPOSITORIO USUARIOS */

  public Duenio buscarDuenioApartirIDMascota(String ID){
    return repositorioUsuarios.getDueniosRegistrados().stream().filter(duenio -> duenio.tieneA(ID)).findFirst().get();
  }

  public List<HogarTransito> solicitarListaHogaresDeTransito() {
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

  public List<HogarTransito> hogaresAdecuadosParaMascota(FormularioMascotaPerdida formulario, Double radio){
    return servicioHogaresTransito.filtrarHogaresPara(formulario, radio);
  }

  public void publicacionMatcheada(PublicacionMascotaPerdida publicacionMascotaPerdida,DatosPersonales datosPersonales, Notificador notificador){
  //TODO: notificador.notificarARescatista();  -> Dale Willy trabajá *LATIGO*
  }

}