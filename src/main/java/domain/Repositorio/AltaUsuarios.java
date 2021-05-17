package domain.Repositorio;

import domain.Exceptions.ContraseniaInvalidaException;
import domain.Exceptions.UsuarioYaRegistradoException;

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

public class AltaUsuarios {

  private HashMap<String, String> usuariosRegistrados;
  private AltaUsuarios instance = null;

  //TODO evitar que este harcodeado
  private static final String path = "src/lista_contraseñas_no_seguras/lista_contraseñas_no_seguras.txt";
  private Integer longitudMinimaContrasenia = 8;

  public AltaUsuarios() {
    this.usuariosRegistrados = new HashMap<String, String>();
  }

  public AltaUsuarios getInstance() {
    if(this.instance == null){
      this.instance = new AltaUsuarios();
    }
    return this.instance;
  }

  public void existeUsuario(String nombreUsuario){
    if(this.usuariosRegistrados.containsKey(nombreUsuario)){
      throw new UsuarioYaRegistradoException();
    }
  }

  //TODO modelar el comportamiento en caso de no poder registrar a un usuario
  public void registrarse(String usuario, String contrasenia) {
    this.esUnaContraseniaValida(contrasenia);
    this.existeUsuario(usuario);

    this.usuariosRegistrados.put(usuario, passwordToHash(contrasenia));

  }

  public void esUnaContraseniaValida(String contrasenia) {
    if(!cumpleLongitudMinima(contrasenia) && existeContraseniaEnListaContraseniasNoSeguras(contrasenia)){
      throw new ContraseniaInvalidaException();
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
      throw new ContraseniaInvalidaException();
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
