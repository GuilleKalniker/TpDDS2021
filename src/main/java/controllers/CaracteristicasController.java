package controllers;

import domain.Mascota.AtributosMascota.Caracteristica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioMascotas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CaracteristicasController extends BaseController {

  public ModelAndView index(Request req, Response res) {
    setUsuarioLogueado(req);
    setModelo(RepositorioMascotas.getInstance().getTodasLasCaracteristicas());
    return new ModelAndView(getDiccionario(), "config.hbs");
  }

  public ModelAndView modificar(Request req, Response res) {
    setUsuarioLogueado(req);
    String nuevaCaracteristica = req.queryParams("nueva_caracteristica");
    List<Caracteristica> caracteristicasSeleccionadas = parsearCaracteristicas(req);

    if (nuevaCaracteristica != null && !nuevaCaracteristica.isEmpty()) {
      AdapterJPA.beginTransaction();
      RepositorioMascotas.getInstance().crearCaracteristica(new Caracteristica(nuevaCaracteristica));
      AdapterJPA.commit();
    }

    return new ModelAndView(getDiccionario(), "config.hbs");
  }

}
