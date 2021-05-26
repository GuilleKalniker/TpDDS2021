package domain.Repositorio;

import domain.Mascota.DatosMascotaPerdida;
import domain.Mascota.MascotaRegistrada;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RepositorioMascotas {

  private List<MascotaRegistrada> mascotasRegistradas = new ArrayList<>();
  private List<DatosMascotaPerdida> datosMascotasPerdidas = new ArrayList<>();
  private static final RepositorioMascotas INSTANCE = new RepositorioMascotas();

  public static RepositorioMascotas getInstance() {
    return INSTANCE;
  }

  public void registrarMascota(MascotaRegistrada mascota){
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
}
