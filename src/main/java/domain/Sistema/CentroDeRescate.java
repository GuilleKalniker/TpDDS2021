package domain.Sistema;

import domain.Mascota.DatosMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.Duenio;
import domain.Repositorio.RepositorioDuenios;
import domain.Repositorio.RepositorioMascotas;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CentroDeRescate {
  //TODO: Ver si metemos esta lista en RepositorioMascotas o si creamos un nuevo repo para ellas
  private List<DatosMascotaPerdida> datosMascotasPerdidas = new ArrayList<>();
  private List<Duenio> listaDuenios = new ArrayList<>();

  public void agregarDatosMascotaPerdida(DatosMascotaPerdida datosMascotaPerdida) {
    this.datosMascotasPerdidas.add(datosMascotaPerdida);
  }

  public List<DatosMascotaPerdida> getDatosMascotasPerdidas() {
    return datosMascotasPerdidas;
  }

  /**
  * obtenerListaDeQRs()
  * Obtiene todos los IDs de las mascotas ya registradas.
  */
   public List<String> obtenerListaDeIDs() {
    return RepositorioMascotas.getInstance().getMascotasRegistradas().stream().map(unaMascota -> unaMascota.getID()).collect(Collectors.toList());
  }

  /**
  * notificarMascotaEncontrada(1)
  * Obtiene una mascota a partir del ID registrado en el estado y luego notifica al duenio.
  */
  public void notificarMascotaEncontrada(DatosMascotaPerdida datosMascotaPerdida) {
    MascotaRegistrada mascota = this.identificarMascota(datosMascotaPerdida.getQrMascotaPerdida());
    Duenio duenioMascota = buscarDuenioApartirMascota(mascota);
    duenioMascota.seEncontro(mascota);

    //TODO esta bien que al notificar se elimine los datos de la mascota perdida?
    this.datosMascotasPerdidas.remove(datosMascotaPerdida);
  }

  public Duenio buscarDuenioApartirMascota(MascotaRegistrada mascota){
    return RepositorioDuenios.getInstance().getDueniosRegistrados().stream().filter(duenio -> duenio.tieneA(mascota.getID())).findFirst().get();
  }

/*
  public void notificarAlDuenio(Duenio duenio, MascotaRegistrada mascota){
    //TODO armar un mensaje y enviarselo a algun dato de contacto del dueño
  }
*/

  /**
  * notificarMascotasDeLosUltimos10Dias()
  * Filtra la lista de datosMascotaPerdida y le notifica a sus dueños que fueron encontradas
  */
  public void notificarMascotasDeLosUltimos10Dias(){
    this.listarEstadosDeMascotasPerdidasEnUltimosDiezDias().stream().forEach(unEstado -> this.notificarMascotaEncontrada(unEstado));
  }

  /**
  * identificarMascota(1)
  * Obtiene una mascota de la lista de mascotas registradas a partir de un ID.
  */
  public MascotaRegistrada identificarMascota(String ID) {
    return RepositorioMascotas.getInstance()
        .getMascotasRegistradas().stream()
        .filter(unaMascota -> unaMascota.coincideID(ID))
        .findFirst()
        .get();
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

