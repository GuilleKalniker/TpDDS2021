package controllers;

import domain.Mascota.FormularioMascotaPerdida;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;

public class HomeController {

    public ModelAndView index(Request req, Response res) {
        List<FormularioMascotaPerdida> model = new ArrayList<>();
        return new ModelAndView(model, "home.hbs");
    }
}
