package domain.Sistema;

import domain.Adapters.AdapterRepoMascotas;
import domain.Mascota.DatosMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.Duenio;
import domain.Repositorio.RepositorioDuenios;
import domain.Repositorio.RepositorioMascotas;

import javax.swing.text.MaskFormatter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CentroDeRescate {
  //TODO: Ver si metemos esta lista en RepositorioMascotas o si creamos un nuevo repo para ellas
  private List<DatosMascotaPerdida> datosMascotasPerdidas = new ArrayList<>();
  private List<Duenio> listaDuenios = new ArrayList<>();

  /** FUNCIONES PARA MASCOTAS REGISTRADAS */

  public String registrarMascota(MascotaRegistrada mascota){
    return new AdapterRepoMascotas().registrarMascota(mascota);
  }

  public MascotaRegistrada buscasMascota(String ID){
    return new AdapterRepoMascotas().buscarMascotaPorID(ID);
  }

  public Boolean existeMascota(String ID) {
    return new AdapterRepoMascotas().existeMascota(ID);
  }

  /** FUNCIONES PARA MASCOTAS PERDIDAS*/

  public void agregarDatosMascotaPerdida(DatosMascotaPerdida datosMascotaPerdida) {
    this.datosMascotasPerdidas.add(datosMascotaPerdida);
  }

  public List<DatosMascotaPerdida> getDatosMascotasPerdidas() {
    return datosMascotasPerdidas;
  }

  /** FUNCIONES QUE SE COMUNICAN CON EL ADAPATER DE REPOSITORIO USUARIOS */





  /**
  * notificarMascotaEncontrada(1)
  * Obtiene una mascota a partir del ID registrado en el estado y luego notifica al duenio.
  */
  public void notificarMascotaEncontrada(DatosMascotaPerdida datosMascotaPerdida) {
    MascotaRegistrada mascota = this.buscasMascota(datosMascotaPerdida.getIDMascotaPerdida());
    Duenio duenioMascota = buscarDuenioApartirMascota(mascota);
    duenioMascota.seEncontro(mascota);

    //TODO esta bien que al notificar se elimine los datos de la mascota perdida?
    this.datosMascotasPerdidas.remove(datosMascotaPerdida);
  }

  public Duenio buscarDuenioApartirMascota(MascotaRegistrada mascota){
    return RepositorioDuenios.getInstance().getDueniosRegistrados().stream().filter(duenio -> duenio.tieneA(mascota.getID())).findFirst().get();
  }

  //TODO armar un mensaje y enviarselo a algun dato de contacto del dueño
  //public void notificarAlDuenio(Duenio duenio, MascotaRegistrada mascota);


  /**
  * notificarMascotasDeLosUltimos10Dias()
  * Filtra la lista de datosMascotaPerdida y le notifica a sus dueños que fueron encontradas
  */
  public void notificarMascotasDeLosUltimos10Dias(){
    this.listarEstadosDeMascotasPerdidasEnUltimosDiezDias().stream().forEach(unEstado -> this.notificarMascotaEncontrada(unEstado));
  }

  /**
  * listarMascotasPerdidasEnUltimosDiezDias()
  * Devuelve la lista de las mascotas perdidas de los ultimos 10 dias
  */
  public List<DatosMascotaPerdida> listarEstadosDeMascotasPerdidasEnUltimosDiezDias() {
    return this.datosMascotasPerdidas.stream()
        .filter(unEstado -> this.pasoEntreUltimosDiezDias(unEstado.getFechaEncuentro())).collect(Collectors.toList());
  }

  /**
  * pasoEntreUltimosDiezDias(1)
  * Verifica si una fecha se encuentra dentro de los ultimos 10 dias.
  */
  private boolean pasoEntreUltimosDiezDias(LocalDate unaFecha) {
    return ChronoUnit.DAYS.between(unaFecha, LocalDate.now()) <= 10;
  }

}

