package domain.Persona;

import domain.Exceptions.ContraseniaInvalidaException;
import domain.Exceptions.UsuarioYaRegistradoException;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioUsuarios;
import org.junit.jupiter.api.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioTest extends AbstractPersistenceTest implements WithGlobalEntityManager {

    @BeforeEach
    public void antes() {
        AdapterJPA.beginTransaction();
    }

    @AfterEach
    public void despues() {
        AdapterJPA.rollback();
    }


    @Test
    public void sePuedeVerificarLaContraseniaHasheadaDeUnUsuario() {
        Usuario u = new Voluntario("Pepe", "churras-89-quito");
        assert(u.matcheaContrasenia("churras-89-quito"));
    }

    @Test
    public void unAdministradorSePersiste() {
        Usuario a = new Administrador("Juan", "juan12345_xX");
        AdapterJPA.persist(a);

        Assertions.assertEquals(a, RepositorioUsuarios.getInstance().getUsuario(a.getId()));
    }

    @Test
    public void unDuenioSePersiste() {
        Duenio d = crearDuenio("Pancho", "Picasso", "Calle Falsa 123");

        AdapterJPA.persist(d);
        d.getDatosPersonales().getContactos().forEach(contacto -> AdapterJPA.persist(contacto));

        Assertions.assertEquals(d, RepositorioUsuarios.getInstance().getUsuario(d.getId()));
    }

    @Test
    public void noSePuedenCrearUsuariosRepetidos() {
        Usuario a = new Voluntario("marcelin", "Churrascoide!!777");

        AdapterJPA.persist(a);

        Assertions.assertThrows(UsuarioYaRegistradoException.class, () -> {new Administrador("marcelin", "adsajsj12_@12");});
    }

    @Test
    public void noSePuedenCrearUsuariosConContraseniasInvalidas() {
        Assertions.assertThrows(ContraseniaInvalidaException.class, () -> {new Administrador("epicardo", "hola");});
    }

    /** Funciones y definiciones **/

    public Duenio crearDuenio(String nombre, String apellido, String direccion) {
        Contacto c = new Contacto("Andres", "Calamaro", 1138636324, "andres_calamaro@hotmail.com.ar");
        List<Contacto> contactos = new ArrayList<>();
        contactos.add(c);
        DatosPersonales dp = new DatosPersonales(nombre, apellido, LocalDate.of(2010, 4, 6), TipoDocumento.DNI, 30214664, contactos, direccion);
        Duenio duenio = new Duenio(nombre.substring(0, 3).toLowerCase() + "_" + apellido.substring(0, 3).toLowerCase(), apellido + apellido.length(), dp);
        return duenio;
    }
}