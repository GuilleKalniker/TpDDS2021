package domain.ORM;

import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersistirDueniosTest {

    public Duenio crearDuenio(String nombre, String apellido, String direccion) {
        Contacto c = new Contacto("Andres", "Calamaro", 1138636324, "andres_calamaro@hotmail.com.ar");
        List<Contacto> contactos = new ArrayList<>();
        contactos.add(c);
        DatosPersonales dp = new DatosPersonales(nombre, apellido, LocalDate.of(2010, 4, 6), TipoDocumento.DNI, 30214664, contactos, direccion);
        Duenio duenio = new Duenio(nombre.substring(0, 3).toLowerCase() + "_" + apellido.substring(0, 3).toLowerCase(), apellido + apellido.length(), dp);
        return duenio;
    }

    @Test
    public void sePersistenDuenios() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Duenio d = crearDuenio("Pedro", "Picapiedra", "Groenlandia 1234");
        d.getDatosPersonales().getContactos().get(0).setDuenio(d);

        entityManager.persist(d.getDatosPersonales().getContactos().get(0));
        entityManager.persist(d);

        transaction.commit();


        transaction.begin();

        Duenio d2 = entityManager.find(Duenio.class, d.getId());
        Assertions.assertEquals(TipoDocumento.DNI, d2.getDatosPersonales().getTipoDocumento());

        transaction.commit();
    }
}
