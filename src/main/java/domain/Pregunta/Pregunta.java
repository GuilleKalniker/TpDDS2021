package domain.Pregunta;

import domain.Exceptions.RespuestaInvalidaException;
import domain.Exceptions.RespuestaVaciaException;
import java.util.ArrayList;
import java.util.List;

public class Pregunta {

  private String pregunta;
  private Boolean esObligatoria;
  private List<String> posiblesRespuestas = new ArrayList<>();
  private String respuesta;

  public Pregunta(String pregunta, List<String> posiblesRespuestas, Boolean esObligatoria) {

    if (posiblesRespuestas.size() <= 1)
      throw new RespuestaInvalidaException("La cantidad minima de respuestas debe ser 2");

    this.pregunta = pregunta;
    this.esObligatoria = esObligatoria;
    this.posiblesRespuestas = posiblesRespuestas;
  }

  public String getPregunta() {
    return pregunta;
  }

  public String getRespuesta() {
    return respuesta;
  }

  public void setRespuesta(String respuesta) {
    if (respuesta.isEmpty())
      throw new RespuestaVaciaException("La cantidad minima de respuestas debe ser 1");
    if (!posiblesRespuestas.contains(respuesta)) // Deberia ser asincronico (?)
      throw new RespuestaInvalidaException("La respuesta es inválida.");
    this.respuesta = respuesta;
  }

  public Boolean esObligatoria() {
    return esObligatoria;
  }

  public Boolean estaRespondida() {
    return respuesta != null;
  }

  public Boolean esValida() {
    return (this.esObligatoria() && this.estaRespondida()) || !this.esObligatoria;
    //TODO darle expresividad ;)
  }

}
