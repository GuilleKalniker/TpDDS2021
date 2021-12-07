package Funciones;

import domain.Exceptions.ContraseniaInvalidaException;
import domain.Repositorio.AdapterJPA;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.Query;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class ValidadorContrasenias {

  private static final Integer LONGITUDMINIMA = 8;
  private static final String PATH = "src/resources/lista_contrasenias_no_seguras.txt";


  public static Boolean esContraseniaValida(String contrasenia) {
    return cumpleLongitudMinima(contrasenia) && !existeContraseniaEnListaContraseniasNoSeguras(contrasenia);
  }

  public static Boolean cumpleLongitudMinima(String contrasenia){
    return contrasenia.length() >= LONGITUDMINIMA;
  }

  public static Boolean existeContraseniaEnListaContraseniasNoSeguras(String contrasenia)  {
/*
    File archivoListaContrasenias = new File(PATH);
    boolean resultadoBusqueda = false;
    try {
      FileInputStream fr = new FileInputStream(archivoListaContrasenias);
      Reader fileReader = new InputStreamReader(fr, StandardCharsets.UTF_8);
      BufferedReader br = new BufferedReader(fileReader);

      String contraseniaEnLista;

      while((contraseniaEnLista=br.readLine()) != null) {
        if ((resultadoBusqueda = contrasenia.equals(contraseniaEnLista))) {
          break;
        }
      }
      fr.close();
      br.close(); */
/*
    Query q = AdapterJPA.entityManager().createNativeQuery("select contrasenia from lista_contrasenias_no_seguras where contrasenia = :pw");
    q.setParameter("pw", contrasenia);

    if(q.getSingleResult() != null) {
      throw new ContraseniaInvalidaException("Error al cargar el archivo de contraseñas.");
    } */

    return false; //resultadoBusqueda;
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
