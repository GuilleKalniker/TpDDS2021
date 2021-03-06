package controllers;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Mascota.AtributosMascota.Sexo;
import domain.Mascota.AtributosMascota.TipoMascota;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.Duenio;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioMascotas;
import domain.Repositorio.RepositorioUsuarios;
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
import java.util.ArrayList;
import java.util.List;

public class MascotasController extends BaseController {
    public ModelAndView registroMascota(Request req, Response res) {
        init(req);

        Duenio d = RepositorioUsuarios.getInstance().getDuenioPorNombre(req.cookie("usuario_logueado"));
        if (d == null)
            res.redirect("/iniciarSesion");
        setModelo(d);
        set("tipos_mascota", TipoMascota.values());
        set("sexos", Sexo.values());
        set("caracteristicas", RepositorioMascotas.getInstance().getCaracteristicasActivas());

        return new ModelAndView(getDiccionario(), "registrarMascota.hbs");
    }

    public ModelAndView registrarMascota(Request req, Response res) throws ServletException, IOException {
        init(req);
        setUsuarioLogueado(req);


        Duenio d = RepositorioUsuarios.getInstance().getDuenioPorNombre(req.cookie("usuario_logueado"));

        MultipartConfigElement config = new MultipartConfigElement(
                "imagenes",
                1000000,
                1000000,
                1024);

        req.raw().setAttribute("org.eclipse.jetty.multipartConfig", config);

        Part uploadedFile = req.raw().getPart("foto");

        String nombre = "";
        String apodo = "";
        TipoMascota especie = null;
        Integer edad = 0;
        String descripcion = "";
        Sexo sexo = null;
        ArrayList<String> fotos = new ArrayList<>();
        List<Caracteristica> caracteristicas = null;

        try {
            nombre = req.queryParams("nombre");
            apodo = req.queryParams("apodo");
            especie = stringToTipoMascota(req.queryParams("especie"));
            edad = Integer.parseInt(req.queryParams("edad"));
            descripcion = req.queryParams("descripcion");
            sexo = stringToSexo(req.queryParams("sexo"));
            caracteristicas = parsearCaracteristicas(req);
        } catch (Exception e) {
            setError("campos_erroneos");
        }

        if (nombre.isEmpty()
                || apodo.isEmpty()
                || descripcion.isEmpty()
                || especie == null
                || edad <= 0
                || sexo == null
                || caracteristicas == null) {
            setError("campos_incompletos");
        }

        if (hayErrores) {
            set("tipos_mascota", TipoMascota.values());
            set("sexos", Sexo.values());
            set("caracteristicas", RepositorioMascotas.getInstance().getCaracteristicasActivas());

            return new ModelAndView(getDiccionario(), "registrarMascota.hbs");
        }

        MascotaRegistrada mascota = new MascotaRegistrada(especie, nombre, apodo, edad, sexo, descripcion, fotos, caracteristicas);

        AdapterJPA.beginTransaction();
        d.registrarMascota(mascota);
        String url = subirFoto(uploadedFile, "pets/" + mascota.getId());
        fotos.add(url);
        AdapterJPA.commit();


        res.redirect("/");
        return null;
    }
}
