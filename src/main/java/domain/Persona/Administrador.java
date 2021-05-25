package domain.Persona;

import domain.Repositorio.RepositorioCaracteristicas;

import java.util.ArrayList;

public class Administrador extends Usuario{

  public void agregarCaracteristica(String caracteristica, ArrayList<String> valorsPosibles) {
    RepositorioCaracteristicas.getInstance().agregarCaracteristicas(caracteristica, valorsPosibles);
  }

  public void agregarValorACaracteristica(String caracteristica, String valor) {
    RepositorioCaracteristicas.getInstance().agregarValorA(caracteristica, valor);
  }

  public Administrador(String usuario, String contrasenia) {
   super(usuario,contrasenia);
  }
}