package domain.Pregunta;

import domain.Sistema.CentroDeRescate;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pregunta")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Pregunta {
  protected String pregunta;

  @Id
  @GeneratedValue
  private long id;

  @ManyToOne
  private CentroDeRescate centro;

  public Pregunta(String pregunta) {
    this.pregunta = pregunta;
  }

  public Pregunta() {}

  public String getPregunta() {
    return pregunta;
  }

  public abstract Boolean getAbierta();
  public abstract Boolean getBooleana();
  public abstract Boolean getOpcionMultiple();

  //TODO: volarlo ta deprecado
  public Boolean esValida() {
    return true;
  }

}
