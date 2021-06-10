package domain.Repositorio;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Persona.AtributosPersona.TipoDocumento;

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

  public List<Caracteristica> todasLasCaracteristicas() {
    List<Caracteristica> todasLasCaracteristicas = new ArrayList<Caracteristica>();
    todasLasCaracteristicas.add(Caracteristica.CASTRADO);
    todasLasCaracteristicas.add(Caracteristica.MARRON);
    todasLasCaracteristicas.add(Caracteristica.NEGRO);
    todasLasCaracteristicas.add(Caracteristica.BLANCO);
    todasLasCaracteristicas.add(Caracteristica.MANSO);
    todasLasCaracteristicas.add(Caracteristica.ARISCO);
    todasLasCaracteristicas.add(Caracteristica.CHICO);
    todasLasCaracteristicas.add(Caracteristica.GRANDE);
    todasLasCaracteristicas.add(Caracteristica.BAJO);
    todasLasCaracteristicas.add(Caracteristica.PESADO);
    todasLasCaracteristicas.add(Caracteristica.JUGUETON);
    todasLasCaracteristicas.add(Caracteristica.MEDIANO);
    todasLasCaracteristicas.add(Caracteristica.RABIOSO);

    return todasLasCaracteristicas;
  }
}
