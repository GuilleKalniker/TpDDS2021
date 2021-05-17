package domain.Persona;

import domain.Repositorio.RepositorioUsuarios;

public class Usuario {
  public Usuario(String nombreDeUsuario, String contrasenia) {
    this.nombreDeUsuario = nombreDeUsuario;
    this.contrasenia = contrasenia;

    this.registrarse();
  }

  protected String nombreDeUsuario;
  protected String contrasenia;

  public void registrarse() {
    RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
    repositorioUsuarios.registrarse(this.nombreDeUsuario, this.contrasenia);
  }
}
