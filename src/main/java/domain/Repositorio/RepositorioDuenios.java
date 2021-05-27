package domain.Repositorio;

import domain.Persona.Duenio;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDuenios {

  private List<Duenio> dueniosRegistrados = new ArrayList<>();

  private static final RepositorioDuenios INSTANCE = new RepositorioDuenios();

  public static RepositorioDuenios getInstance() {
    return INSTANCE;
  }

  public void registrarDuenio(Duenio duenio){
    this.dueniosRegistrados.add(duenio);
  }

  public List<Duenio> getDueniosRegistrados() {
    return this.dueniosRegistrados;
  }


}
