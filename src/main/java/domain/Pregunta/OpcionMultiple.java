package domain.Pregunta;

import java.util.List;

public class OpcionMultiple extends Pregunta {
    List<String> opciones;

    public OpcionMultiple(String pregunta, List<String> opciones) {
        super(pregunta, "multiple");
        this.opciones = opciones;
    }

    public List<String> getOpciones() {
        return opciones;
    }
}
