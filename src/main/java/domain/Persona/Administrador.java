package domain.Persona;

import javax.persistence.*;

@Entity
@DiscriminatorValue("administrador")
public class Administrador extends Usuario {

  public Administrador(String usuario, String contrasenia) {
    super(usuario, contrasenia);
  }

  public Administrador() {}

  public Boolean getEsAdministrador() {
    return true;
  }

}
