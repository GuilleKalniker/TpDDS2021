package domain.Sistema;

import domain.Adapters.AdapterRepoMascotas;
import domain.Mascota.DatosMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.Duenio;
import domain.Repositorio.RepositorioDuenios;
import domain.Servicios.JavaMailApi;

import java.util.List;

public class CentroDeRescate {

  private String correoDelCentro = "centrodemascotasdds@gmail.com";
  private String contrasenia_correo = "tpdds2021";

  private AdapterRepoMascotas adapterRepoMascotas = new AdapterRepoMascotas();

  /** FUNCIONES PARA MASCOTAS REGISTRADAS */

  public String registrarMascota(MascotaRegistrada mascota){
    return this.adapterRepoMascotas.registrarMascota(mascota);
  }

  public MascotaRegistrada buscasMascota(String ID){
    return this.adapterRepoMascotas.buscarMascotaPorID(ID);
  }

  public Boolean existeMascota(String ID) {
    return this.adapterRepoMascotas.existeMascota(ID);
  }

  /** FUNCIONES PARA MASCOTAS PERDIDAS*/

  public void agregarDatosMascotaPerdida(DatosMascotaPerdida datosMascotaPerdida) {
    this.adapterRepoMascotas.agregarDatosMascotaPerdida(datosMascotaPerdida);
    /*
    this.notificar(
        this.buscarDuenioApartirIDMascota(datosMascotaPerdida.getIDMascotaPerdida()),
        datosMascotaPerdida,
        new JavaMailApi(this.correoDelCentro, this.contrasenia_correo));*/
  }

  public void notificar(Duenio duenio, DatosMascotaPerdida datosMascotaPerdida, JavaMailApi api){
    api.notificar(duenio, datosMascotaPerdida);
  }


  /** FUNCIONES QUE SE COMUNICAN CON EL ADAPATER DE REPOSITORIO USUARIOS */

  /**
  * notificarMascotaEncontrada(1)
  * Obtiene una mascota a partir del ID registrado en el estado y luego notifica al duenio.
  */
  public void notificarMascotaEncontrada(DatosMascotaPerdida datosMascotaPerdida) {
    MascotaRegistrada mascota = this.buscasMascota(datosMascotaPerdida.getIDMascotaPerdida());
    Duenio duenioMascota = buscarDuenioApartirIDMascota(mascota.getID());
    duenioMascota.seEncontro(mascota);

    //TODO esta bien que al notificar se elimine los datos de la mascota perdida?
    this.adapterRepoMascotas.eliminarDatosMascotaPerdida(datosMascotaPerdida.getIDMascotaPerdida());
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
    return this.adapterRepoMascotas.datosMascotasPerdidasEnUltimosDiezDias();
  }

}

