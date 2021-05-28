package Funciones;

import domain.Exceptions.ContraseniaInvalidaException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class ValidadorContrasenias {

  private final Integer LONGITUDMINIMA;
  private final String PATH;

  public ValidadorContrasenias(Integer LONGITUDMINIMA) {
    this.LONGITUDMINIMA = LONGITUDMINIMA;
    this.PATH = "src/lista_contraseñas_no_seguras/lista_contraseñas_no_seguras.txt";
  }

  public void esUnaContraseniaValida(String contrasenia) {
    if(!cumpleLongitudMinima(contrasenia) && existeContraseniaEnListaContraseniasNoSeguras(contrasenia)){
      throw new ContraseniaInvalidaException("contrasenia no es valida");
    }
  }

  public Boolean cumpleLongitudMinima(String contrasenia){
    return contrasenia.length() >= this.LONGITUDMINIMA;
  }

  public Boolean existeContraseniaEnListaContraseniasNoSeguras(String contrasenia) {

    File archivoListaContrasenias = new File(this.PATH);
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


  public static String passwordToHash(String password){

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