package domain.Persona;

import domain.Exceptions.ContraseniaInvalidaException;
import domain.Exceptions.UsuarioYaRegistradoException;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioUsuarios;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioTest {

    @Test
    public void sePuedeVerificarLaContraseniaHasheadaDeUnUsuario() {
        Usuario u = new Voluntario("Pepe", "churras-89-quito");
        u.registrarse();

        assert(u.matcheaContrasenia("churras-89-quito"));
    }

    @Test
    public void unAdministradorSePersiste() {
        Usuario a = new Administrador("Juan", "juan12345_xX");
        a.registrarse();

        Assertions.assertEquals(a, RepositorioUsuarios.getInstance().getUsuario(a.getId()));
    }

    @Test
    public void unDuenioSePersiste() {
        Usuario d = crearDuenio("Pancho", "Picasso", "Calle Falsa 123");
        d.registrarse();

        Assertions.assertEquals(d, RepositorioUsuarios.getInstance().getUsuario(d.getId()));
    }

    @Test
    public void noSePuedenCrearUsuariosRepetidos() {
        Usuario a = new Voluntario("marcelin", "Churrascoide!!777");
        RepositorioUsuarios.getInstance().registrarUsuario(a);

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
