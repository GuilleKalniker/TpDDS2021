package domain.Sistema;

import domain.Mascota.DatosMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.Duenio;
import domain.Repositorio.RepositorioDuenios;
import domain.Repositorio.RepositorioMascotas;
import domain.Servicios.Notificadores.JavaMailApi;
import domain.Servicios.Notificadores.Notificador;

import java.util.List;

public class CentroDeRescate {

  private String correoDelCentro = "centrodemascotasdds@gmail.com"; // Hay varios centros, no deberia estar hardcodead
  private String contraseniaCorreo = "tpdds2021"; // Same arriba
  private Notificador notificadorActual = new JavaMailApi(this.correoDelCentro, this.contraseniaCorreo); // Paso a hacerlo Strategy, queda harcodeado

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

  public void agregarDatosMascotaPerdida(DatosMascotaPerdida datosMascotaPerdida) { // Agrega y notifica a la vez, no deberia ser el caso
    RepositorioMascotas.getInstance().agregarDatosMascotaPerdida(datosMascotaPerdida);
    try {
      this.notificar(this.buscarDuenioApartirIDMascota(datosMascotaPerdida.getIDMascotaPerdida()), datosMascotaPerdida);
    }
    catch (RuntimeException e) {
      e.printStackTrace();
    }
  }

  public void notificar(Duenio duenio, DatosMascotaPerdida datosMascotaPerdida){
    notificadorActual.notificar(duenio, datosMascotaPerdida);
  }

  /** FUNCIONES QUE SE COMUNICAN CON EL ADAPATER DE REPOSITORIO USUARIOS */

  /**
  * notificarMascotaEncontrada(1)
  * Obtiene una mascota a partir del ID registrado en el estado y luego notifica al duenio.
  */
  public void notificarMascotaEncontrada(DatosMascotaPerdida datosMascotaPerdida) {
    MascotaRegistrada mascota = this.buscarMascota(datosMascotaPerdida.getIDMascotaPerdida());
    Duenio duenioMascota = buscarDuenioApartirIDMascota(mascota.getID());
    duenioMascota.seEncontro(mascota); // Deberia usar notificador

    //TODO esta bien que al notificar se elimine los datos de la mascota perdida?
    RepositorioMascotas.getInstance().eliminarDatosMascotaPerdida(datosMascotaPerdida.getIDMascotaPerdida());
  }

  public Duenio buscarDuenioApartirIDMascota(String ID){
    return RepositorioDuenios.getInstance().getDueniosRegistrados().stream().filter(duenio -> duenio.tieneA(ID)).findFirst().get();
  }

  //TODO armar un mensaje y enviarselo a algun dato de contacto del dueño
  //public void notificarAlDuenio(Duenio duenio, MascotaRegistrada mascota);

  /**
  * notificarMascotasDeLosUltimos10Dias()
  * Filtra la lista de datosMascotaPerdida y le notifica a sus dueños que fueron encontradas
  */
  public void notificarMascotasDeLosUltimos10Dias(){
    this.listaDatosMascotasPerdidasUltimosDiezDias().stream().forEach(unEstado -> this.notificarMascotaEncontrada(unEstado));
  }

  public List<DatosMascotaPerdida> listaDatosMascotasPerdidasUltimosDiezDias() {
    return RepositorioMascotas.getInstance().datosMascotasPerdidasEnUltimosDiezDias();
  }

}

