package domain.Persona;

import domain.Mascota.FormularioMascotaPerdida;
import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Publicacion.SolicitudPublicacion;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Servicios.ClasesParaLaConsulta.HogarTransito;
import domain.Sistema.CentroDeRescate;

import java.util.List;


public class Rescatista {

  private RepositorioCentroDeRescate repositorioCentroDeRescate = RepositorioCentroDeRescate.getInstance();

  public void setRepositorioCentroDeRescate(RepositorioCentroDeRescate repositorioCentroDeRescate) {
    this.repositorioCentroDeRescate = repositorioCentroDeRescate;
  }

  //Esta lógica va para cuando la mascota tiene chapita
  //TODO: Ver logica pq el repositorio esta medio raro --> ver si hacer un centroGeneral, meter logica en en el repositorio o dejarlo asi
  public void notificarMascotaEncontradaConID(FormularioMascotaPerdida formularioMascotaPerdida, CentroDeRescate centroDeRescate) {
   centroDeRescate.cargarMascotaPerdida(formularioMascotaPerdida);
  }

  public void generarSolicitudPublicacion(FormularioMascotaPerdida formulario){
    CentroDeRescate centroMasCercano = repositorioCentroDeRescate.getCentroDeRescateMasCercanoA(formulario.getLugarEncuentro());
    centroMasCercano.generarSolicitud(new SolicitudPublicacion(new PublicacionMascotaPerdida(formulario)));
  }

  //TODO: Preguntar que hacer con el centro, xq el rescatista no tiene por que saber que centros hay pero tampoco sabemos si poner la logica
  // en el repositorio porque es medio raro, el repositorio, segun entendemos, solo deberia almacenar informacion.

  public List<HogarTransito> buscarHogaresDeTransito(FormularioMascotaPerdida formularioMascotaPerdida, Double radio, CentroDeRescate centro){
    return centro.hogaresAdecuadosParaMascota(formularioMascotaPerdida,radio);
  }
}