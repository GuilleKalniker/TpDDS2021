package domain.Persona;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Repositorio.RepositorioCaracteristicas;
import domain.Repositorio.RepositorioUsuarios;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "administrador")
public class Administrador {
  @Id
  @GeneratedValue
  private long id;

  private String nombreDeUsuario;
  private String contrasenia;


  public Administrador(String usuario, String contrasenia) {
    this.nombreDeUsuario = usuario;
    this.contrasenia = contrasenia;
  }

  public void agregarCaracteristica(Caracteristica caracteristica) {
    RepositorioCaracteristicas.getInstance().agregarCaracteristica(caracteristica);
  }

  public void sacarCaracteristica(Caracteristica caracteristica) {
    RepositorioCaracteristicas.getInstance().sacarCaracteristica(caracteristica);
  }

  public void registrarse() {
    RepositorioUsuarios.getInstance().registrarAdministrador(this);
  }

  public String getNombreUsuario() {
    return nombreDeUsuario;
  }

  public String getContrasenia() {
    return contrasenia;
  }

}
