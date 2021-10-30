package Funciones;

import domain.Exceptions.ContraseniaInvalidaException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class ValidadorContrasenias {

  private static final Integer LONGITUDMINIMA = 8;
  private static final String PATH = "src/lista_contraseñas_no_seguras/lista_contraseñas_no_seguras.txt";


  public static Boolean esContraseniaValida(String contrasenia) {
    return cumpleLongitudMinima(contrasenia) && !existeContraseniaEnListaContraseniasNoSeguras(contrasenia);
  }

  public static Boolean cumpleLongitudMinima(String contrasenia){
    return contrasenia.length() >= LONGITUDMINIMA;
  }

  public static Boolean existeContraseniaEnListaContraseniasNoSeguras(String contrasenia) {

    File archivoListaContrasenias = new File(PATH);
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
      throw new ContraseniaInvalidaException("Error al cargar el archivo de contraseñas.");
    }
    return resultadoBusqueda;
  }

  public static String generarSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);

    return new String(salt);
  }

  public static String passwordToHash(String password, String salt){
    try {
      KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 10000, 128);
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
