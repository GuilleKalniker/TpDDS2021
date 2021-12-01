package domain.Servicios.ClasesParaLaConsulta;


import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Mascota.AtributosMascota.TipoMascota;
import controllers.CaracteristicasController;
import domain.Repositorio.RepositorioMascotas;
import domain.Servicios.HogarTransitoAdaptado;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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


  public static class Ubicacion {
    public String direccion;
    public Double lat;
    public Double longitud;

    public Ubicacion(String direccion, Double lat, Double longitud) {
      this.direccion = direccion;
      this.lat = lat;
      this.longitud = longitud;
    }
  }

  static private class Admisiones {
    public Boolean perros;
    public Boolean gatos;

    public Admisiones(Boolean perros, Boolean gatos) {
      this.perros = perros;
      this.gatos = gatos;
    }
  }

  public HogarTransito(String id, String nombre, String direccion, Double lat, Double longitud, String telefono, Boolean perros, Boolean gatos, Integer capacidad, Integer lugares_disponibles, Boolean patio, ArrayList<String> caracteristicas) {
    this.id = id;
    this.nombre = nombre;
    this.ubicacion = new Ubicacion(direccion, lat, longitud);
    this.telefono = telefono;
    this.admisiones = new Admisiones(perros, gatos);
    this.capacidad = capacidad;
    this.lugares_disponibles = lugares_disponibles;
    this.patio = patio;
    this.caracteristicas = caracteristicas;
  }

  public String getNombre() {
    return nombre;
  }

  public HogarTransitoAdaptado adaptarHogar() {
    HogarTransitoAdaptado hogarAdaptado = new HogarTransitoAdaptado(
        id,
        nombre,
        ubicacion.direccion,
        new domain.Mascota.AtributosMascota.Ubicacion(ubicacion.lat, ubicacion.longitud),
        telefono,
        admisionesAListaTiposMascota(admisiones),
        lugares_disponibles,
        patio,
        stringsAListaCaracteristicas(caracteristicas)
    );
    return hogarAdaptado;
  }

  public List<TipoMascota> admisionesAListaTiposMascota(Admisiones admisiones) {
    List<TipoMascota> listaTipos = new ArrayList<TipoMascota>();
    if (admisiones.perros)
      listaTipos.add(TipoMascota.PERRO);
    if (admisiones.gatos)
      listaTipos.add(TipoMascota.GATO);
    return listaTipos;
  }

  public List<Caracteristica> stringsAListaCaracteristicas(List<String> caracteristicas) {
    return RepositorioMascotas
            .getInstance()
            .getTodasLasCaracteristicas()
            .stream()
            .filter(caracteristica -> caracteristicas.contains(caracteristica.getValor()))
            .collect(Collectors.toList());
  }

  public Integer getCapacidad() {
    return capacidad;
  }
}