package domain.Mascota.AtributosMascota;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "caracteristica")
public class Caracteristica {
  @Id
  @GeneratedValue
  private long id;

  private Boolean activo;
  private String valor;

  public Caracteristica(String valor) {
    this.valor = valor;
    this.activo = true;
  }

  public Caracteristica() {}

  public Boolean tieneCaracteristica(String valor) {
    return this.valor.equalsIgnoreCase(valor);
  }

  public long getId() {
    return id;
  }

  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

  public Boolean getActivo() {
    return activo;
  }

  public Boolean getNoActivo() {
    return !activo;
  }

  public void setActivo(Boolean activo) {
    this.activo = activo;
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
