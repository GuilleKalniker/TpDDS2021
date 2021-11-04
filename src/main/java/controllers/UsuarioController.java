package controllers;

import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.Duenio;
import domain.Repositorio.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


import java.util.List;

public class UsuarioController extends BaseController {

    public ModelAndView todos(Request req, Response res) {
        setUsuarioLogueado(req);
        List<Duenio> model = RepositorioUsuarios.getInstance().getDueniosRegistrados();
        setModelo(model);
        return new ModelAndView(getDiccionario(), "puto.hbs");
    }

    public ModelAndView contactos(Request req, Response res) {
        setUsuarioLogueado(req);
        long id = Long.parseLong(req.params("id"));
        Duenio duenio =  RepositorioUsuarios.getInstance().getDuenio(id);
        if (duenio == null)
            res.redirect("/404");
        List<Contacto> contactos = duenio.getDatosPersonales().getContactos();

        setModelo(contactos);
        return new ModelAndView(getDiccionario(), "contactos.hbs");
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
