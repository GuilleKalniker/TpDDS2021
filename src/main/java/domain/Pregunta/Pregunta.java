package domain.Pregunta;

public class Pregunta {
  protected String tipo;
  protected String pregunta;
  protected String respuesta;

  public Pregunta(String pregunta, String tipo) {
    this.pregunta = pregunta;
    this.tipo = tipo;
  }

  public String getPregunta() {
    return pregunta;
  }

  public void setRespuesta(String respuesta) {
    this.respuesta = respuesta;
  }

  public String getRespuesta() {
    return respuesta;
  }

  public Boolean getAbierta() {
    return tipo.equals("abierta");
  }

  public Boolean getBooleana() {
    return tipo.equals("booleana");
  }

  public Boolean getOpcionMultiple() {
    return tipo.equals("multiple");
  }

  public String getTipo() {
    return tipo;
  }

  public Boolean esValida() {
    return true;
  }

}
