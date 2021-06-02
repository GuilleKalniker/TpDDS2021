package domain.Publicacion;

import domain.Exceptions.EsUnaMascotaConIDException;
import domain.Mascota.FormularioMascotaPerdida;

public class PublicacionMascotaPerdida {
 private FormularioMascotaPerdida formularioMascotaPerdida;


 public PublicacionMascotaPerdida(FormularioMascotaPerdida formularioMascotaPerdida) {
    validarQueNoTieneID();
    filtrarInformacionSensible(formularioMascotaPerdida);
    this.formularioMascotaPerdida = formularioMascotaPerdida;
  }

  private void filtrarInformacionSensible(FormularioMascotaPerdida formularioMascotaPerdida) {
    // TODO
  }

  public void validarQueNoTieneID(){
    if (formularioMascotaPerdida.getIDMascotaPerdida() != null){
      throw new EsUnaMascotaConIDException();
    }
  }
}
