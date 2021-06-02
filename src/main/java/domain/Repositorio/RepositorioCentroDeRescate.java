package domain.Repositorio;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Sistema.CentroDeRescate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioCentroDeRescate {
  private List<CentroDeRescate> centrosDeRescateRegistrados = new ArrayList<>();

  private static final RepositorioCentroDeRescate INSTANCE = new RepositorioCentroDeRescate();

  public static RepositorioCentroDeRescate getInstance() {
    return INSTANCE;
  }

  public void registrarCentroDeRescate(CentroDeRescate centroDeRescate){
    this.centrosDeRescateRegistrados.add(centroDeRescate);
  }

  public List<CentroDeRescate> getCentrosDeRescateRegistrados() {
    return this.centrosDeRescateRegistrados;
  }

  public CentroDeRescate getCentroDeRescateMasCercanoA(Ubicacion ubicacion) {
    return centrosDeRescateRegistrados.stream().sorted(Comparator.comparing(centro -> centro.getUbicacion().calcularDistanciaA(ubicacion))).collect(Collectors.toList()).get(0);
  }


}