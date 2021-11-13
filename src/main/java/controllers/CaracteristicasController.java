package controllers;

import domain.Mascota.AtributosMascota.Caracteristica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.Persona.Administrador;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioMascotas;
import domain.Repositorio.RepositorioUsuarios;
import org.apache.maven.model.Model;
import org.eclipse.jetty.util.Promise;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CaracteristicasController extends BaseController {

  public ModelAndView index(Request req, Response res) {
    init(req);

    Administrador admin = RepositorioUsuarios.getInstance().getAdministradorPorNombre(req.cookie("usuario_logueado"));
    if (admin == null) {
      res.redirect("/");
    }

    setModelo(RepositorioMascotas.getInstance().getTodasLasCaracteristicas());

    return new ModelAndView(getDiccionario(), "config.hbs");
  }
  public ModelAndView eliminar(Request req, Response res) {
    init(req);

    Administrador admin = RepositorioUsuarios.getInstance().getAdministradorPorNombre(req.cookie("usuario_logueado"));
    if (admin == null) {
      res.redirect("/");
    }

    long idCaracteristica = Long.parseLong(req.params("id"));
    AdapterJPA.beginTransaction();

    RepositorioMascotas.getInstance().eliminarCaracteristica(idCaracteristica);

    AdapterJPA.commit();
    AdapterJPA.cleanCache();

    res.redirect("/caracteristicas");
    return null;
  }

  public ModelAndView modificar(Request req, Response res) {
    init(req);

    Administrador admin = RepositorioUsuarios.getInstance().getAdministradorPorNombre(req.cookie("usuario_logueado"));
    if (admin == null) {
      res.redirect("/");
    }

    String nuevaCaracteristica = req.queryParams("nueva_caracteristica");

    AdapterJPA.beginTransaction();
    actualizarCaracteristicas(req);
    if (nuevaCaracteristica != null && !nuevaCaracteristica.isEmpty()) {
      RepositorioMascotas.getInstance().crearCaracteristica(new Caracteristica(nuevaCaracteristica));
    }
    AdapterJPA.commit();

    setModelo(RepositorioMascotas.getInstance().getTodasLasCaracteristicas());


    res.redirect("/caracteristicas");
    return null;
  }

}
