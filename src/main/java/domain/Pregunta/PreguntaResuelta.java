package domain.Pregunta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "preguntaResuelta")
public class PreguntaResuelta {
  @Id
  @GeneratedValue
  private long id;

  @OneToOne
  private Pregunta pregunta;
  private String respuesta;

  public PreguntaResuelta(Pregunta pregunta, String respuesta) {
    this.pregunta = pregunta;
    this.respuesta = respuesta;
  }

  public PreguntaResuelta() {}

  public Pregunta getPregunta() {
    return pregunta;
  }

  public String getRespuesta() {
    return respuesta;
  }

}
