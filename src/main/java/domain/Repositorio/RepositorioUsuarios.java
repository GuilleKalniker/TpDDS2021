package domain.Repositorio;

import domain.Exceptions.IDNoSeCorrespondeException;
import domain.Mascota.MascotaRegistrada;
import domain.Persona.AtributosPersona.Contacto;
import domain.Persona.Duenio;
import domain.Persona.Usuario;
import domain.Persona.Voluntario;
import domain.Persona.Administrador;

import javax.persistence.*;
import java.util.List;

public class RepositorioUsuarios {

  private static RepositorioUsuarios INSTANCE = null;

  public static RepositorioUsuarios getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new RepositorioUsuarios();
    }
    return INSTANCE;
  }

  public void registrarUsuario(Usuario usuario) {
    AdapterJPA.persist(usuario);
  }

  public void persistirContacto(Contacto contacto) {
    AdapterJPA.persist(contacto);
  }

  public List<Duenio> getDueniosRegistrados() {
    TypedQuery<Duenio> query = AdapterJPA.entityManager().createQuery("select d from Duenio d", Duenio.class);
    return query.getResultList();
  }

  public List<Administrador> getAdministradoresRegistrados() {
    TypedQuery<Administrador> query = AdapterJPA.entityManager().createQuery("select a from Administrador a", Administrador.class);
    return query.getResultList();
  }

  public List<Voluntario> getVoluntariosRegistrados() {
    TypedQuery<Voluntario> query = AdapterJPA.entityManager().createQuery("select v from Voluntario v", Voluntario.class);
    return query.getResultList();
  }

  public Boolean existeUsuario(String nombreUsuario){
    TypedQuery<Usuario> query = AdapterJPA.entityManager().createQuery("select d from Usuario d where d.nombreUsuario = :username", Usuario.class);
    query.setParameter("username", nombreUsuario);
    return !query.getResultList().isEmpty();
  }

  public Usuario getUsuario(long id) {
    return AdapterJPA.entityManager().find(Usuario.class, id);
  }

  public Duenio getDuenioPorID(long ID) {
    try {
      MascotaRegistrada mascota = AdapterJPA.entityManager().find(MascotaRegistrada.class, ID);
      return mascota.getDuenio();
    } catch (NullPointerException e){
      throw new IDNoSeCorrespondeException("No se encontro el duenio a partir del ID de la mascota");
    }
  }

  public Usuario getUsuarioPorNombre(String nombreUsuario) {
    Usuario u;
    try {
      TypedQuery<Usuario> query = AdapterJPA.entityManager().createQuery("select d from Usuario d where d.nombreUsuario = :username", Usuario.class);
      query.setParameter("username", nombreUsuario);
      u = query.getSingleResult();
    }
    catch(Exception e) {
      u = null;
    }
    return u;
  }
}
