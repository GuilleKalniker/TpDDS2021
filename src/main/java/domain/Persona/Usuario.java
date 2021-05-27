package domain.Persona;

import domain.Repositorio.RepositorioUsuarios;

public class Usuario {

  protected String nombreDeUsuario;
  protected String contrasenia;

  public Usuario(String nombreDeUsuario, String contrasenia) {
    this.nombreDeUsuario = nombreDeUsuario;
    this.contrasenia = contrasenia;
    this.registrarse();
  }

  public void registrarse() {
    RepositorioUsuarios.getInstance().registrarse(this.nombreDeUsuario, this.contrasenia);
  }

  public String getNombreDeUsuario() {
    return nombreDeUsuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }
}
