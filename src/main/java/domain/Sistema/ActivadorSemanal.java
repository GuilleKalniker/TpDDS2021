package domain.Sistema;

import domain.Persona.Administrador;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioCentroDeRescate;

import java.util.Random;

public class ActivadorSemanal {

  //Envia las recomendaciones semanales a los usuarios, se ejecuta con crontabs
  public static void main(String[] args) {
    System.out.println("activador");
    enviarRecomendiacionesSemanales();
  }

  public static void enviarRecomendiacionesSemanales() {

    RepositorioCentroDeRescate.getInstance().getCentrosDeRescateRegistrados().forEach(centro -> centro.notificacionSemanal());
  }
}
