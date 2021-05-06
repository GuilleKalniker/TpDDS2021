package domain.Persona;

import domain.Sistema.AltaUsuarios;

abstract class Usuario {
  protected String nombreDeUsuario;
  protected String contrasenia;

  public void registrarse() throws Exception {
    AltaUsuarios altaUsuarios = new AltaUsuarios();
    altaUsuarios.registrarse(this.nombreDeUsuario, this.contrasenia);
  }
}
