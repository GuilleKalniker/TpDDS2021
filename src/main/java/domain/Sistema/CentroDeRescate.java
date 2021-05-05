package domain.Sistema;

import domain.Mascota.EstadoMascotaPerdida;
import domain.Mascota.MascotaRegistrada;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CentroDeRescate {
  private List<MascotaRegistrada> mascotasRegistradas = new ArrayList<>(); // Inicializo listas, que si no no funcionan
  private List<EstadoMascotaPerdida> estadosMascotasPerdidas = new ArrayList<>();
  private Caracteristicas caracteristicas;

  private static final CentroDeRescate INSTANCE = new CentroDeRescate();

  public static CentroDeRescate getInstance() {
    return INSTANCE;
  }

  public void agregarMascotaRegistrada(MascotaRegistrada unaMascota) {
    this.mascotasRegistradas.add(unaMascota);
  }

  public void agregarEstadoMascotaPerdida(EstadoMascotaPerdida unEstado) {
    this.estadosMascotasPerdidas.add(unEstado);
  }

  public List<MascotaRegistrada> getMascotasRegistradas() {
    return mascotasRegistradas;
  }

  public Caracteristicas getCaracteristicas() {
    return caracteristicas;
  }

  /*
  * obtenerListaDeQRs()
  * Obtiene todos los QRs de las mascotas ya registradas.
  */
  public List<Integer> obtenerListaDeQRs() {
    return this.mascotasRegistradas.stream().map(unaMascota -> unaMascota.getQr()).collect(Collectors.toList());
  }

  /*
  * otorgarQR()
  * Otorga un nuevo QR aleatorio no repetido.
  */
  public Integer otorgarQR() {
    while(true) { // Ciclo infinito hasta obtener un QR valido
      Integer QR = new Random().nextInt(10000); // QRs hasta 10000
      if (!this.obtenerListaDeQRs().contains(QR)) {
        return QR;
      }
    }
  }

  /*
  * notificarMascotaEncontrada(1)
  * Obtiene una mascota a partir del QR registrado en el estado y luego notifica al duenio.
  */
  public void notificarMascotaEncontrada(EstadoMascotaPerdida unEstado) {
    MascotaRegistrada mascota = this.identificarMascota(unEstado.getQrMascotaPerdida());
    mascota.getDuenio().seEncontro(mascota);
    this.estadosMascotasPerdidas.remove(unEstado);
  }

  /*
  * notificarMascotasDeLosUltimos10Dias()
  * Filtra la lista de estadoMascotaPerdida y le notifica a sus dueños que fueron encontradas
  */
  public void notificarMascotasDeLosUltimos10Dias(){
    this.listarEstadosDeMascotasPerdidasEnUltimosDiezDias().stream().forEach(unEstado -> this.notificarMascotaEncontrada(unEstado));
  }

  /*
  * identificarMascota(1)
  * Obtiene una mascota de la lista de mascotas registradas a partir de un QR.
  */
  public MascotaRegistrada identificarMascota(Integer unQR) { // Podria llevar mas parametros del estado, pero con la key QR no hace falta
    return this.mascotasRegistradas.stream()
        .filter(unaMascota -> unaMascota.getQr().equals(unQR)).findFirst().get();
  }

  /*
  * listarMascotasPerdidasEnUltimosDiezDias()
  * Devuelve la lista de las mascotas perdidas de los ultimos 10 dias
  */
  public List<EstadoMascotaPerdida> listarEstadosDeMascotasPerdidasEnUltimosDiezDias() {

    return this.estadosMascotasPerdidas.stream()
        .filter(unEstado -> this.pasoEntreUltimosDiezDias(unEstado.getFechaEncuentro())).collect(Collectors.toList());
  }

  /*
  * listarMascotasEncontradasEnUltimosDiezDias()
  * Filtra lista de estados a partir de las fechas para luego mappear la mascota correspondiente al estado para generar la lista final. Dicha lista no es printeable.
  */
  public List<MascotaRegistrada> listarMascotasEncontradasEnUltimosDiezDias() {
    return listarEstadosDeMascotasPerdidasEnUltimosDiezDias().stream().map(unEstado -> identificarMascota(unEstado.getQrMascotaPerdida())).collect(Collectors.toList());
  }

  /*
  * pasoEntreUltimosDiezDias(1)
  * Verifica si una fecha se encuentra dentro de los ultimos 10 dias.
  */
  private boolean pasoEntreUltimosDiezDias(LocalDate unaFecha) {
    return ChronoUnit.DAYS.between(unaFecha, LocalDate.now()) <= 10;
  }

  /*
  * validarQR(1)
  * Recibe un QR y verifica si pertenece al centro, de no pertenecer lanza una excepcion.
  */
  public void validarQR(Integer unQR) throws Exception {
    if (!this.obtenerListaDeQRs().contains(unQR)) {
      throw new Exception();
    }
  }
}

