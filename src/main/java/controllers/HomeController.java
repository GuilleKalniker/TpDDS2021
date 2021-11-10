package controllers;

import domain.Repositorio.AdapterJPA;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class HomeController extends BaseController {

    public ModelAndView index(Request req, Response res) {
        AdapterJPA.cleanCache();

        setUsuarioLogueado(req);

        AdapterJPA.cleanCache();
        return new ModelAndView(getDiccionario(), "home.hbs");
    }
}
