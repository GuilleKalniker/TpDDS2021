package domain.Persona;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.DatosMascotaPerdida;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Sistema.CentroDeRescate;
import java.time.LocalDate;


public class Rescatista {

  private DatosPersonales datosPersonales;
  private String direccion;

  public Rescatista(DatosPersonales datosPersonales, String direccion) {
    this.datosPersonales = datosPersonales;
    this.direccion = direccion;
  }

  //Esta lógica va para cuando la mascota tiene chapita
  public void notificarMascotaEncontrada(DatosMascotaPerdida datosMascotaPerdida) {
    RepositorioCentroDeRescate.getInstance().getCentroDeRescateMasCercanoA(datosMascotaPerdida.getLugarEncuentro()).agregarDatosMascotaPerdida(datosMascotaPerdida);
  }

  public void agregarPublicacionMascotaEncontrada(){}
}