import controllers.*;
import domain.Repositorio.AdapterJPA;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
    public static void configure() {
        HandlebarsTemplateEngine te = new HandlebarsTemplateEngine();

        HomeController homeController = new HomeController();
        LoginController loginController = new LoginController();
        RegistrarseController registrarseController = new RegistrarseController();
        AdopcionController adopcionController = new AdopcionController();
        PruebaController pruebaController = new PruebaController();


        DebugScreen.enableDebugScreen();

        Spark.staticFiles.location("public");

        Spark.before("/*", (req, res) -> AdapterJPA.entityManager().clear());

        Spark.after("/*", (req, res) -> AdapterJPA.entityManager().clear());

        Spark.get("/", (req, res) -> homeController.index(req, res), te);
        Spark.get("/usuarios", (req, res) -> pruebaController.todos(req, res), te);
        Spark.get("/usuarios/me", (req, res) -> pruebaController.me(req, res), te);

        Spark.get("/iniciarSesion", (req, res) -> loginController.index(req, res), te);
        Spark.post("/iniciarSesion", (req, res) -> loginController.loguearse(req, res), te);
        Spark.get("/cerrarSesion", (req, res) -> loginController.desloguearse(req, res), te);

        Spark.get("/adoptar", (req, res) -> adopcionController.index(req, res), te);
        Spark.post("/adoptar", (req, res) -> adopcionController.publicar(req, res), te);

        Spark.get("/registrarse", (req, res) -> registrarseController.index(req, res), te);
        Spark.post("/registrarse", (req, res) -> registrarseController.registrar(req, res), te);
    }
}
