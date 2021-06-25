package domain.Pregunta;

import java.util.Objects;

public class Pregunta {

  private String pregunta;
  private Boolean esObligatoria;
  private String respuesta = null;

  public Pregunta(String pregunta, Boolean esObligatoria) {
    this.pregunta = pregunta;
    this.esObligatoria = esObligatoria;
  }

  public String getPregunta() {
    return pregunta;
  }

  public Boolean getEsObligatoria() {
    return esObligatoria;
  }

  public void setRespuesta(String respuesta) {
    this.respuesta = respuesta;
  }

  public void estaRespondida() {
    if (this.esObligatoria && Objects.isNull(respuesta))
      throw new RuntimeException("no se respondio");
      //TODO: willy has algo decente con esto
  }
}
