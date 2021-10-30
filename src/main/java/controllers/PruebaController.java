package controllers;

import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PruebaController {
    public ModelAndView index(Request req, Response res) {
        List<Contacto> contactos = new ArrayList<>();
        contactos.add(new Contacto("Facundo", "Pittaluga", 1138636324, "facupitta@hotmail.com"));
        Duenio model = new Duenio("facupitta",
                "TeLaKreisteWeXd123",
                new DatosPersonales("Facundo", "Pittaluga", LocalDate.now(), TipoDocumento.DNI, 42375218, contactos, "Bolivia 754"));
        return new ModelAndView(model, "usuario.hbs");
    }
}
