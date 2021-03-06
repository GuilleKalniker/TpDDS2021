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
        init(req);

        set("tipos_documento", TipoDocumento.values());

        return new ModelAndView(getDiccionario(), "registrarse.hbs");
    }

    public ModelAndView admin(Request req, Response res) {
        init(req);

        return new ModelAndView(getDiccionario(), "registrarseAdmin.hbs");
    }

    public ModelAndView registrar(Request req, Response res) {
        init(req);

        List<Contacto> contactos = new ArrayList<>();
        LocalDate fechaNac = null;
        String usuario = "";
        String contrasenia = "";
        String nombre = "";
        String apellido = "";
        TipoDocumento tipoDoc = null;
        Integer numDoc = null;
        String direccion = "";
        Integer telefono = null;
        String email = "";

        try {

            usuario = req.queryParams("usuario");
            contrasenia = req.queryParams("contrasenia");
            nombre = req.queryParams("nombre");
            apellido = req.queryParams("apellido");
            direccion = req.queryParams("direccion");
            fechaNac = stringToLocalDate(req.queryParams("nacimiento"));
            tipoDoc = stringToTipoDocumento(req.queryParams("tipo_doc"));
            numDoc = Integer.parseInt(req.queryParams("num_doc"));
            telefono = Integer.parseInt(req.queryParams("telefono"));
            email = req.queryParams("email");

            contactos.add(new Contacto(nombre, apellido, telefono, email));


        } catch (Exception e) {
            set("errorcito", e.getMessage());
            setError("campos_invalidos");
        }

        if (fechaNac == null || usuario.isEmpty() || contrasenia.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || numDoc == null || direccion.isEmpty() || telefono == null || email.isEmpty()) {
            setError("campos_incompletos");
        }
        if (!ValidadorContrasenias.esContraseniaValida(contrasenia)) {
            setError("contrasenia_invalida");
        }

        if (hayErrores) {
            set("tipos_documento", TipoDocumento.values());
            return new ModelAndView(getDiccionario(), "registrarse.hbs");
        }

        Duenio model = new Duenio(usuario,
                contrasenia,
                new DatosPersonales(nombre, apellido, fechaNac, tipoDoc, numDoc, contactos, direccion));
        model.setUrlFotoPerfil("/sin_perfil.png");

        AdapterJPA.beginTransaction();
        model.registrarse();
        AdapterJPA.commit();

        res.redirect("/");
        return null;
    }

    public ModelAndView registrarAdmin(Request req, Response res) {
        init(req);

        String usuario = req.queryParams("usuario");
        String contrasenia = req.queryParams("contrasenia");


        if (usuario.isEmpty() || contrasenia.isEmpty()) {
            setError("campos_incompletos");
        }
        if (!ValidadorContrasenias.esContraseniaValida(contrasenia)) {
            setError("contrasenia_invalida");
        }

        if (hayErrores) {
            return new ModelAndView(getDiccionario(), "registrarseAdmin.hbs");
        }

        Administrador admin = new Administrador(usuario, contrasenia);

        AdapterJPA.beginTransaction();
        RepositorioUsuarios.getInstance().registrarUsuario(admin);
        AdapterJPA.commit();


        res.redirect("/");
        return null;
    }
}
