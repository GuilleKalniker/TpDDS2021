package domain.Persona;

import domain.Sistema.CentroDeRescate;

import java.util.ArrayList;

public class Administrador {
  public void agregarCaracteristica(String caracteristica, ArrayList<String> valorsPosibles) {
    CentroDeRescate.getInstance().getCaracteristicas().agregarCaracteristicas(caracteristica, valorsPosibles);
  }
  public void agregarValorACaracteristica(String caracteristica, String valor) {
    CentroDeRescate.getInstance().getCaracteristicas().agregarValorA(caracteristica, valor);
  }
  //TODO
  //USUARIO Y CONTRASEÑA TANTO PARA ADMIN COMO PARA DUEÑOS
  //TESTS

}