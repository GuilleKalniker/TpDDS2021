package controllers;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import domain.Pregunta.Abierta;
import domain.Pregunta.Pregunta;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioUsuarios;
import domain.Sistema.CentroDeRescate;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdopcionController extends BaseController {
    public ModelAndView index(Request req, Response res) {
        setUsuarioLogueado(req);
        CentroDeRescate model = new CentroDeRescate(new Ubicacion(0.0,0.0));
        Pregunta pregunta = new Abierta("kbokvb ?");
        model.agregarPregunta(pregunta);
        setModelo(model);
        return new ModelAndView(getDiccionario(), "ponerEnAdopcion.hbs");
    }

    public ModelAndView publicar(Request req, Response res) {
        setUsuarioLogueado(req);

        return new ModelAndView(getDiccionario(),"home.hbs");
    }
}
