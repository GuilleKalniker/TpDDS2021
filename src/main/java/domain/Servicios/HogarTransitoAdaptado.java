package domain.Servicios;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Mascota.AtributosMascota.TipoMascota;
import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.MascotaRegistrada;
import domain.Repositorio.RepositorioMascotas;

import java.util.List;

public class HogarTransitoAdaptado {
  private String id;
  private String nombre;
  private String direccion;
  private Ubicacion ubicacion;
  private String telefono;
  private List<TipoMascota> tiposMascotaAdmitidos;
  private Integer lugaresDisponibles;
  private Boolean tienePatio;
  private List<Caracteristica> caracteristicasAdmitidas;

  public HogarTransitoAdaptado(String id, String nombre, String direccion, Ubicacion ubicacion, String telefono, List<TipoMascota> tiposMascotaAdmitidos, Integer lugaresDisponibles, boolean tienePatio, List<Caracteristica> caracteristicasAdmitidas) {
    this.id = id;
    this.nombre = nombre;
    this.direccion = direccion;
    this.ubicacion = ubicacion;
    this.telefono = telefono;
    this.tiposMascotaAdmitidos = tiposMascotaAdmitidos;
    this.lugaresDisponibles = lugaresDisponibles;
    this.tienePatio = tienePatio;
    this.caracteristicasAdmitidas = caracteristicasAdmitidas;
  }

  public String getNombre() {
    return nombre;
  }

  public Boolean esAdecuado(MascotaRegistrada mascota, Double radio, domain.Mascota.AtributosMascota.Ubicacion ubicacion){
    return admiteTipo(mascota.getTipo()) &&
        admiteTamanio(mascota.getCaracteristicas()) &&
        tieneLugarDisponible() &&
        aceptaCaracteristicasEspeciales(mascota.getCaracteristicas()) &&
        estaEnElRadio(ubicacion,radio);
  }

  public Boolean admiteTipo(TipoMascota tipo){
    return this.tiposMascotaAdmitidos.contains(tipo);
  }

  public Boolean admiteTamanio(List<Caracteristica> caracteristicasMascota){
    if(!this.tienePatio){
      return caracteristicasMascota.stream().anyMatch(caracteristica -> caracteristica.tieneCaracteristica("chico"));
    }
    return true;
  }

  public Boolean tieneLugarDisponible(){
    return this.lugaresDisponibles > 0;
  }

  public Boolean aceptaCaracteristicasEspeciales(List<Caracteristica> caracteristicasMascota){
    return caracteristicasAdmitidas.containsAll(caracteristicasMascota);
  }

  public Boolean estaEnElRadio(Ubicacion ubicacion, Double radio){
    return ubicacion.calcularDistanciaA(this.ubicacion) < radio;
  }

}
