package controllers;

import Funciones.StringDate;
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
import java.util.Arrays;
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
        LocalDate fechaNac = StringDate.stringToLocalDate(req.queryParams("nacimiento"));
        Duenio model = new Duenio(req.queryParams("usuario"),
                req.queryParams("contrasenia"),
                new DatosPersonales(req.queryParams("nombre"), req.queryParams("apellido"), fechaNac, stringToTipoDocumento(req.queryParams("tipo_doc")), Integer.valueOf(req.queryParams("num_doc")), contactos, req.queryParams("direccion")));

        AdapterJPA.beginTransaction();
        model.registrarse();
        AdapterJPA.commit();

        AdapterJPA.entityManager().getEntityManagerFactory().getCache().evictAll();
        AdapterJPA.entityManager().clear();
        AdapterJPA.entityManager().close();

        setModelo(model);
        return new ModelAndView(getDiccionario(),"usuario.hbs");
    }

    private TipoDocumento stringToTipoDocumento(String s) {
        return Arrays.stream(TipoDocumento.values()).filter(tipo -> tipo.name().equals(s)).findFirst().get();
    }
}
