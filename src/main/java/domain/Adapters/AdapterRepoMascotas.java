package domain.Adapters;

import domain.Mascota.DatosMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Repositorio.RepositorioMascotas;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class AdapterRepoMascotas {

  private RepositorioMascotas repositorioMascotas = RepositorioMascotas.getInstance();

  /** MASCOTAS REGISTRADAS **/

  public String registrarMascota(MascotaRegistrada mascota){
    mascota.setID(this.generarId());
    this.repositorioMascotas.registrarMascota(mascota);
    return mascota.getID();
  }

  public MascotaRegistrada buscarMascotaPorID(String ID) {
    return this.repositorioMascotas.getMascotasRegistradas()
        .stream().filter(mascota -> mascota.coincideID(ID))
        .findFirst().get();
  }

  public Boolean existeMascota(String ID) {
    return this.buscarMascotaPorID(ID).getID() == ID;
  }

  public static String generarId(){
    return UUID.randomUUID().toString().replace("-", "");
  }

  /** DATOS MASCOTAS PERDIDAS **/

  public void agregarDatosMascotaPerdida(DatosMascotaPerdida datos) {
    repositorioMascotas.agregarDatosMascotaPerdida(datos);
  }

  public void eliminarDatosMascotaPerdida(String ID) {
    this.repositorioMascotas.getDatosMascotasPerdidas()
        .removeIf(datosMascotaPerdida -> datosMascotaPerdida.getIDMascotaPerdida() == ID);
  }

  public DatosMascotaPerdida buscarDatosMascotaPerdida(String ID) {
    return this.repositorioMascotas.getDatosMascotasPerdidas()
        .stream().filter(datos -> datos.getIDMascotaPerdida() == ID)
        .findFirst().get();
  }

  public List<DatosMascotaPerdida> datosMascotasPerdidasEnUltimosDiezDias(){
    return repositorioMascotas.getDatosMascotasPerdidas().stream()
        .filter(datosMascotaPerdida -> this.pasoCantidadDiasEntre(datosMascotaPerdida.getFechaEncuentro(), LocalDate.now(), 10))
        .collect(Collectors.toList());
  }

  /**
   * Boolean pasoCantidadDiasEntre(fecha1, fecha2, cantidadDias)
   * retorna un booleano si entre la fecha1 y la fecha2 pasaron la cantidad de dias :p
   */
  private boolean pasoCantidadDiasEntre(LocalDate unaFecha, LocalDate otraFecha, Integer cantidadDias) {
    return ChronoUnit.DAYS.between(unaFecha, otraFecha) <= cantidadDias;
  }

}
