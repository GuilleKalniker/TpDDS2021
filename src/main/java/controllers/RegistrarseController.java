package controllers;

import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistrarseController {
    public ModelAndView index(Request req, Response res) {
        return new ModelAndView(null, "registrarse.hbs");
    }

    public ModelAndView registrar(Request req, Response res) {

        System.out.println(req.queryParams("contrasenia"));

        List<Contacto> contactos = new ArrayList<>();
        contactos.add(new Contacto("Facundo", "Pittaluga", 1138636324, "facupitta@hotmail.com"));
        Duenio model = new Duenio(req.queryParams("usuario"),
                req.queryParams("contrasenia"),
                new DatosPersonales(req.queryParams("nombre"), req.queryParams("apellido"), LocalDate.now(), TipoDocumento.DNI, 42375218, contactos, req.queryParams("direccion")));

        AdapterJPA.beginTransaction();
        RepositorioUsuarios.getInstance().registrarUsuario(model);
        AdapterJPA.commit();

        AdapterJPA.entityManager().clear();

        return new ModelAndView(model,"usuario.hbs");
    }
}
