package domain.Repositorio;

import Funciones.ValidadorContrasenias;
import domain.Exceptions.UsuarioYaRegistradoException;
import domain.Persona.Administrador;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.Duenio;

import java.util.HashMap;

public class RepositorioUsuarios {

  private HashMap<String, String> usuariosRegistrados;
  private HashMap<String, Duenio> dueniosResgistrados;
  private HashMap<String, Administrador> administradoresRegistrados;
  private HashMap<String, Object> usuarios;
  private static RepositorioUsuarios INSTANCE = null;

  private RepositorioUsuarios() {
    this.usuariosRegistrados = new HashMap<>();
    this.dueniosResgistrados = new HashMap<>();
    this.administradoresRegistrados = new HashMap<>();
  }

  public static RepositorioUsuarios getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new RepositorioUsuarios();
    }
    return INSTANCE;
  }

  public void clear() {
    this.usuariosRegistrados.clear();
    this.dueniosResgistrados.clear();
    this.administradoresRegistrados.clear();
  }

  public void registrarse(String usuario, String contrasenia) {
    this.validarDatosRegistro(usuario, contrasenia);
    this.usuariosRegistrados.put(usuario, ValidadorContrasenias.passwordToHash(contrasenia));
  }

  public Duenio registrarDuenio(String usuario, String contrasenia, DatosPersonales datosPersonales) {
    this.validarDatosRegistro(usuario, contrasenia);
    String contraHasheada = ValidadorContrasenias.passwordToHash(contrasenia);
    Duenio duenio = new Duenio(usuario, contraHasheada, datosPersonales);
    this.agregarDuenio(usuario, duenio);
    return duenio;
  }

  public Administrador registrarAdministrador(String usuario, String contrasenia) {
    this.validarDatosRegistro(usuario, contrasenia);
    String contraHasheada = ValidadorContrasenias.passwordToHash(contrasenia);
    Administrador admin = new Administrador(usuario, contraHasheada);
    this.agregarAdmin(usuario, admin);
    return admin;
  }

  private void agregarDuenio(String usuario, Duenio duenio) {
    this.dueniosResgistrados.put(usuario, duenio);
  }

  public void agregarAdmin(String usuario, Administrador admin){
    this.administradoresRegistrados.put(usuario, admin);
  }

  public void existeUsuario(String nombreUsuario){
    if(this.usuariosRegistrados.containsKey(nombreUsuario)){
      throw new UsuarioYaRegistradoException("El nombre de ususario ya existe");
    }
  }

  public void validarDatosRegistro(String usuario, String contrasenia) {
    existeUsuario(usuario);
    ValidadorContrasenias validador = new ValidadorContrasenias(8);
    validador.esUnaContraseniaValida(contrasenia);
  }
}
