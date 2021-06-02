package domain.Servicios.ClasesParaLaConsulta;


import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Mascota.AtributosMascota.TipoMascota;
import domain.Mascota.MascotaRegistrada;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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


  public static class Ubicacion{
    public String direccion;
    public Double lat;
    public Double longitud;  // es long pero es una palabra reservada :D

    public Ubicacion(String direccion, Double lat, Double longitud) {
      this.direccion = direccion;
      this.lat = lat;
      this.longitud = longitud;
    }
  }

  private class Admisiones{
    public Boolean perros;
    public Boolean gatos;

    public Admisiones(Boolean perros, Boolean gatos) {
      this.perros = perros;
      this.gatos = gatos;
    }
  }

  public HogarTransito(String id, String nombre, String direccion, Double lat, Double longitud, String telefono,Boolean perros, Boolean gatos, Integer capacidad, Integer lugares_disponibles, Boolean patio, ArrayList<String> caracteristicas) {
    this.id = id;
    this.nombre = nombre;
    this.ubicacion = new Ubicacion(direccion,lat,longitud);
    this.telefono = telefono;
    this.admisiones = new Admisiones(perros,gatos);
    this.capacidad = capacidad;
    this.lugares_disponibles = lugares_disponibles;
    this.patio = patio;
    this.caracteristicas = caracteristicas;
  }

  public String getNombre() {
    return nombre;
  }

  public Boolean esAdecuado(MascotaRegistrada mascota,Double radio, domain.Mascota.AtributosMascota.Ubicacion ubicacion){
    return admiteTipo(mascota.getTipo()) &&
        admiteTamanio(mascota.getCaracteristicas()) &&
        tieneLugarDisponible();
        //&& aceptaCaracteristicasEspeciales(mascota.getCaracteristicas()) &&
       // estaEnElRadio(ubicacion,radio);
  }

  public Boolean admiteTipo(TipoMascota tipo){
    if(tipo == TipoMascota.PERRO){
      return this.admisiones.perros;
    }
    return this.admisiones.gatos;
  }
  public Boolean admiteTamanio(List<Caracteristica> caracteristicasMascota){
    if(!this.patio){
      return caracteristicasMascota.contains(Caracteristica.Chico);
    }
    return true;
  }

  public Boolean tieneLugarDisponible(){
    return this.lugares_disponibles > 0;
  }

  public Boolean aceptaCaracteristicasEspeciales(List<Caracteristica> caracteristicasMascota){
    List<String> caracteristicasMascotaString = caracteristicasMascota.stream().map(caracteristica -> caracteristica.toString()).collect(Collectors.toList());

    return caracteristicasMascotaString.containsAll(this.caracteristicas);
  }

  public Boolean estaEnElRadio(domain.Mascota.AtributosMascota.Ubicacion ubicacion, Double radio){
    return ubicacion.calcularDistanciaA(new domain.Mascota.AtributosMascota.Ubicacion(this.ubicacion.lat, this.ubicacion.longitud)) < radio;
  }
}
