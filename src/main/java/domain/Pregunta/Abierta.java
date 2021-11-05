package domain.Pregunta;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("abierta")
public class Abierta extends Pregunta {

    public Abierta(String pregunta) {
        super(pregunta);
    }
    public Abierta() {}

    public Boolean getBooleana() {
        return false;
    }

    public Boolean getAbierta() {
        return true;
    }
    public Boolean getOpcionMultiple() {
        return false;
    }
}
