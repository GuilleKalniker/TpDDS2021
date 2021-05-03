package domain.Persona;

import java.time.LocalDate;

public abstract class Usuario {
  private String nombre;
  private String apellido;
  private LocalDate fechaNacimiento;
  private TipoDocumento tipoDocumento;
  private Integer nroDocumento;

  //TODO comportamiento común a cualquier usuario (quizás loguearse?)
}