package domain.Pregunta;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("booleana")
public class Booleana extends Pregunta {

    public Booleana(String pregunta) {
        super(pregunta);
    }

    public Boolean getBooleana() {
        return true;
    }

    public Boolean getAbierta() {
        return false;
    }
    public Boolean getOpcionMultiple() {
        return false;
    }
}
