package domain.Mascota.AtributosMascota;

public class Ubicacion {
  private Double latitud;
  private Double longitud;

  public Ubicacion(Double latitud, Double longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public Double calcularDistanciaA(Ubicacion ubicacion) {
    return Math.sqrt(Math.pow((ubicacion.getLatitud() - latitud), 2) + Math.pow((ubicacion.getLongitud() - longitud), 2));
  }

  public Double getLatitud() {
    return latitud;
  }

  public void setLatitud(Double latitud) {
    this.latitud = latitud;
  }

  public Double getLongitud() {
    return longitud;
  }

  public void setLongitud(Double longitud) {
    this.longitud = longitud;
  }
}
