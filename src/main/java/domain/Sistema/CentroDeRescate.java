package domain.Sistema;

import domain.Mascota.EstadoMascotaEncontrada;
import domain.Mascota.Mascota;
import java.util.List;
import java.util.stream.Collectors;

public class CentroDeRescate {
  private List<Mascota> mascotasRegistradas;
  private List<Mascota> mascotasPerdidas;
  private List<EstadoMascotaEncontrada> mascotasEncontradas;
  private List<String> caracteristicasActivadas;

  public List<EstadoMascotaEncontrada> mascotasEncontradasUltimos10Dias() {
    return mascotasEncontradas
                    .stream()
                    .filter(encuentroMascota -> encuentroMascota.encontradaHaceMenosDe10Dias())
                    .collect(Collectors.toList());
  }

  public void avisoMascotaEncontrada(EstadoMascotaEncontrada unaMascotaEncontrada) {
    mascotasEncontradas.add(unaMascotaEncontrada);
  }

  public void confirmarMascotaEncontrada(Mascota unaMascota) {
    //TODO
  }

  public void registrarMascota(Mascota unaMascota) {
    mascotasRegistradas.add(unaMascota);
  }

  public void activarCaracteristica(String unaCaracteristica) {
    caracteristicasActivadas.add(unaCaracteristica);
  }

  public void desactivarCaracteristica(String unaCaracteristica) {
    caracteristicasActivadas.remove(unaCaracteristica);
  }
}
