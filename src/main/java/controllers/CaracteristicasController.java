package controllers;

import domain.Mascota.AtributosMascota.Caracteristica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioMascotas;
import org.eclipse.jetty.util.Promise;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CaracteristicasController extends BaseController {

  public ModelAndView index(Request req, Response res) {
    AdapterJPA.cleanCache();

    setUsuarioLogueado(req);
    setModelo(RepositorioMascotas.getInstance().getTodasLasCaracteristicas());

    AdapterJPA.cleanCache();
    return new ModelAndView(getDiccionario(), "config.hbs");
  }

  public ModelAndView modificar(Request req, Response res) {
    AdapterJPA.cleanCache();

    setUsuarioLogueado(req);
    String nuevaCaracteristica = req.queryParams("nueva_caracteristica");

    AdapterJPA.beginTransaction();
    actualizarCaracteristicas(req);
    if (nuevaCaracteristica != null && !nuevaCaracteristica.isEmpty()) {
      RepositorioMascotas.getInstance().crearCaracteristica(new Caracteristica(nuevaCaracteristica));
    }
    AdapterJPA.commit();

    setModelo(RepositorioMascotas.getInstance().getTodasLasCaracteristicas());

    AdapterJPA.cleanCache();

    res.redirect("/caracteristicas");
    return null;
  }

}
