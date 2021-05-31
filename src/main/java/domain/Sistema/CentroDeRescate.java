package domain.Sistema;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.DatosMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.Duenio;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Repositorio.RepositorioDuenios;
import domain.Repositorio.RepositorioMascotas;
import domain.Servicios.ClasesParaLaConsulta.HogarTransito;
import domain.Servicios.Notificadores.JavaMailApi;
import domain.Servicios.Notificadores.Notificador;
import domain.Servicios.ServicioHogaresTransito;

import java.lang.invoke.SerializedLambda;
import java.util.List;

public class CentroDeRescate {

  private String correoDelCentro = "centrodemascotasdds@gmail.com";
  private String contrasenia_correo = "tpdds2021";
  private Ubicacion ubicacion;

  public CentroDeRescate(Ubicacion ubicacion) {
    this.ubicacion = ubicacion;
    RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(this);
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

  public void agregarDatosMascotaPerdida(DatosMascotaPerdida datosMascotaPerdida) {
    RepositorioMascotas.getInstance().agregarDatosMascotaPerdida(datosMascotaPerdida);
    /*
    try{
      this.notificar(
          this.buscarDuenioApartirIDMascota(datosMascotaPerdida.getIDMascotaPerdida()),
          datosMascotaPerdida,
          new JavaMailApi(this.correoDelCentro, this.contrasenia_correo)
      );
    } catch (RuntimeException e) {
      e.printStackTrace();
    }
     */

  }

  public void notificar(Duenio duenio, DatosMascotaPerdida datosMascotaPerdida, Notificador notificador){
    notificador.notificar(duenio, datosMascotaPerdida);
  }

  /** FUNCIONES QUE SE COMUNICAN CON EL ADAPATER DE REPOSITORIO USUARIOS */

  public Duenio buscarDuenioApartirIDMascota(String ID){
    return RepositorioDuenios.getInstance().getDueniosRegistrados().stream().filter(duenio -> duenio.tieneA(ID)).findFirst().get();
  }

  /**
  * notificarMascotaEncontrada(1)
  * Obtiene una mascota a partir del ID registrado en el estado y luego notifica al duenio.
  public void notificarMascotaEncontrada(DatosMascotaPerdida datosMascotaPerdida) {
    MascotaRegistrada mascota = this.buscarMascota(datosMascotaPerdida.getIDMascotaPerdida());
    Duenio duenioMascota = buscarDuenioApartirIDMascota(mascota.getID());
    duenioMascota.seEncontro(mascota);
    //TODO esta bien que al notificar se elimine los datos de la mascota perdida?
    RepositorioMascotas.getInstance().eliminarDatosMascotaPerdida(datosMascotaPerdida.getIDMascotaPerdida());
  }*/

  //TODO armar un mensaje y enviarselo a algun dato de contacto del dueño
  //public void notificarAlDuenio(Duenio duenio, MascotaRegistrada mascota);

  /**
  * notificarMascotasDeLosUltimos10Dias()
  * Filtra la lista de datosMascotaPerdida y le notifica a sus dueños que fueron encontradas
  */
  public void notificarMascotasDeLosUltimos10Dias(){
    this.listaDatosMascotasPerdidasUltimosDiezDias()
                    .stream()
                    .forEach(unDatoMascotaPerdida -> this.notificar(this.buscarDuenioApartirIDMascota(unDatoMascotaPerdida.getIDMascotaPerdida()),
                            unDatoMascotaPerdida,
                            new JavaMailApi(this.correoDelCentro, this.contrasenia_correo)));
  }

  public List<DatosMascotaPerdida> listaDatosMascotasPerdidasUltimosDiezDias() {
    return RepositorioMascotas.getInstance().datosMascotasPerdidasEnUltimosDiezDias();
  }

  public List<HogarTransito> solicitarListaHogaresDeTransito() {
    return ServicioHogaresTransito.getInstance().solicitarTodosLosHogares();
  }
}

