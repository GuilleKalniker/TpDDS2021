package domain.Publicacion;

import domain.Mascota.FormularioMascotaPerdida;

public class PublicacionMascotaPerdida {
 private FormularioMascotaPerdida formularioMascotaPerdida;


 public PublicacionMascotaPerdida(FormularioMascotaPerdida formularioMascotaPerdida) {
    filtrarInformacionSensible(formularioMascotaPerdida);
    this.formularioMascotaPerdida = formularioMascotaPerdida;
  }

  private void filtrarInformacionSensible(FormularioMascotaPerdida formularioMascotaPerdida) {
    // TODO
  }

  public FormularioMascotaPerdida getFormularioMascotaPerdida() {
   return formularioMascotaPerdida;
  }
}
