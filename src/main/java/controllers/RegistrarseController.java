package controllers;

import Funciones.ValidadorContrasenias;
import domain.Persona.Administrador;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistrarseController extends BaseController {
    public ModelAndView index(Request req, Response res) {
        setUsuarioLogueado(req);
        set("tipos_documento", TipoDocumento.values());
        return new ModelAndView(getDiccionario(), "registrarse.hbs");
    }

    public ModelAndView admin(Request req, Response res) {
        setUsuarioLogueado(req);
        return new ModelAndView(getDiccionario(), "registrarseAdmin.hbs");
    }

    public ModelAndView registrar(Request req, Response res) {
        setUsuarioLogueado(req);
        Boolean hayErrores = false;


        List<Contacto> contactos = new ArrayList<>();
        contactos.add(new Contacto("Facundo", "Pittaluga", 1138636324, "facupitta@hotmail.com")); //TODO deshardcodear
        LocalDate fechaNac = null;
        String usuario = "";
        String contrasenia = "";
        String nombre = "";
        String apellido = "";
        TipoDocumento tipoDoc = null;
        Integer numDoc = null;
        String direccion = "";

        try {

            usuario = req.queryParams("usuario");
            contrasenia = req.queryParams("contrasenia");
            nombre = req.queryParams("nombre");
            apellido = req.queryParams("apellido");
            direccion = req.queryParams("direccion");
            fechaNac = stringToLocalDate(req.queryParams("nacimiento"));
            tipoDoc = stringToTipoDocumento(req.queryParams("tipo_doc"));
            numDoc = Integer.parseInt(req.queryParams("num_doc"));


        } catch (Exception e) {
            set("campos_invalidos", true);
            hayErrores = true;
        }

        if (fechaNac == null || usuario.isEmpty() || contrasenia.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || numDoc == null || direccion.isEmpty()) {
            set("campos_incompletos", true);
            hayErrores = true;
        }
        if (!ValidadorContrasenias.esContraseniaValida(contrasenia)) {
            set("contrasenia_invalida", true);
            hayErrores = true;
        }

        if (hayErrores) {
            return new ModelAndView(getDiccionario(), "registrarse.hbs");
        }

        Duenio model = new Duenio(usuario,
                contrasenia,
                new DatosPersonales(nombre, apellido, fechaNac, tipoDoc, numDoc, contactos, direccion));
        model.setUrlFotoPerfil("/sin_perfil.png");

        AdapterJPA.beginTransaction();
        model.registrarse();
        AdapterJPA.commit();

        AdapterJPA.entityManager().clear();
        AdapterJPA.entityManager().getEntityManagerFactory().getCache().evictAll();
        AdapterJPA.entityManager().close();

        res.redirect("/");
        return null;
    }

    public ModelAndView registrarAdmin(Request req, Response res) {
        setUsuarioLogueado(req);
        Boolean hayErrores = false;

        String usuario = req.queryParams("usuario");
        String contrasenia = req.queryParams("contrasenia");


        if (usuario.isEmpty() || contrasenia.isEmpty()) {
            set("campos_incompletos", true);
            hayErrores = true;
        }
        if (!ValidadorContrasenias.esContraseniaValida(contrasenia)) {
            set("contrasenia_invalida", true);
            hayErrores = true;
        }

        if (hayErrores) {
            return new ModelAndView(getDiccionario(), "registrarseAdmin.hbs");
        }

        Administrador admin = new Administrador(usuario, contrasenia);

        AdapterJPA.beginTransaction();
        RepositorioUsuarios.getInstance().registrarUsuario(admin);
        AdapterJPA.commit();

        AdapterJPA.entityManager().clear();
        AdapterJPA.entityManager().getEntityManagerFactory().getCache().evictAll();
        AdapterJPA.entityManager().close();

        res.redirect("/");
        return null;
    }
}
