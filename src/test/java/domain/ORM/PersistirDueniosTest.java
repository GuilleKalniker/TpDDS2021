package domain.ORM;

import domain.Mascota.AtributosMascota.Caracteristica;
import domain.Mascota.AtributosMascota.Sexo;
import domain.Mascota.AtributosMascota.TipoMascota;
import domain.Mascota.AtributosMascota.Ubicacion;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.AtributosPersona.DatosPersonales;
import domain.Persona.AtributosPersona.TipoDocumento;
import domain.Persona.Duenio;
import domain.Repositorio.AdapterJPA;
import domain.Sistema.CentroDeRescate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersistirDueniosTest {

    public Duenio crearDuenio(String nombre, String apellido, String direccion) {
        Contacto c = new Contacto("Andres", "Calamaro", 1138636324, "andres_calamaro@hotmail.com.ar");
        List<Contacto> contactos = new ArrayList<>();
        contactos.add(c);
        DatosPersonales dp = new DatosPersonales(nombre, apellido, LocalDate.of(2010, 4, 6), TipoDocumento.DNI, 30214664, contactos, direccion);
        Duenio duenio = new Duenio(nombre.substring(0, 3).toLowerCase() + "_" + apellido.substring(0, 3).toLowerCase(), apellido + apellido.length(), dp);
        return duenio;
    }

    public MascotaRegistrada crearMascota(String nombre, ArrayList<String> fotos) {
        return new MascotaRegistrada(TipoMascota.PERRO, nombre, nombre.substring(0, 2) + nombre.substring(0, 2),4, Sexo.FEMENINO, "Muerta de hambre", fotos, new ArrayList<Caracteristica>());
    }

    @Test
    public void sePersistenDuenios() {
        Duenio d = crearDuenio("Ricardo", "Arjona", "Coso 1234");
        d.getDatosPersonales().getContactos().get(0).setDuenio(d);

        AdapterJPA.persistir(d);
        AdapterJPA.persistir(d.getDatosPersonales().getContactos().get(0));
    }


    @Test
    public void sePersistenMascotas() {
        ArrayList<String> fotos = new ArrayList<>();
        fotos.add("https://foto.com.ar");
        fotos.add("https://foto2.com.gov");


        MascotaRegistrada m = crearMascota("pICHULA", fotos);
        //m.setID(String.valueOf(new Random().nextInt()));

        m.getCaracteristicas().add(Caracteristica.RABIOSO);
        m.getCaracteristicas().add(Caracteristica.JUGUETON);


        AdapterJPA.persistir(m);
    }

    @Test
    public void sePersistenCentros() {
        CentroDeRescate centro = new CentroDeRescate(new Ubicacion(321.04, 6542.21));

        AdapterJPA.persistir(centro);
    }
}
