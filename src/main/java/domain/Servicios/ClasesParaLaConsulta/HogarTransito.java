package domain.Servicios.ClasesParaLaConsulta;

import java.util.ArrayList;

public class HogarTransito {

  public String id;
  public String nombre;
  public Ubicacion ubicacion;
  public String telefono;
  public Admisiones admisiones;
  public Integer capacidad;
  public Integer lugares_disponibles;
  public Boolean patio;
  public ArrayList<String> caracteristicas;

  private class Ubicacion{
    public String direccion;
    public Double lat;
    public Double longitud;  // es long pero es una palabra reservada :D
  }

  private class Admisiones{
    public Boolean perros;
    public Boolean gatos;
  }

}
