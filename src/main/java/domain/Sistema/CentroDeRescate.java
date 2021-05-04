package domain.Sistema;

import domain.Mascota.EstadoMascotaPerdida;
import domain.Mascota.MascotaRegistrada;

import java.time.LocalDate;
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

  private List<Integer> obtenerListaDeQRs() {
    return mascotasRegistradas.stream().map(unaMascota -> unaMascota.getQr()).collect(Collectors.toList());
  }

  public Integer otorgarQR() {
    while(true) { // Ciclo infinito hasta obtener un QR valido
      Integer QR = new Random().nextInt(10000); // QRs hasta 10000
      if (!this.obtenerListaDeQRs().contains(QR)) {
        return QR;
      }
    }
  }

  public void notificarMascotaEncontrada(EstadoMascotaPerdida unEstado) { // No deberia ser inmediato (no entiendo por que no), por ahora lo es
    MascotaRegistrada mascota = identificarMascota(unEstado.getQrMascotaPerdida());
    mascota.getDuenio().seEncontro(mascota); // Nefasto, pero no se me ocurrio nada mejor - Nico
  }

  MascotaRegistrada identificarMascota(Integer unQR) { // Podria llevar mas parametros del estado, pero con la key QR no hace falta
    return this.mascotasRegistradas.stream().filter(unaMascota -> unaMascota.getQr().equals(unQR)).findFirst().get();
  }

  public List<MascotaRegistrada> listarMascotasEncontradasEnUltimosDiezDias() {
    return estadosMascotasPerdidas.stream().filter(unEstado -> pasoEntreUltimosDiezDias(unEstado.getFechaEncuentro())).map(unEstado -> identificarMascota(unEstado.getQrMascotaPerdida())).collect(Collectors.toList());
  }

  private boolean pasoEntreUltimosDiezDias(LocalDate unaFecha) {
    return unaFecha.compareTo(LocalDate.now()) < 0 && unaFecha.compareTo(LocalDate.now()) > -10; // compareTo devuelve negativo porque unaFecha < now, entonces se fija si mayor a -10 para entrar en los 10 dias. Primera comparacion para evitar errores
  }

}

