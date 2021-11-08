import controllers.*;
import domain.Repositorio.AdapterJPA;
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
        RescatarController rescatarController = new RescatarController();
        CentrosController centrosController = new CentrosController();
        MascotasController mascotasController = new MascotasController();

        DebugScreen.enableDebugScreen();

        //Spark.staticFiles.location("public");
        Spark.staticFiles.externalLocation(System.getProperty("user.dir") + "/src/main/resources/public");

        Spark.before((req, res) -> {
            AdapterJPA.entityManager().clear();
            AdapterJPA.entityManager().getEntityManagerFactory().getCache().evictAll();
            AdapterJPA.entityManager().close();
        });

        Spark.after((req, res) -> {
            AdapterJPA.entityManager().clear();
            AdapterJPA.entityManager().getEntityManagerFactory().getCache().evictAll();
            AdapterJPA.entityManager().close();
        });

        Spark.get("/", homeController::index, engine);
        Spark.get("/usuarios", usuarioController::todos, engine);
        Spark.get("/usuarios/me", usuarioController::me, engine);
        Spark.post("/usuarios/me", usuarioController::cambiarFotoPerfil, engine);
        Spark.get("/usuarios/:id/contactos", usuarioController::contactos, engine);

        Spark.get("/mascotas/nueva", mascotasController::registroMascota, engine);
        Spark.post("/mascotas/nueva", mascotasController::registrarMascota, engine);

        Spark.get("/iniciarSesion", loginController::index, engine);
        Spark.post("/iniciarSesion", loginController::loguearse, engine);
        Spark.get("/cerrarSesion", loginController::desloguearse, engine);

        Spark.get("/centros/nuevo", centrosController::nuevoCentro, engine);
        Spark.post("/centros/nuevo", centrosController::crearCentro, engine);

        Spark.get("/adoptar", adopcionController::index, engine);
        Spark.post("/adoptar", adopcionController::publicar, engine);

        Spark.get("/registrarse", registrarseController::index, engine);
        Spark.post("/registrarse", registrarseController::registrar, engine);
        Spark.get("/registrarse/admin", registrarseController::admin, engine);
        Spark.post("/registrarse/admin", registrarseController::registrarAdmin, engine);

        Spark.get("/rescatar", rescatarController::index, engine);
        Spark.post("/rescatar", rescatarController::rescatar, engine);
    }
}
