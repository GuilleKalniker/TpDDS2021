package domain.Publicacion;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Sistema.CentroDeRescate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name = "publicacionMascotaPerdida")
public class PublicacionMascotaPerdida {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private CentroDeRescate centro;

    @OneToOne
    private FormularioMascotaPerdida formularioMascotaPerdida;

    public PublicacionMascotaPerdida(FormularioMascotaPerdida formularioMascotaPerdida) {
       this.formularioMascotaPerdida = formularioMascotaPerdida;
    }

    public String getFoto(){
       return formularioMascotaPerdida.getFoto();
    }
  public String getFecha(){
    return formularioMascotaPerdida.getFechaEncuentro();
  }
  public Ubicacion getLugar(){
    return formularioMascotaPerdida.getLugarEncuentro();
  }
  public String getDescripcion(){
    return formularioMascotaPerdida.getDescripcion();
  }

    public PublicacionMascotaPerdida(){}

    public void aceptarseEnElCentro() {
         centro.aceptarSolicitud(this);
     }

    public void eliminarseEnElCentro() {
         centro.eliminarSolicitud(this);
     }

    public void setCentro(CentroDeRescate centro) {
         this.centro = centro;
     }

      public FormularioMascotaPerdida getFormularioMascotaPerdida() {
       return formularioMascotaPerdida;
      }
}
