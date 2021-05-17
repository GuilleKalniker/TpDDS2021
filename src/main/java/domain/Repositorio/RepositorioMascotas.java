package domain.Repositorio;

import domain.Mascota.MascotaRegistrada;
import domain.Sistema.CentroDeRescate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RepositorioMascotas {

  private List<MascotaRegistrada> mascotasRegistradas = new ArrayList<>();

  private static final RepositorioMascotas INSTANCE = new RepositorioMascotas();

  public static RepositorioMascotas getInstance() {
    return INSTANCE;
  }

  public void registrarMascota(MascotaRegistrada mascota){
    this.mascotasRegistradas.add(mascota);
  }

  public List<MascotaRegistrada> getMascotasRegistradas() {
    return this.mascotasRegistradas;
  }
}
