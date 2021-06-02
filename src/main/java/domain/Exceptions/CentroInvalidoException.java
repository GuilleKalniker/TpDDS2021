package domain.Exceptions;

public class CentroInvalidoException extends RuntimeException {
  public CentroInvalidoException(String mensaje){
    super(mensaje);
  }
}
