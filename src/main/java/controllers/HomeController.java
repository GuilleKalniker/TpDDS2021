package controllers;

import domain.Mascota.FormularioMascotaPerdida;
import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioCentroDeRescate;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;


public class HomeController extends BaseController {

    public ModelAndView index(Request req, Response res) {
        init(req);

        List<FormularioMascotaPerdida> formularios = RepositorioCentroDeRescate.getInstance().getFormularios();
        setModelo(formularios);

        return new ModelAndView(getDiccionario(), "home.hbs");
    }

}
