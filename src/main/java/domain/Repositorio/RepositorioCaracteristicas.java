package domain.Repositorio;

import java.util.ArrayList;
import java.util.HashMap;

public class RepositorioCaracteristicas {
  private HashMap<String, ArrayList<String>> DiccionarioCaracteristicas;
  private static final RepositorioCaracteristicas instance = new RepositorioCaracteristicas();

  private RepositorioCaracteristicas(){
    this.DiccionarioCaracteristicas = new HashMap<String, ArrayList<String>>();
  }

  public static RepositorioCaracteristicas getInstance(){
    return instance;
  }

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
