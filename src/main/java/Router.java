import controllers.HomeController;
import controllers.PruebaController;
import controllers.RegistrarseController;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
    public static void configure() {
        HandlebarsTemplateEngine te = new HandlebarsTemplateEngine();

        HomeController homeController = new HomeController();
        RegistrarseController registrarseController = new RegistrarseController();
        PruebaController pruebaController = new PruebaController();


        DebugScreen.enableDebugScreen();

        Spark.staticFiles.location("public");

        Spark.get("/", (req, res) -> homeController.index(req, res), te);
        Spark.get("/me", (req, res) -> pruebaController.index(req, res), te);
        Spark.get("/registrarse", (req, res) -> registrarseController.index(req, res), te);
        Spark.post("/registrarse", (req, res) -> registrarseController.registrar(req, res), te);
    }
}
