package domain.Sistema;

import domain.Repositorio.RepositorioCentroDeRescate;

public class ActivadorSemanal {

  //Envia las recomendaciones semanales a los usuarios, se ejecuta con crontabs
  public static void main(String[] args) {
    enviarRecomendiacionesSemanales();
  }

  public static void enviarRecomendiacionesSemanales() {

    RepositorioCentroDeRescate.getInstance().getCentrosDeRescateRegistrados().forEach(centro -> centro.notificacionSemanal());
  }
}
