package domain.Pregunta;

import domain.Exceptions.RespuestaInvalidaException;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Pregunta {

  private String pregunta;

  private Boolean esObligatoria;
  private List<String> posiblesRespuestas;
  private String respuesta;

  public Pregunta(String pregunta, List<String> posiblesRespuestas, Boolean esObligatoria) {

    /*if (posiblesRespuestas.size() <= 1)
      throw new RespuestaInvalidaException("La cantidad minima de respuestas debe ser 2");
    */
    this.pregunta = pregunta;
    this.esObligatoria = esObligatoria;
    this.posiblesRespuestas = posiblesRespuestas.stream().map(value -> value.toLowerCase().trim()).collect(Collectors.toList());
  }

  public String getPregunta() {
    return pregunta;
  }

  public String getRespuesta() {
    return respuesta;
  }

  public void setRespuesta(String respuesta) {
    if (!posiblesRespuestas.contains(respuesta.toLowerCase().trim()))
      throw new RespuestaInvalidaException("La respuesta es inválida");
    this.respuesta = respuesta.toLowerCase().trim();
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
