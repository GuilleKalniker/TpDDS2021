package Funciones;

import domain.Exceptions.ContraseniaInvalidaException;
import domain.Repositorio.AdapterJPA;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.Query;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ValidadorContrasenias {

  private static final Integer LONGITUDMINIMA = 8;
  private static final String PATH = "https://res.cloudinary.com/utn-frba/raw/upload/v1639879936/lista_contrasenias_no_seguras_h316qd.txt";


  public static Boolean esContraseniaValida(String contrasenia) {
    return cumpleLongitudMinima(contrasenia) && !existeContraseniaEnListaContraseniasNoSeguras(contrasenia);
  }

  public static Boolean cumpleLongitudMinima(String contrasenia){
    return contrasenia.length() >= LONGITUDMINIMA;
  }

  public static Boolean existeContraseniaEnListaContraseniasNoSeguras(String contrasenia) {
    boolean resultadoBusqueda = false;
    try {
      URL archivoListaContrasenias = new URL(PATH);
      Scanner s = new Scanner(archivoListaContrasenias.openStream());

      while (s.hasNextLine()) {
        if ((resultadoBusqueda = contrasenia.equals(s.nextLine()))) {
          break;
        }
      }
      s.close();
      }
      catch (IOException e) {
        throw new ContraseniaInvalidaException("Error al cargar el archivo de contraseñas.");
      }
    return resultadoBusqueda;
    }

  public static byte[] generarSalt() {
    SecureRandom random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    return salt;
  }

  public static byte[] passwordToHash(String password, byte[] salt){
    try {
      KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 10000, 128);
      SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      byte[] hash = factory.generateSecret(spec).getEncoded();

      return hash;
    }
    catch (NoSuchAlgorithmException | InvalidKeySpecException e){
      e.printStackTrace();
      return null;
    }
  }
}
