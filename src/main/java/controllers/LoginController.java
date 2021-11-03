package controllers;


import domain.Persona.Usuario;
import domain.Repositorio.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LoginController extends BaseController {

    public ModelAndView index(Request req, Response res) {
        setUsuarioLogueado(req);
        set("credenciales_invalidas", false);
        return new ModelAndView(getDiccionario(), "login.hbs");
    }

    public ModelAndView loguearse(Request req, Response res) {
        setUsuarioLogueado(req);
        String user = req.queryParams("usuario");

        Usuario u = RepositorioUsuarios.getInstance().getUsuarioPorNombre(user);

        if (u != null && u.matcheaContrasenia(req.queryParams("contrasenia"))) {
            res.cookie("usuario_logueado", user);
        } else {
            set("credenciales_invalidas", true);
            return new ModelAndView(getDiccionario(), "login.hbs");
        }
        res.redirect("/");
        return null;
    }

    public ModelAndView desloguearse(Request req, Response res) {
        res.removeCookie("usuario_logueado");
        res.redirect("/");
        return null;
    }
}
