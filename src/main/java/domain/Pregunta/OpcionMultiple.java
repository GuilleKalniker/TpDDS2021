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
    public OpcionMultiple() {}
    public List<String> getOpciones() {
        return opciones;
    }
    public Boolean getOpcionMultiple() {
        return true;
    }

}
