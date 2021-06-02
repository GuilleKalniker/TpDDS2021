package domain.Persona;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Repositorio.RepositorioCaracteristicas;
import domain.Repositorio.RepositorioUsuarios;

import java.util.ArrayList;

public class Administrador{

  private String nombreDeUsuario;
  private String contrasenia;

  private RepositorioUsuarios repositorioUsuarios = RepositorioUsuarios.getInstance();
  private RepositorioCaracteristicas repositorioCaracteristicas = RepositorioCaracteristicas.getInstance();

  public Administrador(String usuario, String contrasenia) {
    this.nombreDeUsuario = usuario;
    this.contrasenia = contrasenia;
  }

  public void setRepositorioUsuarios(RepositorioUsuarios repositorioUsuarios) {
    this.repositorioUsuarios = repositorioUsuarios;
  }

  public void setRepositorioCaracteristicas(RepositorioCaracteristicas repositorioCaracteristicas) {
    this.repositorioCaracteristicas = repositorioCaracteristicas;
  }

  public void agregarCaracteristica(Caracteristica caracteristica) {
    repositorioCaracteristicas.agregarCaracteristica(caracteristica);
  }

  public void sacarCaracteristica(Caracteristica caracteristica) {
    repositorioCaracteristicas.sacarCaracteristica(caracteristica);
  }

  public void registrarse() {
    repositorioUsuarios.registrarAdministrador(this);
  }

  public String getNombreUsuario() {
    return nombreDeUsuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }

}