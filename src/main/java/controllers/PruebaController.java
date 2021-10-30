package controllers;

import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import domain.Persona.Usuario;
import domain.Repositorio.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PruebaController {
    public ModelAndView index(Request req, Response res) {
        List<Duenio> model = RepositorioUsuarios.getInstance().getDueniosRegistrados();
        return new ModelAndView(model, "usuario.hbs");
    }
}
