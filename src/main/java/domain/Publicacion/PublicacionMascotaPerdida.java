package domain.Publicacion;

import domain.Mascota.FormularioMascotaPerdida;
import domain.Sistema.CentroDeRescate;

import javax.persistence.*;

@Entity
@Table (name = "publicacionMascotaPerdida")
public class PublicacionMascotaPerdida {

    @Id
    @GeneratedValue
    private long id;

    @Transient
    private CentroDeRescate centro;

    @Embedded
    private FormularioMascotaPerdida formularioMascotaPerdida;


    public PublicacionMascotaPerdida(FormularioMascotaPerdida formularioMascotaPerdida) {
       filtrarInformacionSensible(formularioMascotaPerdida);
       this.formularioMascotaPerdida = formularioMascotaPerdida;
    }

    public void aceptarseEnElCentro() {
         centro.aceptarSolicitud(this);
     }

    public void eliminarseEnElCentro() {
         centro.eliminarSolicitud(this);
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
