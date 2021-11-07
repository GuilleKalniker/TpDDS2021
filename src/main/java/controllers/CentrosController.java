package controllers;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Sistema.CentroDeRescate;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CentrosController extends BaseController {
    public ModelAndView nuevoCentro(Request req, Response res) {
        setUsuarioLogueado(req);
        return new ModelAndView(getDiccionario(), "nuevoCentro.hbs");
    }

    public ModelAndView crearCentro(Request req, Response res) {
        setUsuarioLogueado(req);

        Ubicacion ubicacion = new Ubicacion(Double.parseDouble(req.queryParams("lat")), Double.parseDouble(req.queryParams("lon")));
        CentroDeRescate centro = new CentroDeRescate(ubicacion);

        AdapterJPA.beginTransaction();
        RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(centro);
        AdapterJPA.commit();

        res.redirect("/");
        return null;
    }
}
