package controllers;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioUsuarios;
import domain.Sistema.CentroDeRescate;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AdopcionController extends BaseController {
    public ModelAndView index(Request req, Response res) {
        setUsuarioLogueado(req);
        CentroDeRescate model = new CentroDeRescate(new Ubicacion(0.0,0.0));
        setModelo(model);
        return new ModelAndView(getDiccionario(), "ponerEnAdopcion.hbs");
    }

    public ModelAndView publicar(Request req, Response res) {
        setUsuarioLogueado(req);
        List<Contacto> contactos = new ArrayList<>();
        contactos.add(new Contacto("Facundo", "Pittaluga", 1138636324, "facupitta@hotmail.com"));
        Duenio model = new Duenio(req.queryParams("usuario"),
                req.queryParams("contrasenia"),
                new DatosPersonales(req.queryParams("nombre"), req.queryParams("apellido"), LocalDate.now(), TipoDocumento.DNI, 42375218, contactos, req.queryParams("direccion")));

        AdapterJPA.beginTransaction();
        RepositorioUsuarios.getInstance().registrarUsuario(model);
        AdapterJPA.commit();

        setModelo(model);
        return new ModelAndView(getDiccionario(),"home.hbs");
    }
}
