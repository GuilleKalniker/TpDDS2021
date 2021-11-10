package domain.Mascota.AtributosMascota;

public class Caracteristica {
  private Boolean activo;
  private String valor;

  Caracteristica(Boolean activo, String valor) {
    this.activo = activo;
    this.valor = valor;
  }

  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }
}


/*
  CASTRADO,
  MARRON,
  NEGRO,
  BLANCO,
  MANSO,
  ARISCO,
  CHICO,
  GRANDE,
  BAJO,
  PESADO,
  JUGUETON,
  MEDIANO,
  RABIOSO

  */
