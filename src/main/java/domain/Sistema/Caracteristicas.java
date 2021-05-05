package domain.Sistema;

import java.util.ArrayList;
import java.util.HashMap;

public class Caracteristicas {

  private HashMap<String, ArrayList<String>> DiccionarioCaracteristicas = new HashMap<>();

  public void agregarCaracteristicas(String nombreCaracteristica, ArrayList<String> valorsPosibles){
    this.DiccionarioCaracteristicas.put(nombreCaracteristica, valorsPosibles);
  }

  public ArrayList<String> obtenerValoresPosibles(String nombreCaracteristica){
    return this.DiccionarioCaracteristicas.get(nombreCaracteristica);
  }

  public void agregarValorA(String nombreCaracteristica, String valor){
    ArrayList<String> list;
    if (DiccionarioCaracteristicas.containsKey(nombreCaracteristica)) {
      list = DiccionarioCaracteristicas.get(nombreCaracteristica);
      list.add(valor);
    } else {
      list = new ArrayList<String>();
      list.add(valor);
      DiccionarioCaracteristicas.put(nombreCaracteristica, list);
    }
  }
}
