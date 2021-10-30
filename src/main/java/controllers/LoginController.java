package controllers;

import Funciones.ValidadorContrasenias;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import domain.Persona.Usuario;
import domain.Repositorio.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoginController {
    public ModelAndView index(Request req, Response res) {
        return new ModelAndView(null, "login.hbs");
    }

    public ModelAndView loguearse(Request req, Response res) {
        String user = req.queryParams("usuario");

        System.out.println(user);
        System.out.println(req.queryParams("contrasenia"));

        Usuario u = RepositorioUsuarios.getInstance().getUsuarioPorNombre(user);

        System.out.println(u.getNombreUsuario());

        System.out.println("Contrasenia del chabon: " + u.getContraseniaHasheada());
        System.out.println("Hash del chabon: " + u.getSalt());

        System.out.println("Contrasenia recibida: " + ValidadorContrasenias.passwordToHash(req.queryParams("contrasenia"), u.getSalt()));

        if (u.matcheaContrasenia(req.queryParams("contrasenia"))) {
            System.out.println("Entre al if");
            res.cookie("usuario_logueado", user);
        } else {
            return new ModelAndView(null, "login.hbs");
        }
        return new ModelAndView(null, "home.hbs");
    }
}
