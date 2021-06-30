package domain.Exceptions;

public class RespuestaInvalidaException extends RuntimeException {
  public RespuestaInvalidaException(String message) {
    super(message);
  }
}