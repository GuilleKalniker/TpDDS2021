package domain.Persona;

import domain.Mascota.FormularioMascotaPerdida;
import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Publicacion.SolicitudPublicacion;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Sistema.CentroDeRescate;


public class Rescatista {

  //Esta lógica va para cuando la mascota tiene chapita
  //TODO: Ver logica pq el repositorio esta medio raro --> ver si hacer un centroGeneral, meter logica en en el repositorio o dejarlo asi
  public void notificarMascotaEncontradaConID(FormularioMascotaPerdida formularioMascotaPerdida) {
    RepositorioCentroDeRescate.getInstance().getCentroDeRescateMasCercanoA(formularioMascotaPerdida.getLugarEncuentro()).cargarMascotaPerdida(formularioMascotaPerdida);
  }

  public void generarSolicitudPublicacion(FormularioMascotaPerdida formulario){
    CentroDeRescate centroMasCercano = RepositorioCentroDeRescate.getInstance().getCentroDeRescateMasCercanoA(formulario.getLugarEncuentro());
    centroMasCercano.generarSolicitud(new SolicitudPublicacion(new PublicacionMascotaPerdida(formulario)));
  }
}