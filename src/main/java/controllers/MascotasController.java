package controllers;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Mascota.AtributosMascota.Sexo;
import domain.Mascota.AtributosMascota.TipoMascota;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.Duenio;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MascotasController extends BaseController {
    public ModelAndView registroMascota(Request req, Response res) {
        setUsuarioLogueado(req);
        Duenio d = RepositorioUsuarios.getInstance().getDuenioPorNombre(req.cookie("usuario_logueado"));
        if (d == null)
            res.redirect("/iniciarSesion");
        setModelo(d);
        set("tipos_mascota", TipoMascota.values());
        set("sexos", Sexo.values());
        set("caracteristicas", Caracteristica.values());
        return new ModelAndView(getDiccionario(), "registrarMascota.hbs");
    }

    public ModelAndView registrarMascota(Request req, Response res) throws ServletException, IOException {
        setUsuarioLogueado(req);

        Duenio d = RepositorioUsuarios.getInstance().getDuenioPorNombre(req.cookie("usuario_logueado"));



        MultipartConfigElement config = new MultipartConfigElement(
                "imagenes",
                1000000,
                1000000,
                1024);

        req.raw().setAttribute("org.eclipse.jetty.multipartConfig", config);

        Part uploadedFile = req.raw().getPart("foto");
        //TODO descargar foto con nombre unico

        String nombre = req.queryParams("nombre");
        String apodo = req.queryParams("apodo");
        TipoMascota especie = stringToTipoMascota(req.queryParams("especie"));
        Integer edad = Integer.parseInt(req.queryParams("edad"));
        String descripcion = req.queryParams("descripcion");
        Sexo sexo = stringToSexo(req.queryParams("sexo"));
        ArrayList<String> fotos = new ArrayList<>();//TODO
        List<Caracteristica> caracteristicas = parsearCaracteristicas(req);

        String nuevaUrl = generatePath() + getFormat(uploadedFile.getSubmittedFileName());

        Path out = Paths.get(getPublicPath() + nuevaUrl);

        try (final InputStream in = uploadedFile.getInputStream()) {
            Files.copy(in, out);
            uploadedFile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }

        fotos.add(nuevaUrl);
        MascotaRegistrada mascota = new MascotaRegistrada(especie, nombre, apodo, edad, sexo, descripcion, fotos, caracteristicas);

        AdapterJPA.beginTransaction();
        d.registrarMascota(mascota);
        AdapterJPA.commit();

        res.redirect("/");
        return null;
    }

    private List<Caracteristica> parsearCaracteristicas(Request req) {

        return Arrays.stream(Caracteristica.values()).filter(caracteristica -> req.queryParams(caracteristica.name()) != null).collect(Collectors.toList());
    }
}
