package controllers;

import domain.Persona.AtributosPersona.TipoDocumento;

import spark.ModelAndView;
import spark.Request;
import spark.Response;


import java.util.Arrays;


public class RescatarController extends BaseController{
  public ModelAndView index(Request req, Response res) {

    set("tipos_documento", TipoDocumento.values());
    return new ModelAndView(getDiccionario(), "rescatar.hbs");
  }

  public ModelAndView rescatar(Request req, Response res) {

   /*
    AdapterJPA.beginTransaction();
    model.registrarse();
    AdapterJPA.commit();

    AdapterJPA.entityManager().getEntityManagerFactory().getCache().evictAll();
    AdapterJPA.entityManager().clear();
    AdapterJPA.entityManager().close();
  */

    return new ModelAndView(null,"rescatar.hbs");
  }
}
