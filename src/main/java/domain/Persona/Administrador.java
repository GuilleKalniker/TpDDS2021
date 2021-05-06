package domain.Persona;

import domain.Sistema.CentroDeRescate;

import java.util.ArrayList;

public class Administrador extends Usuario{

  public void agregarCaracteristica(String caracteristica, ArrayList<String> valorsPosibles) {
    CentroDeRescate.getInstance().getCaracteristicas().agregarCaracteristicas(caracteristica, valorsPosibles);
  }

  public void agregarValorACaracteristica(String caracteristica, String valor) {
    CentroDeRescate.getInstance().getCaracteristicas().agregarValorA(caracteristica, valor);
  }

  public Administrador(String usuario, String contrasenia) throws Exception {
    this.nombreDeUsuario = usuario;
    this.contrasenia = contrasenia;

    this.registrarse();
  }
}