package domain.Repositorio;

import domain.Exceptions.IDInvalidoException;
import domain.Exceptions.IDNoSeCorrespondeException;
import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.Duenio;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RepositorioMascotas {

  private static final RepositorioMascotas INSTANCE = new RepositorioMascotas();

  public static RepositorioMascotas getInstance() {
    return INSTANCE;
  }

  public List<MascotaRegistrada> getMascotasRegistradas() {

    TypedQuery<MascotaRegistrada> query = AdapterJPA.entityManager().createQuery("select m from MascotaRegistrada m", MascotaRegistrada.class);
    return query.getResultList();
  }

  public void agregarDatosMascotaPerdida(FormularioMascotaPerdida datos) {
    this.getDatosMascotasPerdidas().add(datos);
  }

  public List<FormularioMascotaPerdida> getDatosMascotasPerdidas() {

    TypedQuery<FormularioMascotaPerdida> query = AdapterJPA.entityManager().createQuery("select m from FormularioMascotaPerdida m", FormularioMascotaPerdida.class);
    return query.getResultList();
  }

  public List<Caracteristica> getTodasLasCaracteristicas() {
    TypedQuery<Caracteristica> query = AdapterJPA.entityManager().createQuery("select c from Caracteristica c", Caracteristica.class);
    return query.getResultList();
  }

  public List<Caracteristica> getCaracteristicasActivas() {
    return getTodasLasCaracteristicas().stream().filter(caracteristica -> caracteristica.getActivo()).collect(Collectors.toList());
  }



  public void eliminarCaracteristica(long id){
    Caracteristica caracteristica = AdapterJPA.entityManager().find(Caracteristica.class, id);
    AdapterJPA.entityManager().remove(caracteristica);
  }

  public void crearCaracteristica(Caracteristica caracteristica) {
    AdapterJPA.persist(caracteristica);
  }

  /** MASCOTAS REGISTRADAS **/

  public void registrarMascota(MascotaRegistrada mascota){
    AdapterJPA.persist(mascota);
  }

  public MascotaRegistrada buscarMascotaPorID(long ID) {
    return AdapterJPA.entityManager().find(MascotaRegistrada.class, ID);

  }

  public Boolean existeMascota(long ID) {
    return this.buscarMascotaPorID(ID).getID() == ID;
  }

  /** DATOS MASCOTAS PERDIDAS **/
/*
  public void eliminarDatosMascotaPerdida(String ID) {
    this.getDatosMascotasPerdidas()
        .removeIf(datosMascotaPerdida -> datosMascotaPerdida.getIDMascotaPerdida() == ID);
  }

  public FormularioMascotaPerdida buscarDatosMascotaPerdida(String ID) {
    return this.getDatosMascotasPerdidas()
        .stream().filter(datos -> datos.getIDMascotaPerdida() == ID)
        .findFirst().orElseThrow(() -> new IDNoSeCorrespondeException("No se encontro la mascota perdida por ID"));
  }

  public List<FormularioMascotaPerdida> datosMascotasPerdidasEnUltimosDiezDias(){
    return this.getDatosMascotasPerdidas().stream()
        .filter(datosMascotaPerdida -> this.pasoCantidadDiasEntre(datosMascotaPerdida.getFechaEncuentro(), LocalDate.now(), 10))
        .collect(Collectors.toList());
  }*/

  /**************************************************************************************
   * Boolean pasoCantidadDiasEntre(fecha1, fecha2, cantidadDias)                        *
   * retorna un booleano si entre la fecha1 y la fecha2 pasaron la cantidad de dias :p  *
   *************************************************************************************/
  private boolean pasoCantidadDiasEntre(LocalDate unaFecha, LocalDate otraFecha, Integer cantidadDias) {
    return ChronoUnit.DAYS.between(unaFecha, otraFecha) <= cantidadDias;
  }


}
