package domain.Mascota;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Repositorio.AdapterJPA;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Query;

@Entity
public class CaracteristicasActuales {

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
      Query queryVoladora = AdapterJPA.createQuery("deletefrom");
    }

}
