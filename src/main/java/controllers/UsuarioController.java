package controllers;

import com.github.jknack.handlebars.Handlebars;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.Duenio;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.List;

public class UsuarioController extends BaseController {

    public ModelAndView todos(Request req, Response res) {
        init(req);

        List<Duenio> model = RepositorioUsuarios.getInstance().getDueniosRegistrados();
        setModelo(model);

        return new ModelAndView(getDiccionario(), "puto.hbs");
    }

    public ModelAndView contactos(Request req, Response res) {

        init(req);

        long id = Long.parseLong(req.params("id"));
        Duenio duenio =  RepositorioUsuarios.getInstance().getDuenio(id);
        if (duenio == null) {
            res.redirect("/404");
        }

        assert duenio != null;
        List<Contacto> contactos = duenio.getDatosPersonales().getContactos();

        setModelo(contactos);

        return new ModelAndView(getDiccionario(), "contactos.hbs");
    }

    public ModelAndView me(Request req, Response res) {
        init(req);

        Duenio duenio = RepositorioUsuarios.getInstance().getDuenio(Long.parseLong(req.params("id")));

        set("usuario_param", duenio);
        set("mismo_usuario", req.cookie("usuario_logueado").equals(duenio.getNombreUsuario()));

        set("tiene_mascotas", duenio.getMascotas().isEmpty());

        return new ModelAndView(getDiccionario(), "usuario.hbs");
    }

    public ModelAndView cambiarFotoPerfil(Request req, Response res) throws ServletException, IOException {
        init(req);

        MultipartConfigElement config = new MultipartConfigElement(
                "imagenes",
                1000000,
                1000000,
                1024);

        req.raw().setAttribute("org.eclipse.jetty.multipartConfig", config);

        Part uploadedFile = req.raw().getPart("foto");

        Duenio d = RepositorioUsuarios.getInstance().getDuenioPorNombre(req.cookie("usuario_logueado"));

        String nuevaUrl = generateProfilePath(d) + getFormat(uploadedFile.getSubmittedFileName());

        Path out = Paths.get(getPublicPath() + nuevaUrl);

        try (final InputStream in = uploadedFile.getInputStream()) {
            Files.copy(in, out);
            uploadedFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AdapterJPA.beginTransaction();
        d.setUrlFotoPerfil("/" + nuevaUrl);
        AdapterJPA.commit();

        setModelo(d);

        return new ModelAndView(getDiccionario(), "usuario.hbs");
    }
}
