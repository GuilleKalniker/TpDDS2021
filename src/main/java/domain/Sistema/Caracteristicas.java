package domain.Sistema;

import java.util.ArrayList;
import java.util.HashMap;

public class Caracteristicas {

  private HashMap<String, ArrayList<String>> DiccionarioCaracteristicas;

  public void agregarCaracteristicas(String nombreCaracteristica, ArrayList<String> valorsPosibles){
    this.DiccionarioCaracteristicas.put(nombreCaracteristica, valorsPosibles);
  }

  public ArrayList<String> obtenerValoresPsobiles(String nombreCaracteristica){
    return this.DiccionarioCaracteristicas.get(nombreCaracteristica);
  }
}
