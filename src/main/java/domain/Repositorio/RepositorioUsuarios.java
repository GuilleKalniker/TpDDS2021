package domain.Repositorio;

import domain.Exceptions.ContraseniaInvalidaException;
import domain.Exceptions.UsuarioYaRegistradoException;
import domain.Persona.DatosPersonales;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.HashMap;

public class RepositorioUsuarios {

  private String path;
  private Integer longitudMinimaContrasenia;
  private HashMap<String, String> usuariosRegistrados;
  private static RepositorioUsuarios instance = null;

  private RepositorioUsuarios() {
    this.usuariosRegistrados = new HashMap<>();
    this.path = "src/lista_contraseñas_no_seguras/lista_contraseñas_no_seguras.txt";
    this.longitudMinimaContrasenia = 8;
  }

  public static RepositorioUsuarios getInstance() {
    if (instance == null) {
      instance = new RepositorioUsuarios();
    }
    return instance;
  }

  public void clear() {
    this.usuariosRegistrados.clear();
  }

  public void registrarse(String usuario, String contrasenia) {
    this.existeUsuario(usuario);
    this.esUnaContraseniaValida(contrasenia);
    this.usuariosRegistrados.put(usuario, passwordToHash(contrasenia));
  }

  public void registrarDuenio(String usuario, String contrasenia, DatosPersonales datos) {
    //validamso usuario
    //validamos contrasenia
    // new duenio(usuario, contrasenia, datos)?
    // add duenio
    // return duenio
  }

  public void registrarAdmin(String usuario, String contrasenia) {
    //validamso usuario
    //validamos contrasenia
    // new admin(usuario, contrasenia)
    // add admin
    // return admin
  }


  /**
   * Metodos de validaciones sobre nombre de usuario y contraseña
   */

  public void existeUsuario(String nombreUsuario){
    if(this.usuariosRegistrados.containsKey(nombreUsuario)){
      throw new UsuarioYaRegistradoException("nombre usuario no es valido");
    }
  }

  public void esUnaContraseniaValida(String contrasenia) {
    if(!cumpleLongitudMinima(contrasenia) && existeContraseniaEnListaContraseniasNoSeguras(contrasenia)){
      throw new ContraseniaInvalidaException("contrasenia no es valida");
    }
  }

  public Boolean cumpleLongitudMinima(String contrasenia){
    return contrasenia.length() >= this.longitudMinimaContrasenia;
  }

  public Boolean existeContraseniaEnListaContraseniasNoSeguras(String contrasenia) {

    File archivoListaContrasenias = new File(path);
    Boolean resultadoBusqueda = false;
    try {
      FileReader fr = new FileReader(archivoListaContrasenias);
      BufferedReader br = new BufferedReader(fr);

      String contraseniaEnLista;

      while((contraseniaEnLista=br.readLine()) != null) {
        if ((resultadoBusqueda = contrasenia.equals(contraseniaEnLista))) {
          break;
        }
      }
      fr.close();
      br.close();
    }
    catch (Exception e) {
      throw new ContraseniaInvalidaException("Error al cargar el archivo de contrasenias");
    }
    return resultadoBusqueda;
  }

  public String passwordToHash(String password){

    //generamos la salt
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);

    //generamos el hash
    try {
      KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 10000, 128);
      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      byte[] hash = factory.generateSecret(spec).getEncoded();
      return new String(hash);
    }
    catch (NoSuchAlgorithmException | InvalidKeySpecException e){
      e.printStackTrace();
      return null;
    }
  }

}
