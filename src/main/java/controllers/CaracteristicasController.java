package controllers;

import domain.Mascota.AtributosMascota.Caracteristica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CaracteristicasController extends BaseController {

  public ModelAndView index(Request req, Response res) {
    setUsuarioLogueado(req);
    setModelo(Caracteristica.values());
    return new ModelAndView(getDiccionario(), "config.hbs");
  }

  public ModelAndView modificar(Request req, Response res) {
    setUsuarioLogueado(req);
    List<Caracteristica> caracteristicasSeleccionadas = parsearCaracteristicas(req);
    return new ModelAndView(getDiccionario(), "config.hbs");
  }

}
