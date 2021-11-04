import controllers.*;
import domain.Repositorio.AdapterJPA;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import spark.Spark;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
    public static void configure() {
        HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

        HomeController homeController = new HomeController();
        LoginController loginController = new LoginController();
        RegistrarseController registrarseController = new RegistrarseController();
        AdopcionController adopcionController = new AdopcionController();
        UsuarioController usuarioController = new UsuarioController();


        DebugScreen.enableDebugScreen();

        Spark.staticFiles.location("public");

        Spark.before((req, res) -> {
            AdapterJPA.entityManager().getEntityManagerFactory().getCache().evictAll();
            AdapterJPA.entityManager().close();
        });

        Spark.after((req, res) -> {
            AdapterJPA.entityManager().getEntityManagerFactory().getCache().evictAll();
            AdapterJPA.entityManager().close();
        });

        Spark.get("/", (req, res) -> homeController.index(req, res), engine);
        Spark.get("/usuarios", (req, res) -> usuarioController.todos(req, res), engine);
        Spark.get("/usuarios/me", (req, res) -> usuarioController.me(req, res), engine);
        Spark.get("/usuarios/:id/contactos", (req, res) -> usuarioController.contactos(req, res), engine);

        Spark.get("/iniciarSesion", (req, res) -> loginController.index(req, res), engine);
        Spark.post("/iniciarSesion", (req, res) -> loginController.loguearse(req, res), engine);
        Spark.get("/cerrarSesion", (req, res) -> loginController.desloguearse(req, res), engine);

        Spark.get("/adoptar", (req, res) -> adopcionController.index(req, res), engine);
        Spark.post("/adoptar", (req, res) -> adopcionController.publicar(req, res), engine);

        Spark.get("/registrarse", (req, res) -> registrarseController.index(req, res), engine);
        Spark.post("/registrarse", (req, res) -> registrarseController.registrar(req, res), engine);
    }
}
