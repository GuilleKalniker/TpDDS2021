package domain.Adapters;

import domain.Mascota.MascotaRegistrada;
import domain.Repositorio.RepositorioMascotas;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AdapterRepoMascotas {

  private RepositorioMascotas repositorioMascotas = RepositorioMascotas.getInstance();

  public String registrarMascota(MascotaRegistrada mascota){
    mascota.setID(this.generarId());
    this.repositorioMascotas.registrarMascota(mascota);
    return mascota.getID();
  }

  public MascotaRegistrada buscarMascotaPorID(String ID) {
    return this.repositorioMascotas.getMascotasRegistradas().stream().filter(mascota -> mascota.coincideID(ID)).findFirst().get();
  }

  public Boolean existeMascota(String ID) {
    return this.buscarMascotaPorID(ID).getID() == ID;
  }


  public String generarId(){
    return UUID.randomUUID().toString().replace("-", "");
  }

}
