package controllers;

import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;

import domain.Persona.Rescatista;
import domain.Repositorio.AdapterJPA;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;

public class RescatarController extends BaseController{
  public ModelAndView index(Request req, Response res) {
    init(req);

    set("tipos_documento", TipoDocumento.values());

    return new ModelAndView(getDiccionario(), "rescatar.hbs");
  }

  public ModelAndView rescatar(Request req, Response res) throws ServletException, IOException {
    init(req);

    MultipartConfigElement config = new MultipartConfigElement(
            "imagenes",
            1000000,
            1000000,
            1024);

    req.raw().setAttribute("org.eclipse.jetty.multipartConfig", config);

    Part uploadedFile = req.raw().getPart("fotos");

    String url = subirFoto(uploadedFile, "rescues/" + generatePath());

    setUsuarioLogueado(req);
    String chapitaOpcional = req.queryParams("chapita");

    String descripcion = req.queryParams("descripcion");
    //String lugarEncuentro = req.queryParams("lugarEncuentro");

    LocalDate fechaEncuentro = stringToLocalDate(req.queryParams("fecha_encuentro"));

    String nombre = req.queryParams("nombre");
    String apellido = req.queryParams("apellido");
    LocalDate fechaNacimiento = stringToLocalDate(req.queryParams("fechaNacimiento"));

    TipoDocumento tipoDocumento = stringToTipoDocumento(req.queryParams("tipo_doc"));
    Integer nroDocumento = Integer.parseInt(req.queryParams("num_doc"));
    String direccion = req.queryParams("direccion");
    Contacto contacto1 = new Contacto(nombre, apellido, Integer.parseInt(req.queryParams("contacto1")), null);
    Contacto contacto2 = new Contacto(nombre, apellido, Integer.parseInt(req.queryParams("contacto2")), null);

    DatosPersonales dp = new DatosPersonales(nombre, apellido, fechaNacimiento, tipoDocumento, nroDocumento, Arrays.asList(contacto1, contacto2), direccion);

    FormularioMascotaPerdida formulario;
    if (chapitaOpcional == null || chapitaOpcional.isEmpty())
      formulario = new FormularioMascotaPerdida(dp, descripcion, Arrays.asList(url), new Ubicacion(0.0,0.0), fechaEncuentro);
    else
      formulario = new FormularioMascotaPerdida(dp, descripcion, null, new Ubicacion(0.0,0.0), fechaEncuentro, Long.parseLong(chapitaOpcional));

    AdapterJPA.beginTransaction();
    (new Rescatista()).generarSolicitudPublicacion(formulario);
    AdapterJPA.commit();

    return new ModelAndView(null,"rescatar.hbs");
  }
}
