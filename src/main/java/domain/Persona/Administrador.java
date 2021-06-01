package domain.Persona;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Repositorio.RepositorioCaracteristicas;

import java.util.ArrayList;

public class Administrador extends Usuario{

  public Administrador(String usuario, String contrasenia) {
    super(usuario,contrasenia);
  }

  public void agregarCaracteristica(Caracteristica caracteristica) {
    RepositorioCaracteristicas.getInstance().agregarCaracteristica(caracteristica);
  }

  public void sacarCaracteristica(Caracteristica caracteristica) {
    RepositorioCaracteristicas.getInstance().sacarCaracteristica(caracteristica);
  }
}