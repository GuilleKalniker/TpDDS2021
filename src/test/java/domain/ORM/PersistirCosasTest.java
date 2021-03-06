package domain.ORM;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Mascota.AtributosMascota.Sexo;
import domain.Mascota.AtributosMascota.TipoMascota;
import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.FormularioMascotaPerdida;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import domain.Publicacion.PublicacionMascotaPerdida;
import domain.Repositorio.AdapterJPA;
import domain.Repositorio.RepositorioCentroDeRescate;
import domain.Sistema.CentroDeRescate;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class PersistirCosasTest {
    @BeforeEach
    public void setup() {
        AdapterJPA.beginTransaction();
    }

    @AfterEach
    public void teardown() {
        AdapterJPA.rollback();
    }

    public Duenio crearDuenio(String nombre, String apellido, String direccion) {
        Contacto c = new Contacto("Andres", "Calamaro", 1138636324, "andres_calamaro@hotmail.com.ar");
        List<Contacto> contactos = new ArrayList<>();
        contactos.add(c);
        DatosPersonales dp = new DatosPersonales(nombre, apellido, LocalDate.of(2010, 4, 6), TipoDocumento.DNI, 30214664, contactos, direccion);
        Duenio duenio = new Duenio(nombre.substring(0, 3).toLowerCase() + "_" + apellido.substring(0, 3).toLowerCase(), apellido + apellido.length() + "@!", dp);
        return duenio;
    }

    public MascotaRegistrada crearMascota(String nombre, ArrayList<String> fotos) {
        return new MascotaRegistrada(TipoMascota.PERRO, nombre, nombre.substring(0, 2) + nombre.substring(0, 2).toLowerCase(),4, Sexo.HEMBRA, "Muerta de hambre", fotos, new ArrayList<Caracteristica>());
    }

    public PublicacionMascotaPerdida crearPublicacion() {
        Contacto c = new Contacto("Andres", "Calamaro", 1138636324, "andres_calamaro@hotmail.com.ar");
        List<Contacto> contactos = new ArrayList<>();
        contactos.add(c);
        DatosPersonales dp = new DatosPersonales("Rocket", "Launcher", LocalDate.of(1998, 12, 2), TipoDocumento.DNI, 38642215, contactos, "Johto 1995");

        FormularioMascotaPerdida f = new FormularioMascotaPerdida(dp, "Hecho mierda", new ArrayList<>(), new Ubicacion(214.83,53.75), LocalDate.of(2021, 9, 22), 3);

        return new PublicacionMascotaPerdida(f);

    }

    @Test
    public void sePersistenDuenios() {
        Duenio d = crearDuenio("Palito", "Arjona", "Coso 1234");
        d.getDatosPersonales().getContactos().get(0).setDuenio(d);

        AdapterJPA.persist(d);
        AdapterJPA.persist(d.getDatosPersonales().getContactos().get(0));

        Assertions.assertEquals(d, AdapterJPA.entityManager().find(Duenio.class, d.getId()));
    }


    @Test
    public void sePersistenCentros() {
        CentroDeRescate centro = new CentroDeRescate(new Ubicacion(321.04, 6542.21));

        RepositorioCentroDeRescate.getInstance().registrarCentroDeRescate(centro);

        centro.setUbicacion(new Ubicacion(1.0, 2.0));

        List<CentroDeRescate> lista = RepositorioCentroDeRescate.getInstance().getCentrosDeRescateRegistrados();

        Assertions.assertEquals(centro, lista.stream().filter(unCentro -> unCentro.getId() == centro.getId()).findFirst().get());
    }

    @Test
    public void sePersistenPublicacionesMascotasPerdidas() {
        PublicacionMascotaPerdida p = crearPublicacion();

        AdapterJPA.persist(p);
    }
}
