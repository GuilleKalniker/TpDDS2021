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
import java.util.List;

public class RegistrarseController extends BaseController {
    public ModelAndView index(Request req, Response res) {
        setUsuarioLogueado(req);
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
        RepositorioUsuarios.getInstance().registrarUsuario(model);
        AdapterJPA.commit();

        AdapterJPA.entityManager().clear();

        setModelo(model);
        return new ModelAndView(getDiccionario(),"usuario.hbs");
    }

    private TipoDocumento stringToTipoDocumento(String s) {
        TipoDocumento tipo = TipoDocumento.DNI;
        switch (s) {
            case "DNI": tipo = TipoDocumento.DNI;
            case "LC": tipo = TipoDocumento.LC;
            case "LE": tipo = TipoDocumento.LE;
            case "CI": tipo = TipoDocumento.CI;
        }
        return tipo;
    }
}
