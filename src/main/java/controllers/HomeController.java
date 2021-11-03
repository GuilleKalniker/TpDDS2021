package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController extends BaseController {

    public ModelAndView index(Request req, Response res) {
        setUsuarioLogueado(req);
        return new ModelAndView(getDiccionario(), "home.hbs");
    }
}
