package domain.Sistema;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Persona.Duenio;
import domain.Publicacion.SolicitudPublicacion;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Repositorio.RepositorioDuenios;
import domain.Repositorio.RepositorioMascotas;
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

  public CentroDeRescate(Ubicacion ubicacion) {
    this.ubicacion = ubicacion;
    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(this);
  }

  public List<SolicitudPublicacion> getSolicitudPublicacion() {
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
      this.notificar(
          this.buscarDuenioApartirIDMascota(formularioMascotaPerdida.getIDMascotaPerdida()),
          formularioMascotaPerdida,
          new JavaMailApi(this.correoDelCentro, this.contrasenia_correo)
      );
    } catch (RuntimeException e) {
      e.printStackTrace();
    }
  }

  public void notificar(Duenio duenio, FormularioMascotaPerdida formularioMascotaPerdida, Notificador notificador){
    notificador.notificar(duenio, formularioMascotaPerdida);
  }

  /** FUNCIONES QUE SE COMUNICAN CON EL ADAPATER DE REPOSITORIO USUARIOS */

  public Duenio buscarDuenioApartirIDMascota(String ID){
    return RepositorioDuenios.getInstance().getDueniosRegistrados().stream().filter(duenio -> duenio.tieneA(ID)).findFirst().get();
  }

  public List<HogarTransito> solicitarListaHogaresDeTransito() {
    return ServicioHogaresTransito.getInstance().solicitarTodosLosHogares();
  }

  /** FUNCIONES QUE SE COMUNICAN CON EL COMMAND DE REPOSITORIO USUARIOS */

  public void aceptarSolicitud(SolicitudPublicacion solicitudPublicacion){
    this.getPublicacionesMascotaPerdidasSinID().add(solicitudPublicacion.getPublicacion());
    eliminarSolicitud(solicitudPublicacion);
  }

  public void eliminarSolicitud(SolicitudPublicacion solicitud){
    getSolicitudPublicacion().remove(solicitud);
  }

  public void generarSolicitud(SolicitudPublicacion solicitud){
    getSolicitudPublicacion().add(solicitud);
  }
}