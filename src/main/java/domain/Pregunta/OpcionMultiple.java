package domain.Pregunta;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("multiple")
public class OpcionMultiple extends Pregunta {

    @ElementCollection
    List<String> opciones;

    public OpcionMultiple(String pregunta, List<String> opciones) {
        super(pregunta);
        this.opciones = opciones;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public Boolean getBooleana() {
        return false;
    }

    public Boolean getAbierta() {
        return false;
    }
    public Boolean getOpcionMultiple() {
        return true;
    }

}
