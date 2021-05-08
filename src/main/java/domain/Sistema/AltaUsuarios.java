package domain.Sistema;

import domain.Exceptions.ContraseniaInvalidaException;
import domain.Exceptions.UsuarioYaRegistradoException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class AltaUsuarios {

  private HashMap<String, String> usuariosRegistrados;

  //TODO evitar que este harcodeado
  private static String path = "src/lista_contraseñas_no_seguras/lista_contranias_no_seguras.txt";
  private Integer longitudMinimaContrasenia = 8;

  //un singleton mas o no :p
  public AltaUsuarios() {
    this.usuariosRegistrados = new HashMap<String, String>();
  }

  /*
  public Boolean existeUsuario(String nombreUsuario){
    return this.usuariosRegistrados.containsKey(nombreUsuario);
  } */

  public Boolean existeUsuario(String nombreUsuario){
    Boolean resultado = this.usuariosRegistrados.containsKey(nombreUsuario);
    if(resultado){
      throw new UsuarioYaRegistradoException();
    }
    return false;
  }

  //TODO modelar el comportamiento en caso de no poder registrar a un usuario
  public void registrarse(String usuario, String contrasenia) {
    if(!existeUsuario(usuario) && esUnaContraseniaValida(contrasenia)) {
      this.usuariosRegistrados.put(usuario, contrasenia);
    }
  }

  //TODO controlar la excepcion que es lanzada por la funcion esValida() en la clase ValidarContrasnia ;)
  //TODO pensar si mover todas estas funciones a una clase que se encarge exclusivamente de validar contrasenias

  /*
  public Boolean esUnaContraseniaValida(String contrasenia) throws Exception {
    return cumpleLongitudMinima(contrasenia) && !existeContraseniaEnListaContraseniasNoSeguras(contrasenia);
  } */


  public Boolean esUnaContraseniaValida(String contrasenia) {
    Boolean resultado = cumpleLongitudMinima(contrasenia) && !existeContraseniaEnListaContraseniasNoSeguras(contrasenia);
    if(!resultado){
      throw new ContraseniaInvalidaException();
    }
    return true;
  }

  public Boolean cumpleLongitudMinima(String contrasenia){
    return contrasenia.length() >= this.longitudMinimaContrasenia;
  }

  public Boolean existeContraseniaEnListaContraseniasNoSeguras(String contrasenia) {

    File archivoListaContrasenias = new File(path);
    try {
      FileReader fr = new FileReader(archivoListaContrasenias);
      BufferedReader br = new BufferedReader(fr);

      String contraseniaEnLista;

      while((contraseniaEnLista=br.readLine()) != null) {
        if (contrasenia.equals(contraseniaEnLista)) {

          fr.close();
          br.close();
          return true;
        }
      }
      fr.close();
      br.close();
      return false;
    }
    catch (Exception e) {
      throw new ContraseniaInvalidaException();
    }
  }
}
