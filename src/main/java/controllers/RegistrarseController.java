package controllers;

import Funciones.Utils;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import domain.Repositorio.AdapterJPA;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistrarseController extends BaseController {
    public ModelAndView index(Request req, Response res) {
        setUsuarioLogueado(req);
        set("tipos_documento", TipoDocumento.values());
        return new ModelAndView(getDiccionario(), "registrarse.hbs");
    }

    public ModelAndView registrar(Request req, Response res) {
        setUsuarioLogueado(req);

        List<Contacto> contactos = new ArrayList<>();
        contactos.add(new Contacto("Facundo", "Pittaluga", 1138636324, "facupitta@hotmail.com"));
        LocalDate fechaNac = stringToLocalDate(req.queryParams("nacimiento"));
        Duenio model = new Duenio(req.queryParams("usuario"),
                req.queryParams("contrasenia"),
                new DatosPersonales(req.queryParams("nombre"), req.queryParams("apellido"), fechaNac, stringToTipoDocumento(req.queryParams("tipo_doc")), Integer.parseInt(req.queryParams("num_doc")), contactos, req.queryParams("direccion")));

        model.setUrlFotoPerfil("/sin_perfil.png");

        AdapterJPA.beginTransaction();
        model.registrarse();
        AdapterJPA.commit();

        AdapterJPA.entityManager().getEntityManagerFactory().getCache().evictAll();
        AdapterJPA.entityManager().clear();
        AdapterJPA.entityManager().close();

        setModelo(model);
        res.redirect("/");
        return null;
    }
}
