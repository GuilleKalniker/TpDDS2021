package domain.Repositorio;

import domain.Mascota.AtributosMascota.Caracteristica;

import java.util.*;

public class RepositorioCaracteristicas {
  private static final RepositorioCaracteristicas instance = new RepositorioCaracteristicas();

  private HashSet<Caracteristica> caracteristicasVigentes = new HashSet<Caracteristica>() {};

  public static RepositorioCaracteristicas getInstance(){
    return instance;
  }

  public Set<Caracteristica> getCaracteristicasVigentes() {
    return caracteristicasVigentes;
  }

  public void agregarCaracteristica(Caracteristica caracteristica){
    this.caracteristicasVigentes.add(caracteristica);
  }

  public void sacarCaracteristica(Caracteristica caracteristica){
    this.caracteristicasVigentes.remove(caracteristica);
  }

}
