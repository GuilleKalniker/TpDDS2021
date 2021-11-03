package controllers;

import domain.Persona.Duenio;
import domain.Repositorio.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


import java.util.List;

public class PruebaController extends BaseController {

    public ModelAndView todos(Request req, Response res) {
        setUsuarioLogueado(req);
        List<Duenio> model = RepositorioUsuarios.getInstance().getDueniosRegistrados();
        setModelo(model);
        return new ModelAndView(getDiccionario(), "puto.hbs");
    }

    public ModelAndView me(Request req, Response res) {
        setUsuarioLogueado(req);
        if (getDiccionario().get("usuario_logueado") == null) {
            res.redirect("/");
        }
        setModelo(null);
        return new ModelAndView(getDiccionario(), "usuario.hbs");
    }
}
