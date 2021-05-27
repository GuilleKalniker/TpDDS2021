package domain.Repositorio;

import domain.Mascota.DatosMascotaPerdida;
import domain.Mascota.MascotaRegistrada;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RepositorioMascotas {

  private List<MascotaRegistrada> mascotasRegistradas = new ArrayList<>();
  private List<DatosMascotaPerdida> datosMascotasPerdidas = new ArrayList<>();
  private static final RepositorioMascotas INSTANCE = new RepositorioMascotas();

  public static RepositorioMascotas getInstance() {
    return INSTANCE;
  }

  public void addMascota(MascotaRegistrada mascota){
    this.getMascotasRegistradas().add(mascota);
  }

  public List<MascotaRegistrada> getMascotasRegistradas() {
    return this.mascotasRegistradas;
  }

  public void agregarDatosMascotaPerdida(DatosMascotaPerdida datos) {
    this.getDatosMascotasPerdidas().add(datos);
  }

  public List<DatosMascotaPerdida> getDatosMascotasPerdidas() {
    return datosMascotasPerdidas;
  }

  /** MASCOTAS REGISTRADAS **/

  public String registrarMascota(MascotaRegistrada mascota){
    mascota.setID(this.generarId());
    this.addMascota(mascota);
    return mascota.getID();
  }

  public MascotaRegistrada buscarMascotaPorID(String ID) {
    return this.getMascotasRegistradas()
        .stream().filter(mascota -> mascota.coincideID(ID))
        .findFirst().get();
  }

  public Boolean existeMascota(String ID) {
    return this.buscarMascotaPorID(ID).getID() == ID;
  }

  /** DATOS MASCOTAS PERDIDAS **/

  public void eliminarDatosMascotaPerdida(String ID) {
    this.getDatosMascotasPerdidas()
        .removeIf(datosMascotaPerdida -> datosMascotaPerdida.getIDMascotaPerdida() == ID);
  }

  public DatosMascotaPerdida buscarDatosMascotaPerdida(String ID) {
    return this.getDatosMascotasPerdidas()
        .stream().filter(datos -> datos.getIDMascotaPerdida() == ID)
        .findFirst().get();
  }

  public List<DatosMascotaPerdida> datosMascotasPerdidasEnUltimosDiezDias(){
    return this.getDatosMascotasPerdidas().stream()
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

  public static String generarId(){
    return UUID.randomUUID().toString().replace("-", "");
  }

}
