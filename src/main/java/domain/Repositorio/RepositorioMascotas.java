package domain.Repositorio;

import domain.Mascota.MascotaRegistrada;
import domain.Sistema.CentroDeRescate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class RepositorioMascotas {

  private List<MascotaRegistrada> mascotasRegistradas = new ArrayList<>();
  private static final RepositorioMascotas INSTANCE = new RepositorioMascotas();

  public static RepositorioMascotas getInstance() {
    return INSTANCE;
  }

  public String registrarMascota(MascotaRegistrada mascota){
    mascota.setID(this.generarId());
    this.mascotasRegistradas.add(mascota);
    return mascota.getID();
  }

  public List<MascotaRegistrada> getMascotasRegistradas() {
    return this.mascotasRegistradas;
  }

  public String generarId(){
    return UUID.randomUUID().toString().replace("-", "");
  }
}
