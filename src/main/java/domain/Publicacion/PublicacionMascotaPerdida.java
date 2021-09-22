package domain.Publicacion;

import domain.Mascota.FormularioMascotaPerdida;
import domain.Sistema.CentroDeRescate;

public class PublicacionMascotaPerdida {

 private CentroDeRescate centro;
 private FormularioMascotaPerdida formularioMascotaPerdida;

 public PublicacionMascotaPerdida(FormularioMascotaPerdida formularioMascotaPerdida) {
    filtrarInformacionSensible(formularioMascotaPerdida);
    this.formularioMascotaPerdida = formularioMascotaPerdida;
  }

  public void aceptarseEnElCentro() {
     centro.aceptarSolicitud(this);
 }

 public void setCentro(CentroDeRescate centro) {
     this.centro = centro;
 }

  private void filtrarInformacionSensible(FormularioMascotaPerdida formularioMascotaPerdida) {
    // TODO
  }

  public FormularioMascotaPerdida getFormularioMascotaPerdida() {
   return formularioMascotaPerdida;
  }
}
