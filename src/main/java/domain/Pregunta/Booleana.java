package domain.Pregunta;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("booleana")
public class Booleana extends Pregunta {

    public Booleana(String pregunta) {
        super(pregunta);
    }
    public Booleana() {}
    public Boolean getBooleana() {
        return true;
    }
}
