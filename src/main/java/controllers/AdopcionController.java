package controllers;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import domain.Pregunta.Abierta;
import domain.Pregunta.Booleana;
import domain.Pregunta.OpcionMultiple;
import domain.Pregunta.Pregunta;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Repositorio.RepositorioUsuarios;
import domain.Sistema.CentroDeRescate;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdopcionController extends BaseController {
    public ModelAndView index(Request req, Response res) {
        setUsuarioLogueado(req);
        CentroDeRescate model;
        if (RepositorioCentroDeRescate.getInstance().getCentrosDeRescateRegistrados().isEmpty()) {
            model = new CentroDeRescate(new Ubicacion(0.0,0.0));

            AdapterJPA.beginTransaction();

            RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(model);

            model.agregarPregunta(new Abierta("¿Qué le gusta hacer?"));
            model.agregarPregunta(new Booleana("¿Está castrada?"));
            model.agregarPregunta(new OpcionMultiple("¿Qué vacunas tiene?", Arrays.asList("Sinopharm", "Astrazeneca", "Sputnik")));
            model.agregarPregunta(new OpcionMultiple("¿Cuantas veces sale a pasear por día?", Arrays.asList("1 vez", "2 veces", "3 o más veces")));


            AdapterJPA.commit();
        } else
            model = RepositorioCentroDeRescate.getInstance().getCentrosDeRescateRegistrados().get(0);

        setModelo(model);
        return new ModelAndView(getDiccionario(), "ponerEnAdopcion.hbs");
    }

    public ModelAndView publicar(Request req, Response res) {
        setUsuarioLogueado(req);

        return new ModelAndView(getDiccionario(),"home.hbs");
    }
}
