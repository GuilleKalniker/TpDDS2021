package domain.Repositorio;

import Funciones.ValidadorContrasenias;
import domain.Exceptions.UsuarioYaRegistradoException;
import domain.Persona.Administrador;
import domain.Persona.Duenio;
import domain.Persona.Voluntario;
import domain.Exceptions.*;

import java.util.HashMap;
import java.util.Set;

public class RepositorioUsuarios {

  private HashMap<Duenio, String> dueniosRegistrados;
  private HashMap<Administrador, String> administradoresRegistrados;
  private HashMap<Voluntario, String> voluntariosRegistrados;

  private static RepositorioUsuarios INSTANCE = null;

  private RepositorioUsuarios() {
    this.dueniosRegistrados = new HashMap<>();
    this.administradoresRegistrados = new HashMap<>();
    this.voluntariosRegistrados = new HashMap<>();
  }

  public static RepositorioUsuarios getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new RepositorioUsuarios();
    }
    return INSTANCE;
  }

  public Set<Duenio> getDueniosRegistrados() {
    return dueniosRegistrados.keySet();
  }

  public Set<Administrador> getAdministradoresRegistrados() {
    return administradoresRegistrados.keySet();
  }

  public Set<Voluntario> getVoluntariosRegistrados() {
    return voluntariosRegistrados.keySet();
  }

  public void clear() {
    this.dueniosRegistrados.clear();
  }

  public void registrarDuenio(Duenio duenio) {
    this.validarDatosRegistro(duenio.getNombreUsuario(), duenio.getContrasenia());
    this.dueniosRegistrados.put(duenio, ValidadorContrasenias.passwordToHash(duenio.getContrasenia()));
  }

  public void registrarAdministrador(Administrador administrador) {
    this.validarDatosRegistro(administrador.getNombreUsuario(), administrador.getContrasenia());
    this.administradoresRegistrados.put(administrador, ValidadorContrasenias.passwordToHash(administrador.getContrasenia()));
  }

  public void registrarVoluntario(Voluntario voluntario) {
    this.validarDatosRegistro(voluntario.getNombreUsuario(), voluntario.getContrasenia());
    this.voluntariosRegistrados.put(voluntario, ValidadorContrasenias.passwordToHash(voluntario.getContrasenia()));
  }

  public void existeUsuario(String nombreUsuario){
    if (nombreUsuarioEnDuenio(nombreUsuario)
        || nombreUsuarioEnAdministrador(nombreUsuario)
        || nombreUsuarioEnVoluntario(nombreUsuario)){
      throw new UsuarioYaRegistradoException("El nombre de ususario ya existe.");
    }
  }

  // No seria mejor una func que verifique en todas las listas y devuelva el string de que es?
  // Mucha rep de codigo, aparte que si queres saber si es admin ponele, le comparas desde donde llama a "Administrador" y listo
  public boolean nombreUsuarioEnDuenio(String nombreUsuario) {
    return this.dueniosRegistrados.keySet().stream().anyMatch(duenio -> duenio.getNombreUsuario() == nombreUsuario);
  }

  public boolean nombreUsuarioEnAdministrador(String nombreUsuario) {
    return this.administradoresRegistrados.keySet().stream().anyMatch(duenio -> duenio.getNombreUsuario() == nombreUsuario);
  }

  public boolean nombreUsuarioEnVoluntario(String nombreUsuario) {
    return this.voluntariosRegistrados.keySet().stream().anyMatch(duenio -> duenio.getNombreUsuario() == nombreUsuario);
  }

  public void validarDatosRegistro(String usuario, String contrasenia) {
    existeUsuario(usuario);
    ValidadorContrasenias validador = new ValidadorContrasenias(8);
    validador.esUnaContraseniaValida(contrasenia);
  }

  public Duenio getDuenioPorID(String ID) {
    return getDueniosRegistrados().stream().filter(duenio -> duenio.tieneA(ID)).findFirst().orElseThrow(() -> new IDNoSeCorrespondeException());
  }
}
