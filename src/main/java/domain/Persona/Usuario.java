package domain.Persona;

import domain.Repositorio.AltaUsuarios;

public class Usuario {
  public Usuario(String nombreDeUsuario, String contrasenia) {
    this.nombreDeUsuario = nombreDeUsuario;
    this.contrasenia = contrasenia;

    this.registrarse();
  }

  protected String nombreDeUsuario;
  protected String contrasenia;

  public void registrarse() {
    AltaUsuarios altaUsuarios = new AltaUsuarios();
    altaUsuarios.registrarse(this.nombreDeUsuario, this.contrasenia);
  }
}
