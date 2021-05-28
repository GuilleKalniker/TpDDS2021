package domain.Mascota;

import com.sun.org.apache.xpath.internal.operations.Bool;
import domain.Mascota.AtributosMascota.TipoMascota;

// TODO: permitir caracteristicas, o hacer una herencia
// Funciona de adapter a la API provista (TODO)
public class HogarDeTransito {
  private Boolean tienePreferencia;
  private TipoMascota preferencia;
  private Boolean tienePatio;
  private Integer disponibilidadMaxima;
  private Integer ocupacionActual = 0;
  // TODO: algun tipo de ubicacion

  HogarDeTransito(Boolean tienePreferencia, TipoMascota preferencia, Boolean tienePatio, Integer disponibilidadMaxima) {
    this.tienePreferencia = tienePreferencia;
    this.preferencia = preferencia;
    this.tienePatio = tienePatio;
    this.disponibilidadMaxima = disponibilidadMaxima;
  }

  Boolean puedeAceptarMascota(DatosMascotaPerdida datosMascotaPerdida) {
    return hayDisponibilidad() && mascotaEntra(datosMascotaPerdida) && mascotaEsDePreferencia(datosMascotaPerdida);
  }

  private boolean mascotaEsDePreferencia(DatosMascotaPerdida datosMascotaPerdida) {
    return !tienePreferencia || datosMascotaPerdida.getTipo() == preferencia;
  }

  private boolean mascotaEntra(DatosMascotaPerdida datosMascotaPerdida) {
    return !datosMascotaPerdida.noEsPequenia() || tienePatio;
  }

  private Boolean hayDisponibilidad() {
    return ocupacionActual < disponibilidadMaxima;
  }

  private void liberarMascota() {
    ocupacionActual--;
  }

  private void agregarMascota() {
    ocupacionActual++;
  }
}
